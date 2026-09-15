package net.bogdanvalentin.fishingparadise.util;

import net.bogdanvalentin.fishingparadise.FishingParadise;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;

public final class ModTags {
    public static final TagKey<Item> FISHING_RODS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FishingParadise.MOD_ID, "fishing_rods"));

    private ModTags() {
    }
}
