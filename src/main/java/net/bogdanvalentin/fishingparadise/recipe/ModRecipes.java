package net.bogdanvalentin.fishingparadise.recipe;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<FilletRecipe> FILLET = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath(FishingParadise.MOD_ID, "fillet"), new FilletRecipe.Serializer());

    public static void registerRecipes() {
        FishingParadise.LOGGER.info("Registering Recipe Serializers for " + FishingParadise.MOD_ID);
    }
}
