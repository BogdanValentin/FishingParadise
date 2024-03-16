package net.bogdanvalentin.fishingparadise.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.bogdanvalentin.fishingparadise.item.ModItems;
import net.bogdanvalentin.fishingparadise.mixin.FishingBobberAccessor;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

import java.util.Collections;

public class BambooFishingBobberEntity extends FishingBobberEntity {
    public BambooFishingBobberEntity(EntityType<? extends FishingBobberEntity> type, World world, int luckOfTheSeaLevel, int lureLevel) {
        super(type, world, luckOfTheSeaLevel, lureLevel);
    }

    public BambooFishingBobberEntity(EntityType<? extends FishingBobberEntity> entityType, World world) {
        super(entityType, world);
    }

    public BambooFishingBobberEntity(PlayerEntity thrower, World world, int luckOfTheSeaLevel, int lureLevel) {
        super(thrower, world, luckOfTheSeaLevel, lureLevel);
    }

    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(ModItems.BAMBOO_FISHING_ROD);
        boolean bl2 = itemStack2.isOf(ModItems.BAMBOO_FISHING_ROD);
        if (player.isRemoved() || !player.isAlive() || !bl && !bl2 || this.squaredDistanceTo(player) > 1024.0) {
            this.discard();
            return true;
        }
        return false;
    }

    // Helper class
    private class ItemWithWeight {
        ItemStack itemStack;
        double percent;

        public ItemWithWeight(ItemStack itemStack, double percent) {
            this.itemStack = itemStack;
            this.percent = percent;
        }

        public ItemStack getItem() {
            return itemStack;
        }
    }

    public int use(ItemStack usedItem) {
        PlayerEntity playerEntity = this.getPlayerOwner();
        if (this.getWorld().isClient || playerEntity == null || this.removeIfInvalid(playerEntity)) {
            return 0;
        }
        int i = 0;
        int hookCountdown = ((FishingBobberAccessor) this).getHookCountdown();
        if (this.getHookedEntity() != null) {
            this.pullHookedEntity(this.getHookedEntity());
            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity)playerEntity, usedItem, this, Collections.emptyList());
            this.getWorld().sendEntityStatus(this, EntityStatuses.PULL_HOOKED_ENTITY);
            i = this.getHookedEntity() instanceof ItemEntity ? 3 : 5;
        } else if(hookCountdown > 0) {

            ObjectArrayList<ItemWithWeight> pool = new ObjectArrayList<>();
            pool.add(new ItemWithWeight(new ItemStack(Items.LILY_PAD), 5));
            pool.add(new ItemWithWeight(new ItemStack(Items.LEATHER), 4.7));
            pool.add(new ItemWithWeight(new ItemStack(Items.STICK), 5));
            pool.add(new ItemWithWeight(new ItemStack(Items.STRING), 5));
            pool.add(new ItemWithWeight(new ItemStack(Items.BONE), 5));
            pool.add(new ItemWithWeight(new ItemStack(Items.INK_SAC), 0.2));
            pool.add(new ItemWithWeight(new ItemStack(Items.GLOW_INK_SAC), 0.1));
            // 25 chance junk

            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_TILAPIA), 14));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_CARP), 12));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_ANCHOVETA), 14));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_SHRIMP), 3));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_TUNA), 12));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_CRAB), 5));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.STARFISH), 5));
            pool.add(new ItemWithWeight(new ItemStack(ModItems.RAW_HERING), 10));
            // TODO ADD MORE FISH

            // RANDOM LOOT LOGIC
            double totalWeight = pool.stream().mapToDouble(item -> item.percent).sum();
            double randomValue = random.nextDouble() * totalWeight;
            ObjectArrayList<ItemStack> list = new ObjectArrayList<>();
            double cumulativeWeight = 0;
            for (ItemWithWeight item : pool) {
                cumulativeWeight += item.percent;
                if (randomValue <= cumulativeWeight) {
                    list.add(item.getItem());
                    break;
                }
            }

            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity)playerEntity, usedItem, this, list);
            for (ItemStack itemStack : list) {
                ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), itemStack);
                double d = playerEntity.getX() - this.getX();
                double e = playerEntity.getY() - this.getY();
                double f = playerEntity.getZ() - this.getZ();
                double g = 0.1;
                itemEntity.setVelocity(d * 0.1, e * 0.1 + Math.sqrt(Math.sqrt(d * d + e * e + f * f)) * 0.08, f * 0.1);
                this.getWorld().spawnEntity(itemEntity);
                playerEntity.getWorld().spawnEntity(new ExperienceOrbEntity(playerEntity.getWorld(), playerEntity.getX(), playerEntity.getY() + 0.5, playerEntity.getZ() + 0.5, this.random.nextInt(6) + 1));
                if (!itemStack.isIn(ItemTags.FISHES)) continue;
                playerEntity.increaseStat(Stats.FISH_CAUGHT, 1);
            }
            i = 1;
        }
        if (this.isOnGround()) {
            i = 2;
        }
        this.discard();
        return i;
    }
}
