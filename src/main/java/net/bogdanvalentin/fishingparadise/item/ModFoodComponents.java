package net.bogdanvalentin.fishingparadise.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * Food values, including the eating effects for the legendary catches.
 *
 * On 1.21.1 effects still live on the FoodProperties itself. They move out to
 * a separate Consumable in 1.21.2, which is why the newer branches differ here.
 */
public class ModFoodComponents {
    public static final FoodProperties RAW_ANCHOVETA = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_ANCHOVETA = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).build();
    public static final FoodProperties RAW_CARP = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_CARP = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).build();
    public static final FoodProperties RAW_HERING = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_HERING = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties RAW_SHRIMP = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_SHRIMP = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build();
    public static final FoodProperties RAW_TILAPIA = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_TILAPIA = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties RAW_TUNA = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_TUNA = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();

    public static final FoodProperties RAW_CRAB = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties STARFISH = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();

    public static final FoodProperties FISH_FILLETS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_FISH_FILLETS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6f).build();
    public static final FoodProperties FISH_AND_CHIPS = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();
    public static final FoodProperties FISH_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();
    public static final FoodProperties FISH_STEW = new FoodProperties.Builder().nutrition(10).saturationModifier(0.9f).build();
    public static final FoodProperties SEA_FOOD = new FoodProperties.Builder().nutrition(10).saturationModifier(0.9f).build();
    public static final FoodProperties SUSHI = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();

    /** LEGENDARY FISH. 20 ticks = 1 second. **/
    public static final FoodProperties ANGLERFISH = legendary(1, 0.5f,
            new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
            new MobEffectInstance(MobEffects.BLINDNESS, 100, 0),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0));

    public static final FoodProperties OCTOPUS = legendary(1, 0.5f,
            new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
            new MobEffectInstance(MobEffects.DARKNESS, 100, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 0));

    public static final FoodProperties SERPENT = legendary(2, 0.3f,
            new MobEffectInstance(MobEffects.CONFUSION, 200, 0),
            new MobEffectInstance(MobEffects.POISON, 100, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 0));

    /** Always edible and every effect always applies. */
    private static FoodProperties legendary(int nutrition, float saturation, MobEffectInstance... effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        for (MobEffectInstance effect : effects) {
            builder.effect(effect, 1.0f);
        }
        return builder.alwaysEdible().build();
    }
}
