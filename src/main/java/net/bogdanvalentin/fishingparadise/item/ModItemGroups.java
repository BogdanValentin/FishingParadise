package net.bogdanvalentin.fishingparadise.item;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ModItemGroups {
    public static final CreativeModeTab FISH_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(FishingParadise.MOD_ID, "fish"),
            FabricCreativeModeTab.builder().title(Component.translatable("itemgroup.fish"))
                    .icon(() -> new ItemStack(ModItems.RAW_SHRIMP)).displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.BAMBOO_FISHING_ROD);
                        entries.accept(ModItems.WOODEN_FISHING_ROD);
                        entries.accept(ModItems.METAL_FISHING_ROD);
                        entries.accept(ModItems.NETHERITE_FISHING_ROD);

                        entries.accept(ModItems.RAW_ANCHOVETA);
                        entries.accept(ModItems.COOKED_ANCHOVETA);
                        entries.accept(ModItems.RAW_CARP);
                        entries.accept(ModItems.COOKED_CARP);
                        entries.accept(ModItems.RAW_HERING);
                        entries.accept(ModItems.COOKED_HERING);
                        entries.accept(ModItems.RAW_SHRIMP);
                        entries.accept(ModItems.COOKED_SHRIMP);
                        entries.accept(ModItems.RAW_TILAPIA);
                        entries.accept(ModItems.COOKED_TILAPIA);
                        entries.accept(ModItems.RAW_TUNA);
                        entries.accept(ModItems.COOKED_TUNA);

                        entries.accept(ModItems.RAW_CRAB);
                        entries.accept(ModItems.STARFISH);

                        entries.accept(ModItems.FISH_FILLETS);
                        entries.accept(ModItems.COOKED_FISH_FILLETS);
                        entries.accept(ModItems.FISH_AND_CHIPS);
                        entries.accept(ModItems.FISH_PIE);
                        entries.accept(ModItems.FISH_STEW);
                        entries.accept(ModItems.SEA_FOOD);
                        entries.accept(ModItems.SUSHI);

                        entries.accept(ModItems.ANGLERFISH);
                        entries.accept(ModItems.OCTOPUS);
                        entries.accept(ModItems.SERPENT);
                    }).build());
    public static void registerItemGroups() {
        FishingParadise.LOGGER.info("Registering Item Groups for " + FishingParadise.MOD_ID);
    }
}
