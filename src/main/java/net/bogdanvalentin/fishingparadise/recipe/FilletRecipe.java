package net.bogdanvalentin.fishingparadise.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.collection.DefaultedList;

public class FilletRecipe extends ShapelessRecipe {
    private static final int DURABILITY_COST = 1;

    private final ItemStack output;

    public FilletRecipe(String group, CraftingRecipeCategory category, ItemStack output, DefaultedList<Ingredient> ingredients) {
        super(group, category, output, ingredients);
        this.output = output;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
        DefaultedList<ItemStack> remainders = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < remainders.size(); slot++) {
            ItemStack stack = inventory.getStack(slot);
            if (stack.isIn(ItemTags.SWORDS)) {
                remainders.set(slot, wearDown(stack));
            } else if (stack.getItem().hasRecipeRemainder()) {
                remainders.set(slot, new ItemStack(stack.getItem().getRecipeRemainder()));
            }
        }

        return remainders;
    }

    private static ItemStack wearDown(ItemStack sword) {
        ItemStack kept = sword.copyWithCount(1);
        if (!kept.isDamageable() || DURABILITY_COST == 0) {
            return kept;
        }
        if (kept.getDamage() + DURABILITY_COST >= kept.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        kept.setDamage(kept.getDamage() + DURABILITY_COST);
        return kept;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FILLET;
    }

    public static class Serializer implements RecipeSerializer<FilletRecipe> {
        private static final Codec<FilletRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(FilletRecipe::getGroup),
                CraftingRecipeCategory.CODEC.optionalFieldOf("category", CraftingRecipeCategory.MISC).forGetter(FilletRecipe::getCategory),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
                Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").flatXmap(
                        list -> {
                            Ingredient[] ingredients = list.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                            if (ingredients.length == 0) {
                                return DataResult.error(() -> "No ingredients for fillet recipe");
                            }
                            if (ingredients.length > 9) {
                                return DataResult.error(() -> "Too many ingredients for fillet recipe");
                            }
                            return DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients));
                        },
                        DataResult::success
                ).forGetter(FilletRecipe::getIngredients)
        ).apply(instance, FilletRecipe::new));

        @Override
        public Codec<FilletRecipe> codec() {
            return CODEC;
        }

        @Override
        public FilletRecipe read(PacketByteBuf buf) {
            String group = buf.readString();
            CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromPacket(buf));
            return new FilletRecipe(group, category, buf.readItemStack(), ingredients);
        }

        @Override
        public void write(PacketByteBuf buf, FilletRecipe recipe) {
            buf.writeString(recipe.getGroup());
            buf.writeEnumConstant(recipe.getCategory());
            buf.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
