package net.bogdanvalentin.fishingparadise.mixin;

import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FishingHook.class)
public interface FishingBobberAccessor {
    @Accessor("nibble")
    int getNibble();
}
