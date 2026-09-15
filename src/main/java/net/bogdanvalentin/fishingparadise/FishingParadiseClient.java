package net.bogdanvalentin.fishingparadise;

import net.bogdanvalentin.fishingparadise.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;

public class FishingParadiseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Item rod : new Item[]{ModItems.BAMBOO_FISHING_ROD, ModItems.WOODEN_FISHING_ROD,
                                   ModItems.METAL_FISHING_ROD, ModItems.NETHERITE_FISHING_ROD}) {
            ItemProperties.register(rod, ResourceLocation.withDefaultNamespace("cast"), castProperty());
        }
    }

    private static ClampedItemPropertyFunction castProperty() {
        return (stack, level, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            boolean mainHand = entity.getMainHandItem() == stack;
            boolean offHand = entity.getOffhandItem() == stack;
            if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                offHand = false;
            }
            return (mainHand || offHand) && entity instanceof Player player && player.fishing != null ? 1.0F : 0.0F;
        };
    }
}
