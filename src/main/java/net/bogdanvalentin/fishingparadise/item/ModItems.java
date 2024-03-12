package net.bogdanvalentin.fishingparadise.item;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.bogdanvalentin.fishingparadise.item.custom.MetalFishingRodItem;
import net.bogdanvalentin.fishingparadise.item.custom.NetheriteFishingRodItem;
import net.bogdanvalentin.fishingparadise.item.custom.WoodenFishingRodItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

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
    public static final Item RAW_CRAB = registerItem("raw_crab", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_CRAB)));
    public static final Item STARFISH = registerItem("starfish", new Item(new FabricItemSettings().food(ModFoodComponents.STARFISH)));

    /** LEGENDARY FISH **/
    public static final Item RAW_OCTOPUS = registerItem("raw_octopus", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_OCTOPUS).rarity(Rarity.RARE)));

    /** FOODS **/
    public static final Item SEA_FOOD = registerItem("sea_food", new Item(new FabricItemSettings().food(ModFoodComponents.SEA_FOOD)));


    /** FISHING RODS
     maxDamage means max durability **/
    public static final Item WOODEN_FISHING_ROD = registerItem("wooden_fishing_rod", new WoodenFishingRodItem(new Item.Settings().maxDamage(64)));
    public static final Item METAL_FISHING_ROD = registerItem("metal_fishing_rod", new MetalFishingRodItem(new Item.Settings().maxDamage(128)));
    public static final Item NETHERITE_FISHING_ROD = registerItem("netherite_fishing_rod", new NetheriteFishingRodItem(new Item.Settings().maxDamage(256)));


    private static void addItemsToFoodItemGroup(FabricItemGroupEntries entries) {
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
        entries.add(RAW_CRAB);
        entries.add(STARFISH);
        entries.add(RAW_OCTOPUS);
        entries.add(SEA_FOOD);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(WOODEN_FISHING_ROD);
        entries.add(METAL_FISHING_ROD);
        entries.add(NETHERITE_FISHING_ROD);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FishingParadise.MOD_ID, name), item);
    }
    public static void registerModItems() {
        FishingParadise.LOGGER.info("Registering Mod Items for " + FishingParadise.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodItemGroup);
    }
}
