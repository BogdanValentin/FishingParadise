package net.bogdanvalentin.fishingparadise;

import net.bogdanvalentin.fishingparadise.item.ModItemGroups;
import net.bogdanvalentin.fishingparadise.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishingParadise implements ModInitializer {
	public static final String MOD_ID = "fishingparadise";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}