package net.bogdanvalentin.fishingparadise.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import java.util.List;

/**
 * A shapeless recipe that hands the sword back instead of eating it, one point
 * of durability lighter. Behaves exactly like minecraft:crafting_shapeless
 * otherwise, so the JSON is unchanged apart from the type.
 */
public class FilletRecipe extends ShapelessRecipe {
    /** Durability spent per fillet. Set to 0 to make filleting free. */
    private static final int DURABILITY_COST = 1;

    private final ItemStackTemplate output;
    private final List<Ingredient> inputs;

    public FilletRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo,
                        ItemStackTemplate output, List<Ingredient> inputs) {
        super(commonInfo, bookInfo, output, inputs);
        this.output = output;
        this.inputs = inputs;
    }

    public static final MapCodec<FilletRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Recipe.CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
            CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter(recipe -> recipe.bookInfo),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
            Ingredient.CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(recipe -> recipe.inputs)
    ).apply(instance, FilletRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FilletRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC, recipe -> recipe.commonInfo,
            CraftingRecipe.CraftingBookInfo.STREAM_CODEC, recipe -> recipe.bookInfo,
            ItemStackTemplate.STREAM_CODEC, recipe -> recipe.output,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), recipe -> recipe.inputs,
            FilletRecipe::new);

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int slot = 0; slot < remainders.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(ItemTags.SWORDS)) {
                remainders.set(slot, wearDown(stack));
            } else {
                ItemStackTemplate remainder = stack.getItem().getCraftingRemainder();
                remainders.set(slot, remainder != null ? remainder.create() : ItemStack.EMPTY);
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

    /**
     * ShapelessRecipe narrows this to RecipeSerializer&lt;ShapelessRecipe&gt;, which is
     * invariant, so the override cannot return our own type. The cast is safe: generics
     * are erased here and the serializer really does produce a FilletRecipe, which is a
     * ShapelessRecipe.
     */
    @Override
    @SuppressWarnings("unchecked")
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) (RecipeSerializer<?>) ModRecipes.FILLET;
    }
}
