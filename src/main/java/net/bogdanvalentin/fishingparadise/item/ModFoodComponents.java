package net.bogdanvalentin.fishingparadise.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent RAW_ANCHOVETA = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_ANCHOVETA = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f).meat().build();
    public static final FoodComponent RAW_CARP = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_CARP = new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).meat().build();
    public static final FoodComponent RAW_HERING = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_HERING = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).meat().build();
    public static final FoodComponent RAW_SHRIMP = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_SHRIMP = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).meat().build();
    public static final FoodComponent RAW_TILAPIA = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_TILAPIA = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).meat().build();
    public static final FoodComponent RAW_TUNA = new FoodComponent.Builder().hunger(3).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_TUNA = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).meat().build();

    public static final FoodComponent RAW_CRAB = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent STARFISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();

    // 20 ticks = 1 second
    public static final FoodComponent FISH_FILLETS = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_FISH_FILLETS = new FoodComponent.Builder().hunger(4).saturationModifier(0.6f).meat().build();
    public static final FoodComponent FISH_AND_CHIPS = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).build();
    public static final FoodComponent FISH_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).build();
    public static final FoodComponent FISH_STEW = new FoodComponent.Builder().hunger(10).saturationModifier(0.9f).build();
    public static final FoodComponent SEA_FOOD = new FoodComponent.Builder().hunger(10).saturationModifier(0.9f).build();
    public static final FoodComponent SUSHI = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).build();

    public static final FoodComponent ANGLERFISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 6000), 1f).alwaysEdible().build();
    public static final FoodComponent OCTOPUS = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000), 1f).alwaysEdible().build();
    public static final FoodComponent SERPENT = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6000), 1f).alwaysEdible().build();
}
