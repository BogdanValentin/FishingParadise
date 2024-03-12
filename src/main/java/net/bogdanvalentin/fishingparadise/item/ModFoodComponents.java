package net.bogdanvalentin.fishingparadise.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent RAW_CARP = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_CARP = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).meat().build();
    public static final FoodComponent RAW_ANCHOVETA = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_ANCHOVETA = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).meat().build();
    public static final FoodComponent RAW_TILAPIA = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_TILAPIA = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).meat().build();
    public static final FoodComponent RAW_SHRIMP = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).meat().build();
    public static final FoodComponent COOKED_SHRIMP = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).meat().build();
    public static final FoodComponent RAW_TUNA = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).meat().build();
    public static final FoodComponent COOKED_TUNA = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).meat().build();
    public static final FoodComponent RAW_CRAB = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent STARFISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    // 20 ticks = 1 second
    public static final FoodComponent SEA_FOOD = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 2400), 1f).build();
    public static final FoodComponent RAW_OCTOPUS = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000), 1f).alwaysEdible().meat().build();
}
