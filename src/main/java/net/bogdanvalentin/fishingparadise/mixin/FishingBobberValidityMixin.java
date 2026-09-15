package net.bogdanvalentin.fishingparadise.mixin;

import net.bogdanvalentin.fishingparadise.util.ModTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public abstract class FishingBobberValidityMixin extends Entity {
    private FishingBobberValidityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "shouldStopFishing",
            at = @At("HEAD"),
            cancellable = true
    )
    private void keepModdedRodsFishing(Player player, CallbackInfoReturnable<Boolean> cir) {
        boolean holdingRod = player.getMainHandItem().is(ModTags.FISHING_RODS)
                || player.getOffhandItem().is(ModTags.FISHING_RODS);
        if (!player.isRemoved() && player.isAlive() && holdingRod && this.distanceToSqr(player) <= 1024.0) {
            cir.setReturnValue(false);
        }
    }
}
