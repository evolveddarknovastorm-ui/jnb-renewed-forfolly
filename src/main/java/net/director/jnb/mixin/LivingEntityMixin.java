package net.director.jnb.mixin;

import net.director.jnb.JNBRenewed;
import net.director.jnb.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void useCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        ItemStack totemStack = null;
        for (Hand hand : Hand.values()) {
            ItemStack stack = entity.getStackInHand(hand);
            if (stack.isOf(ModItems.D_COIN)) {
                totemStack = stack.copy();
                stack.decrement(1); // Consume the totem
                break;
            }
        }


        if (totemStack != null) {
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.incrementStat(Stats.USED.getOrCreateStat(ModItems.D_COIN));
                Criteria.USED_TOTEM.trigger(serverPlayer, totemStack);
            }


            entity.setHealth(1.0F);
            entity.clearStatusEffects();


            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3600, 2));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3600, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 3600, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3600, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3600, 1));


            entity.getWorld().sendEntityStatus(entity, (byte) 35);


            cir.setReturnValue(true);
        }
    }
}
