package net.bogdanvalentin.fishingparadise.mixin;

import net.bogdanvalentin.fishingparadise.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        ItemStack offHandStack = playerEntity.getOffHandStack();

        boolean mainHandHasRod = mainHandStack.isOf(Items.FISHING_ROD)
                || mainHandStack.isOf(ModItems.BAMBOO_FISHING_ROD)
                || mainHandStack.isOf(ModItems.WOODEN_FISHING_ROD)
                || mainHandStack.isOf(ModItems.METAL_FISHING_ROD)
                || mainHandStack.isOf(ModItems.NETHERITE_FISHING_ROD);
        boolean offHandHasRod = offHandStack.isOf(Items.FISHING_ROD)
                || offHandStack.isOf(ModItems.BAMBOO_FISHING_ROD)
                || offHandStack.isOf(ModItems.WOODEN_FISHING_ROD)
                || offHandStack.isOf(ModItems.METAL_FISHING_ROD)
                || offHandStack.isOf(ModItems.NETHERITE_FISHING_ROD);

        if (!playerEntity.isRemoved() && playerEntity.isAlive() && (mainHandHasRod || offHandHasRod) && this.squaredDistanceTo(playerEntity) <= 1024.0D) {
            cir.setReturnValue(false);
        }
    }
}
