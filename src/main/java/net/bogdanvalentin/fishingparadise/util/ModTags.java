package net.bogdanvalentin.fishingparadise.util;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModTags {
    public static final TagKey<Item> FISHING_RODS =
            TagKey.of(RegistryKeys.ITEM, new Identifier(FishingParadise.MOD_ID, "fishing_rods"));

    private ModTags() {
    }
}
