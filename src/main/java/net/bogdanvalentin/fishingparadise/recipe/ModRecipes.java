package net.bogdanvalentin.fishingparadise.recipe;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipes {
    public static final RecipeSerializer<FilletRecipe> FILLET =
            register("fillet", new RecipeSerializer<>(FilletRecipe.MAP_CODEC, FilletRecipe.STREAM_CODEC));

    /** RecipeSerializer became a record in 26.2, so serializers are built rather than implemented. */
    private static <T extends Recipe<?>> RecipeSerializer<T> register(String name, RecipeSerializer<T> serializer) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
                Identifier.fromNamespaceAndPath(FishingParadise.MOD_ID, name), serializer);
        return serializer;
    }

    public static void registerRecipes() {
        FishingParadise.LOGGER.info("Registering Recipe Serializers for " + FishingParadise.MOD_ID);
    }
}
