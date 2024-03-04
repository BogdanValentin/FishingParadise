package net.bogdanvalentin.fishingparadise.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WoodenFishingRodItem extends FishingRodItem {
    public WoodenFishingRodItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int i;
        if (user.fishHook != null) {
            if (!world.isClient) {
                i = user.fishHook.use(itemStack);
                itemStack.damage(i, user, (p) -> {
                    p.sendToolBreakStatus(hand);
                });
            }

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL,
                    1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL,
                    0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                i = EnchantmentHelper.getLure(itemStack);
                int j = EnchantmentHelper.getLuckOfTheSea(itemStack);
                Entity testEntity = new FishingBobberEntity(user, world, j, i);
                itemStack.getOrCreateNbt().putUuid("bobberUUID", testEntity.getUuid());
                world.spawnEntity(testEntity);
            }
        }

        NbtCompound nbt = itemStack.getOrCreateNbt();
        if (world instanceof ServerWorld serverWorld) {
            if (serverWorld.getEntity(nbt.getUuid("bobberUUID")) != null) {
                nbt.putFloat("cast", 1.0f);
            } else {
                nbt.putFloat("cast", 0.0f);
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = super.getDefaultStack();
        itemStack.getOrCreateNbt().putFloat("cast", 0.0f);
        return itemStack;
    }
}


