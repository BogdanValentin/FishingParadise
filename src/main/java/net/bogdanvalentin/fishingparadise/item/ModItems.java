package net.bogdanvalentin.fishingparadise.item;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.bogdanvalentin.fishingparadise.item.custom.ModFishingRodItem;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Rarity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.Function;

public class ModItems {
    /** FISH **/
    public static final Item RAW_ANCHOVETA = registerItem("raw_anchoveta", new Item.Properties().food(ModFoodComponents.RAW_ANCHOVETA));
    public static final Item COOKED_ANCHOVETA = registerItem("cooked_anchoveta", new Item.Properties().food(ModFoodComponents.COOKED_ANCHOVETA));
    public static final Item RAW_CARP = registerItem("raw_carp", new Item.Properties().food(ModFoodComponents.RAW_CARP));
    public static final Item COOKED_CARP = registerItem("cooked_carp", new Item.Properties().food(ModFoodComponents.COOKED_CARP));
    public static final Item RAW_HERING= registerItem("raw_hering", new Item.Properties().food(ModFoodComponents.RAW_HERING));
    public static final Item COOKED_HERING = registerItem("cooked_hering", new Item.Properties().food(ModFoodComponents.COOKED_HERING));
    public static final Item RAW_SHRIMP = registerItem("raw_shrimp", new Item.Properties().food(ModFoodComponents.RAW_SHRIMP));
    public static final Item COOKED_SHRIMP = registerItem("cooked_shrimp", new Item.Properties().food(ModFoodComponents.COOKED_SHRIMP));
    public static final Item RAW_TILAPIA = registerItem("raw_tilapia", new Item.Properties().food(ModFoodComponents.RAW_TILAPIA));
    public static final Item COOKED_TILAPIA = registerItem("cooked_tilapia", new Item.Properties().food(ModFoodComponents.COOKED_TILAPIA));
    public static final Item RAW_TUNA = registerItem("raw_tuna", new Item.Properties().food(ModFoodComponents.RAW_TUNA));
    public static final Item COOKED_TUNA = registerItem("cooked_tuna", new Item.Properties().food(ModFoodComponents.COOKED_TUNA));

    public static final Item RAW_CRAB = registerItem("raw_crab", new Item.Properties().food(ModFoodComponents.RAW_CRAB));
    public static final Item STARFISH = registerItem("starfish", new Item.Properties().food(ModFoodComponents.STARFISH));

    /** LEGENDARY FISH **/
    public static final Item ANGLERFISH = registerItem("anglerfish", new Item.Properties().food(ModFoodComponents.ANGLERFISH, ModFoodComponents.ANGLERFISH_EFFECTS).rarity(Rarity.RARE).stacksTo(16));
    public static final Item OCTOPUS = registerItem("octopus", new Item.Properties().food(ModFoodComponents.OCTOPUS, ModFoodComponents.OCTOPUS_EFFECTS).rarity(Rarity.RARE).stacksTo(16));
    public static final Item SERPENT = registerItem("serpent", new Item.Properties().food(ModFoodComponents.SERPENT, ModFoodComponents.SERPENT_EFFECTS).rarity(Rarity.RARE).stacksTo(16));

    /** FOODS **/
    public static final Item FISH_FILLETS = registerItem("fish_fillets", new Item.Properties().food(ModFoodComponents.FISH_FILLETS));
    public static final Item COOKED_FISH_FILLETS = registerItem("cooked_fish_fillets", new Item.Properties().food(ModFoodComponents.COOKED_FISH_FILLETS));
    public static final Item FISH_AND_CHIPS = registerItem("fish_and_chips", new Item.Properties().food(ModFoodComponents.FISH_AND_CHIPS));
    public static final Item FISH_PIE = registerItem("fish_pie", new Item.Properties().food(ModFoodComponents.FISH_PIE));
    public static final Item FISH_STEW = registerItem("fish_stew", new Item.Properties().food(ModFoodComponents.FISH_STEW));
    public static final Item SEA_FOOD = registerItem("sea_food", new Item.Properties().food(ModFoodComponents.SEA_FOOD));
    public static final Item SUSHI = registerItem("sushi", new Item.Properties().food(ModFoodComponents.SUSHI));

    /** FISHING RODS
     maxDamage means max durability **/
    public static final Item WOODEN_FISHING_ROD = registerRod("wooden_fishing_rod", 64);
    public static final Item BAMBOO_FISHING_ROD = registerRod("bamboo_fishing_rod", 64);
    public static final Item METAL_FISHING_ROD = registerRod("metal_fishing_rod", 128);
    public static final Item NETHERITE_FISHING_ROD = registerRod("netherite_fishing_rod", 256);

    private static void addItemsToFoodItemGroup(FabricCreativeModeTabOutput entries) {
        entries.accept(RAW_ANCHOVETA);
        entries.accept(COOKED_ANCHOVETA);
        entries.accept(RAW_CARP);
        entries.accept(COOKED_CARP);
        entries.accept(RAW_HERING);
        entries.accept(COOKED_HERING);
        entries.accept(RAW_SHRIMP);
        entries.accept(COOKED_SHRIMP);
        entries.accept(RAW_TILAPIA);
        entries.accept(COOKED_TILAPIA);
        entries.accept(RAW_TUNA);
        entries.accept(COOKED_TUNA);

        entries.accept(RAW_CRAB);
        entries.accept(STARFISH);

        entries.accept(FISH_FILLETS);
        entries.accept(COOKED_FISH_FILLETS);
        entries.accept(FISH_AND_CHIPS);
        entries.accept(FISH_PIE);
        entries.accept(FISH_STEW);
        entries.accept(SEA_FOOD);
        entries.accept(SUSHI);

        entries.accept(ANGLERFISH);
        entries.accept(OCTOPUS);
        entries.accept(SERPENT);
    }
    private static void addItemsToToolsItemGroup(FabricCreativeModeTabOutput entries) {
        entries.accept(BAMBOO_FISHING_ROD);
        entries.accept(WOODEN_FISHING_ROD);
        entries.accept(METAL_FISHING_ROD);
        entries.accept(NETHERITE_FISHING_ROD);
    }
    private static Item registerRod(String name, int durability) {
        ResourceKey<LootTable> lootTable = ResourceKey.create(Registries.LOOT_TABLE, id("gameplay/" + name));
        return registerItem(name, properties -> new ModFishingRodItem(properties, lootTable),
                new Item.Properties().durability(durability));
    }

    private static Item registerItem(String name, Item.Properties properties) {
        return registerItem(name, Item::new, properties);
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(properties.setId(key)));
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(FishingParadise.MOD_ID, path);
    }
    public static void registerModItems() {
        FishingParadise.LOGGER.info("Registering Mod Items for " + FishingParadise.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(ModItems::addItemsToToolsItemGroup);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(ModItems::addItemsToFoodItemGroup);
    }
}
