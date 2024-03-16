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
    public static final Item RAW_ANCHOVETA = registerItem("raw_anchoveta", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_ANCHOVETA)));
    public static final Item COOKED_ANCHOVETA = registerItem("cooked_anchoveta", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_ANCHOVETA)));
    public static final Item RAW_CARP = registerItem("raw_carp", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_CARP)));
    public static final Item COOKED_CARP = registerItem("cooked_carp", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_CARP)));
    public static final Item RAW_HERING= registerItem("raw_hering", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_HERING)));
    public static final Item COOKED_HERING = registerItem("cooked_hering", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_HERING)));
    public static final Item RAW_SHRIMP = registerItem("raw_shrimp", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_SHRIMP)));
    public static final Item COOKED_SHRIMP = registerItem("cooked_shrimp", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_SHRIMP)));
    public static final Item RAW_TILAPIA = registerItem("raw_tilapia", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_TILAPIA)));
    public static final Item COOKED_TILAPIA = registerItem("cooked_tilapia", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_TILAPIA)));
    public static final Item RAW_TUNA = registerItem("raw_tuna", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_TUNA)));
    public static final Item COOKED_TUNA = registerItem("cooked_tuna", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_TUNA)));

    public static final Item RAW_CRAB = registerItem("raw_crab", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_CRAB)));
    public static final Item STARFISH = registerItem("starfish", new Item(new FabricItemSettings().food(ModFoodComponents.STARFISH)));

    /** LEGENDARY FISH **/
    public static final Item ANGLERFISH = registerItem("anglerfish", new Item(new FabricItemSettings().food(ModFoodComponents.ANGLERFISH).rarity(Rarity.RARE).maxCount(16)));
    public static final Item OCTOPUS = registerItem("octopus", new Item(new FabricItemSettings().food(ModFoodComponents.OCTOPUS).rarity(Rarity.RARE).maxCount(16)));
    public static final Item SERPENT = registerItem("serpent", new Item(new FabricItemSettings().food(ModFoodComponents.SERPENT).rarity(Rarity.RARE).maxCount(16)));

    /** FOODS **/
    public static final Item FISH_FILLETS = registerItem("fish_fillets", new Item(new FabricItemSettings().food(ModFoodComponents.FISH_FILLETS)));
    public static final Item COOKED_FISH_FILLETS = registerItem("cooked_fish_fillets", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_FISH_FILLETS)));
    public static final Item FISH_AND_CHIPS = registerItem("fish_and_chips", new Item(new FabricItemSettings().food(ModFoodComponents.FISH_AND_CHIPS)));
    public static final Item FISH_PIE = registerItem("fish_pie", new Item(new FabricItemSettings().food(ModFoodComponents.FISH_PIE)));
    public static final Item FISH_STEW = registerItem("fish_stew", new Item(new FabricItemSettings().food(ModFoodComponents.FISH_STEW)));
    public static final Item SEA_FOOD = registerItem("sea_food", new Item(new FabricItemSettings().food(ModFoodComponents.SEA_FOOD)));
    public static final Item SUSHI = registerItem("sushi", new Item(new FabricItemSettings().food(ModFoodComponents.SUSHI)));

    /** FISHING RODS
     maxDamage means max durability **/
    public static final Item WOODEN_FISHING_ROD = registerItem("wooden_fishing_rod", new WoodenFishingRodItem(new Item.Settings().maxDamage(64)));
    public static final Item METAL_FISHING_ROD = registerItem("metal_fishing_rod", new MetalFishingRodItem(new Item.Settings().maxDamage(128)));
    public static final Item NETHERITE_FISHING_ROD = registerItem("netherite_fishing_rod", new NetheriteFishingRodItem(new Item.Settings().maxDamage(256)));


    private static void addItemsToFoodItemGroup(FabricItemGroupEntries entries) {
        entries.add(RAW_ANCHOVETA);
        entries.add(COOKED_ANCHOVETA);
        entries.add(RAW_CARP);
        entries.add(COOKED_CARP);
        entries.add(RAW_HERING);
        entries.add(COOKED_HERING);
        entries.add(RAW_SHRIMP);
        entries.add(COOKED_SHRIMP);
        entries.add(RAW_TILAPIA);
        entries.add(COOKED_TILAPIA);
        entries.add(RAW_TUNA);
        entries.add(COOKED_TUNA);

        entries.add(RAW_CRAB);
        entries.add(STARFISH);

        entries.add(FISH_FILLETS);
        entries.add(COOKED_FISH_FILLETS);
        entries.add(FISH_AND_CHIPS);
        entries.add(FISH_PIE);
        entries.add(FISH_STEW);
        entries.add(SEA_FOOD);
        entries.add(SUSHI);

        entries.add(ANGLERFISH);
        entries.add(OCTOPUS);
        entries.add(SERPENT);
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
