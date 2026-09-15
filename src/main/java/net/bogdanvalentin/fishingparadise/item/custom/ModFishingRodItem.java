package net.bogdanvalentin.fishingparadise.item.custom;

import net.bogdanvalentin.fishingparadise.entity.ModFishingBobberEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ModFishingRodItem extends FishingRodItem {
    private final Identifier lootTableId;

    public ModFishingRodItem(Settings settings, Identifier lootTableId) {
        super(settings);
        this.lootTableId = lootTableId;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.fishHook != null) {
            if (!world.isClient) {
                int damage = user.fishHook.use(itemStack);
                itemStack.damage(damage, user, (p) -> p.sendToolBreakStatus(hand));
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL,
                    1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL,
                    0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                int lure = EnchantmentHelper.getLure(itemStack);
                int luckOfTheSea = EnchantmentHelper.getLuckOfTheSea(itemStack);
                world.spawnEntity(new ModFishingBobberEntity(user, world, luckOfTheSea, lure, this, this.lootTableId));
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
