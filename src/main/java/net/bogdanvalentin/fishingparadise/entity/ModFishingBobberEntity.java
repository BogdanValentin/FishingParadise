package net.bogdanvalentin.fishingparadise.entity;

import net.bogdanvalentin.fishingparadise.mixin.FishingBobberAccessor;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Collections;
import java.util.List;

public class ModFishingBobberEntity extends FishingHook {
    private final Item rodItem;
    private final ResourceKey<LootTable> lootTable;
    private final int luckBonus;

    public ModFishingBobberEntity(Player thrower, Level level, int luckBonus, int lureTicks,
                                  Item rodItem, ResourceKey<LootTable> lootTable) {
        super(thrower, level, luckBonus, lureTicks);
        this.rodItem = rodItem;
        this.lootTable = lootTable;
        this.luckBonus = luckBonus;
    }

    private boolean shouldStopFishing(Player player) {
        boolean mainHand = player.getMainHandItem().is(this.rodItem);
        boolean offHand = player.getOffhandItem().is(this.rodItem);
        if (player.isRemoved() || !player.isAlive() || (!mainHand && !offHand) || this.distanceToSqr(player) > 1024.0) {
            this.discard();
            return true;
        }
        return false;
    }

    @Override
    public int retrieve(ItemStack usedItem) {
        Player player = this.getPlayerOwner();
        if (this.level().isClientSide() || player == null || this.shouldStopFishing(player)) {
            return 0;
        }

        int i = 0;
        if (this.getHookedIn() != null) {
            this.pullEntity(this.getHookedIn());
            CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) player, usedItem, this, Collections.emptyList());
            this.level().broadcastEntityEvent(this, EntityEvent.FISHING_ROD_REEL_IN);
            i = this.getHookedIn() instanceof ItemEntity ? 3 : 5;
        } else if (((FishingBobberAccessor) this).getNibble() > 0) {
            List<ItemStack> loot = this.rollLoot(player, usedItem);
            CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) player, usedItem, this, loot);
            for (ItemStack itemStack : loot) {
                this.dropCatch(player, itemStack);
            }
            i = 1;
        }

        if (this.onGround()) {
            i = 2;
        }
        this.discard();
        return i;
    }

    private List<ItemStack> rollLoot(Player player, ItemStack usedItem) {
        LootParams lootParams = new LootParams.Builder((ServerLevel) this.level())
                .withParameter(LootContextParams.ORIGIN, this.position())
                .withParameter(LootContextParams.TOOL, usedItem)
                .withParameter(LootContextParams.THIS_ENTITY, this)
                .withLuck(this.luckBonus + player.getLuck())
                .create(LootContextParamSets.FISHING);
        LootTable table = this.level().getServer().reloadableRegistries().getLootTable(this.lootTable);
        return table.getRandomItems(lootParams);
    }

    private void dropCatch(Player player, ItemStack itemStack) {
        ItemEntity itemEntity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), itemStack);
        double d = player.getX() - this.getX();
        double e = player.getY() - this.getY();
        double f = player.getZ() - this.getZ();
        itemEntity.setDeltaMovement(d * 0.1, e * 0.1 + Math.sqrt(Math.sqrt(d * d + e * e + f * f)) * 0.08, f * 0.1);
        this.level().addFreshEntity(itemEntity);
        player.level().addFreshEntity(new ExperienceOrb(player.level(), player.getX(),
                player.getY() + 0.5, player.getZ() + 0.5, this.random.nextInt(6) + 1));
        if (itemStack.is(ItemTags.FISHES)) {
            player.awardStat(Stats.FISH_CAUGHT, 1);
        }
    }
}
