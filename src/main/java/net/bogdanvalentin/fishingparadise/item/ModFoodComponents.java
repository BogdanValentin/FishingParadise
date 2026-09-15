package net.bogdanvalentin.fishingparadise.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

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

    // 20 ticks = 1 second
    public static final FoodProperties ANGLERFISH = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).alwaysEdible().build();
    public static final Consumable ANGLERFISH_EFFECTS = legendary(
            new MobEffectInstance(MobEffects.NAUSEA, 200, 0),
            new MobEffectInstance(MobEffects.BLINDNESS, 100, 0),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0));

    public static final FoodProperties OCTOPUS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).alwaysEdible().build();
    public static final Consumable OCTOPUS_EFFECTS = legendary(
            new MobEffectInstance(MobEffects.NAUSEA, 200, 0),
            new MobEffectInstance(MobEffects.DARKNESS, 100, 0),
            new MobEffectInstance(MobEffects.STRENGTH, 6000, 0));

    public static final FoodProperties SERPENT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).alwaysEdible().build();
    public static final Consumable SERPENT_EFFECTS = legendary(
            new MobEffectInstance(MobEffects.NAUSEA, 200, 0),
            new MobEffectInstance(MobEffects.POISON, 100, 0),
            new MobEffectInstance(MobEffects.SPEED, 6000, 0));

    private static Consumable legendary(MobEffectInstance... effects) {
        return Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(effects)))
                .build();
    }
}
