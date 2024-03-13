package net.bogdanvalentin.fishingparadise.item;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FISH_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(FishingParadise.MOD_ID, "fish"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fish"))
                    .icon(() -> new ItemStack(ModItems.RAW_SHRIMP)).entries((displayContext, entries) -> {
                        entries.add(ModItems.WOODEN_FISHING_ROD);
                        entries.add(ModItems.METAL_FISHING_ROD);
                        entries.add(ModItems.NETHERITE_FISHING_ROD);

                        entries.add(ModItems.RAW_ANCHOVETA);
                        entries.add(ModItems.COOKED_ANCHOVETA);
                        entries.add(ModItems.RAW_CARP);
                        entries.add(ModItems.COOKED_CARP);
                        entries.add(ModItems.RAW_HERING);
                        entries.add(ModItems.COOKED_HERING);
                        entries.add(ModItems.RAW_SHRIMP);
                        entries.add(ModItems.COOKED_SHRIMP);
                        entries.add(ModItems.RAW_TILAPIA);
                        entries.add(ModItems.COOKED_TILAPIA);
                        entries.add(ModItems.RAW_TUNA);
                        entries.add(ModItems.COOKED_TUNA);

                        entries.add(ModItems.RAW_CRAB);
                        entries.add(ModItems.STARFISH);

                        entries.add(ModItems.FISH_FILLETS);
                        entries.add(ModItems.COOKED_FISH_FILLETS);
                        entries.add(ModItems.FISH_AND_CHIPS);
                        entries.add(ModItems.FISH_PIE);
                        entries.add(ModItems.FISH_STEW);
                        entries.add(ModItems.SEA_FOOD);
                        entries.add(ModItems.SUSHI);

                        entries.add(ModItems.ANGLERFISH);
                        entries.add(ModItems.OCTOPUS);
                        entries.add(ModItems.SERPENT);
                    }).build());
    public static void registerItemGroups() {
        FishingParadise.LOGGER.info("Registering Item Groups for " + FishingParadise.MOD_ID);
    }
}
