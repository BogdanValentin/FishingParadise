package net.bogdanvalentin.fishingparadise.item.custom;

import net.bogdanvalentin.fishingparadise.entity.ModFishingBobberEntity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Every modded rod. A rod is its durability plus the loot table it casts into.
 */
public class ModFishingRodItem extends FishingRodItem {
    private final ResourceKey<LootTable> lootTable;

    public ModFishingRodItem(Properties properties, ResourceKey<LootTable> lootTable) {
        super(properties);
        this.lootTable = lootTable;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player.fishing != null) {
            if (!level.isClientSide) {
                int damage = player.fishing.retrieve(itemStack);
                itemStack.hurtAndBreak(damage, player, LivingEntity.getSlotForHand(hand));
            }
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL,
                    1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL,
                    0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (level instanceof ServerLevel serverLevel) {
                int lureTicks = (int) (EnchantmentHelper.getFishingTimeReduction(serverLevel, itemStack, player) * 20.0F);
                int luckBonus = EnchantmentHelper.getFishingLuckBonus(serverLevel, itemStack, player);
                level.addFreshEntity(new ModFishingBobberEntity(player, level, luckBonus, lureTicks, this, this.lootTable));
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
