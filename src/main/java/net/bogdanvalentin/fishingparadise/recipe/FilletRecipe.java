package net.bogdanvalentin.fishingparadise.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import java.util.List;

public class FilletRecipe extends ShapelessRecipe {
    private static final int DURABILITY_COST = 1;

    private final ItemStack output;

    public FilletRecipe(String group, CraftingBookCategory category, ItemStack output, List<Ingredient> ingredients) {
        super(group, category, output, ingredients);
        this.output = output;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < remainders.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            remainders.set(slot, stack.is(ItemTags.SWORDS)
                    ? wearDown(stack)
                    : stack.getItem().getCraftingRemainder());
        }

        return remainders;
    }

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
    @SuppressWarnings("unchecked")
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) (RecipeSerializer<?>) ModRecipes.FILLET;
    }

    public static class Serializer implements RecipeSerializer<FilletRecipe> {
        private static final MapCodec<FilletRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(FilletRecipe::group),
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(FilletRecipe::category),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
                Ingredient.CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(recipe -> recipe.placementInfo().ingredients())
        ).apply(instance, FilletRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FilletRecipe> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, FilletRecipe::group,
                CraftingBookCategory.STREAM_CODEC, FilletRecipe::category,
                ItemStack.STREAM_CODEC, recipe -> recipe.output,
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), recipe -> recipe.placementInfo().ingredients(),
                FilletRecipe::new);

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
