package net.bogdanvalentin.fishingparadise.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.bogdanvalentin.fishingparadise.item.ModItems;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberRendererMixin {
    @ModifyExpressionValue(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean addCustomFishingRodToFishingRodCheck(boolean original, @Local ItemStack itemStack) {
        return original || itemStack.isOf(ModItems.WOODEN_FISHING_ROD)
                        || itemStack.isOf(ModItems.METAL_FISHING_ROD)
                        || itemStack.isOf(ModItems.NETHERITE_FISHING_ROD);
    }
}
