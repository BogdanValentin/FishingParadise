package net.bogdanvalentin.fishingparadise.mixin;

import net.bogdanvalentin.fishingparadise.util.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberValidityMixin extends Entity {
    @Shadow
    public abstract PlayerEntity getPlayerOwner();

    private FishingBobberValidityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "removeIfInvalid",
            at = @At("HEAD"),
            cancellable = true
    )
    private void removeIfInvalid(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        boolean holdingRod = playerEntity.getMainHandStack().isIn(ModTags.FISHING_RODS)
                || playerEntity.getOffHandStack().isIn(ModTags.FISHING_RODS);

        if (!playerEntity.isRemoved() && playerEntity.isAlive() && holdingRod && this.squaredDistanceTo(playerEntity) <= 1024.0D) {
            cir.setReturnValue(false);
        }
    }
}
