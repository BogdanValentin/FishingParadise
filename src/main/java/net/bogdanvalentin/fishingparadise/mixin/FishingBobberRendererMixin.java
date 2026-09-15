package net.bogdanvalentin.fishingparadise.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.bogdanvalentin.fishingparadise.util.ModTags;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHookRenderer.class)
public class FishingBobberRendererMixin {
    @ModifyExpressionValue(
            method = "getPlayerHandPos(Lnet/minecraft/world/entity/player/Player;FF)Lnet/minecraft/world/phys/Vec3;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean fishingparadise$treatModdedRodsAsRods(boolean original, @Local ItemStack itemStack) {
        return original || itemStack.is(ModTags.FISHING_RODS);
    }
}
