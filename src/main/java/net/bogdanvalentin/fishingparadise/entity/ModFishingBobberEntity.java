package net.bogdanvalentin.fishingparadise.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.bogdanvalentin.fishingparadise.mixin.FishingBobberAccessor;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Collections;

/**
 * The bobber for every modded rod. What a rod catches lives in its loot table,
 * so adding or rebalancing a rod is a JSON change rather than a Java one.
 *
 * Note this rides on vanilla's EntityType.FISHING_BOBBER, so clients only ever
 * see a plain bobber and nothing needs registering.
 */
public class ModFishingBobberEntity extends FishingBobberEntity {
    private final Item rodItem;
    private final Identifier lootTableId;
    private final int luckOfTheSeaLevel;

    public ModFishingBobberEntity(PlayerEntity thrower, World world, int luckOfTheSeaLevel, int lureLevel,
                                  Item rodItem, Identifier lootTableId) {
        super(thrower, world, luckOfTheSeaLevel, lureLevel);
        this.rodItem = rodItem;
        this.lootTableId = lootTableId;
        this.luckOfTheSeaLevel = luckOfTheSeaLevel;
    }

    private boolean removeIfInvalid(PlayerEntity player) {
        boolean mainHand = player.getMainHandStack().isOf(this.rodItem);
        boolean offHand = player.getOffHandStack().isOf(this.rodItem);
        if (player.isRemoved() || !player.isAlive() || (!mainHand && !offHand) || this.squaredDistanceTo(player) > 1024.0) {
            this.discard();
            return true;
        }
        return false;
    }

    @Override
    public int use(ItemStack usedItem) {
        PlayerEntity playerEntity = this.getPlayerOwner();
        if (this.getWorld().isClient || playerEntity == null || this.removeIfInvalid(playerEntity)) {
            return 0;
        }

        int i = 0;
        if (this.getHookedEntity() != null) {
            this.pullHookedEntity(this.getHookedEntity());
            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) playerEntity, usedItem, this, Collections.emptyList());
            this.getWorld().sendEntityStatus(this, EntityStatuses.PULL_HOOKED_ENTITY);
            i = this.getHookedEntity() instanceof ItemEntity ? 3 : 5;
        } else if (((FishingBobberAccessor) this).getHookCountdown() > 0) {
            ObjectArrayList<ItemStack> loot = this.generateLoot(playerEntity, usedItem);
            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) playerEntity, usedItem, this, loot);
            for (ItemStack itemStack : loot) {
                this.dropCatch(playerEntity, itemStack);
            }
            i = 1;
        }

        if (this.isOnGround()) {
            i = 2;
        }
        this.discard();
        return i;
    }

    private ObjectArrayList<ItemStack> generateLoot(PlayerEntity playerEntity, ItemStack usedItem) {
        LootTable lootTable = this.getWorld().getServer().getLootManager().getLootTable(this.lootTableId);
        LootContextParameterSet parameters = new LootContextParameterSet.Builder((ServerWorld) this.getWorld())
                .add(LootContextParameters.ORIGIN, this.getPos())
                .add(LootContextParameters.TOOL, usedItem)
                .add(LootContextParameters.THIS_ENTITY, this)
                .luck((float) this.luckOfTheSeaLevel + playerEntity.getLuck())
                .build(LootContextTypes.FISHING);
        return lootTable.generateLoot(parameters);
    }

    private void dropCatch(PlayerEntity playerEntity, ItemStack itemStack) {
        ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), itemStack);
        double d = playerEntity.getX() - this.getX();
        double e = playerEntity.getY() - this.getY();
        double f = playerEntity.getZ() - this.getZ();
        itemEntity.setVelocity(d * 0.1, e * 0.1 + Math.sqrt(Math.sqrt(d * d + e * e + f * f)) * 0.08, f * 0.1);
        this.getWorld().spawnEntity(itemEntity);
        playerEntity.getWorld().spawnEntity(new ExperienceOrbEntity(playerEntity.getWorld(), playerEntity.getX(),
                playerEntity.getY() + 0.5, playerEntity.getZ() + 0.5, this.random.nextInt(6) + 1));
        if (itemStack.isIn(ItemTags.FISHES)) {
            playerEntity.increaseStat(Stats.FISH_CAUGHT, 1);
        }
    }
}
