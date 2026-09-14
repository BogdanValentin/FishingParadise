package net.bogdanvalentin.fishingparadise.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

/**
 * A shapeless recipe that hands the sword back instead of eating it, one point
 * of durability lighter. Behaves exactly like minecraft:crafting_shapeless
 * otherwise, so the JSON is unchanged apart from the type.
 */
public class FilletRecipe extends ShapelessRecipe {
    /** Durability spent per fillet. Set to 0 to make filleting free. */
    private static final int DURABILITY_COST = 1;

    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;

    public FilletRecipe(String group, CraftingBookCategory category, ItemStack output, NonNullList<Ingredient> inputs) {
        super(group, category, output, inputs);
        this.output = output;
        this.inputs = inputs;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < remainders.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(ItemTags.SWORDS)) {
                remainders.set(slot, wearDown(stack));
            } else if (stack.getItem().hasCraftingRemainingItem()) {
                remainders.set(slot, new ItemStack(stack.getItem().getCraftingRemainingItem()));
            }
        }

        return remainders;
    }

    /** Returns the sword with the filleting cost applied, or nothing if that broke it. */
    private static ItemStack wearDown(ItemStack sword) {
        ItemStack kept = sword.copyWithCount(1);
        if (!kept.isDamageableItem() || DURABILITY_COST == 0) {
            return kept;
        }
        if (kept.getDamageValue() + DURABILITY_COST >= kept.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        kept.setDamageValue(kept.getDamageValue() + DURABILITY_COST);
        return kept;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FILLET;
    }

    public static class Serializer implements RecipeSerializer<FilletRecipe> {
        private static final MapCodec<FilletRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(FilletRecipe::getGroup),
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(FilletRecipe::category),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap(
                        list -> {
                            Ingredient[] ingredients = list.stream().filter(i -> !i.isEmpty()).toArray(Ingredient[]::new);
                            if (ingredients.length == 0) {
                                return DataResult.error(() -> "No ingredients for fillet recipe");
                            }
                            if (ingredients.length > 9) {
                                return DataResult.error(() -> "Too many ingredients for fillet recipe");
                            }
                            return DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
                        },
                        DataResult::success
                ).forGetter(recipe -> recipe.inputs)
        ).apply(instance, FilletRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FilletRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        private static FilletRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            String group = buf.readUtf();
            CraftingBookCategory category = buf.readEnum(CraftingBookCategory.class);
            NonNullList<Ingredient> ingredients = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            return new FilletRecipe(group, category, ItemStack.STREAM_CODEC.decode(buf), ingredients);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, FilletRecipe recipe) {
            buf.writeUtf(recipe.getGroup());
            buf.writeEnum(recipe.category());
            buf.writeVarInt(recipe.inputs.size());
            for (Ingredient ingredient : recipe.inputs) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
        }

        @Override
        public MapCodec<FilletRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FilletRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
