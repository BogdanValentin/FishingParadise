package net.bogdanvalentin.fishingparadise;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.bogdanvalentin.fishingparadise.item.ModItems;

import static net.bogdanvalentin.fishingparadise.item.ModItems.WOODEN_FISHING_ROD;

public class FishingParadiseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register( WOODEN_FISHING_ROD, new Identifier("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) return 0.0f;
            if (itemStack.getItem() != WOODEN_FISHING_ROD) return 0.0f;

            NbtCompound nbt = itemStack.getOrCreateNbt();

            if (nbt.contains("cast")) {
                return nbt.getFloat("cast");
            }

            return 1.0f;
        });
    }
}
