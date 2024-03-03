package net.bogdanvalentin.fishingparadise.item;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.bogdanvalentin.fishingparadise.item.custom.WoodenFishingRodItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    /** FISH **/
    public static final Item RAW_CARP = registerItem("raw_carp", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_CARP)));
    public static final Item COOKED_CARP = registerItem("cooked_carp", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_CARP)));
    public static final Item RAW_ANCHOVETA = registerItem("raw_anchoveta", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_ANCHOVETA)));
    public static final Item COOKED_ANCHOVETA = registerItem("cooked_anchoveta", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_ANCHOVETA)));
    public static final Item RAW_TILAPIA = registerItem("raw_tilapia", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_TILAPIA)));
    public static final Item COOKED_TILAPIA = registerItem("cooked_tilapia", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_TILAPIA)));
    public static final Item RAW_SHRIMP = registerItem("raw_shrimp", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_SHRIMP)));
    public static final Item COOKED_SHRIMP = registerItem("cooked_shrimp", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_SHRIMP)));
    public static final Item RAW_TUNA = registerItem("raw_tuna", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_TUNA)));
    public static final Item COOKED_TUNA = registerItem("cooked_tuna", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_TUNA)));

    /** FISHING RODS
     maxDamage means max durability **/
    public static final Item WOODEN_FISHING_ROD = registerItem("wooden_fishing_rod", new WoodenFishingRodItem(new FabricItemSettings().maxDamage(64)));


    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(RAW_CARP);
        entries.add(COOKED_CARP);
        entries.add(RAW_ANCHOVETA);
        entries.add(COOKED_ANCHOVETA);
        entries.add(RAW_TILAPIA);
        entries.add(COOKED_TILAPIA);
        entries.add(RAW_SHRIMP);
        entries.add(COOKED_SHRIMP);
        entries.add(RAW_TUNA);
        entries.add(COOKED_TUNA);

        entries.add(WOODEN_FISHING_ROD);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FishingParadise.MOD_ID, name), item);
    }
    public static void registerModItems() {
        FishingParadise.LOGGER.info("Registering Mod Items for " + FishingParadise.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
