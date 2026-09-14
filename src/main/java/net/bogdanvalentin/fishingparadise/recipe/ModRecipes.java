package net.bogdanvalentin.fishingparadise.recipe;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<FilletRecipe> FILLET = Registry.register(Registries.RECIPE_SERIALIZER,
            new Identifier(FishingParadise.MOD_ID, "fillet"), new FilletRecipe.Serializer());

    public static void registerRecipes() {
        FishingParadise.LOGGER.info("Registering Recipe Serializers for " + FishingParadise.MOD_ID);
    }
}
