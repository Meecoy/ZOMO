/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meecoy.zomo.event;

/**
 *
 * @author meecoy
 */

import com.meecoy.zomo.ZOMO;
import com.meecoy.zomo.item.ModItems;
import com.meecoy.zomo.item.ZOMOBatItem;
import com.meecoy.zomo.effect.ModEffects;
import com.meecoy.zomo.effect.ZOMOBeatenUpEffect;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = ZOMO.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ZOMOBatItem)) {
            return;
        }

        boolean hasDocument = player.getInventory().contains(
                new ItemStack(ModItems.ZOMO_DOCUMENT.get())
        );

        if (!hasDocument) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event){
        LivingEntity target = event.getEntity();
        
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        
        if (!(attacker.getMainHandItem().getItem() instanceof ZOMOBatItem)) {
            return;
        }
        
        MobEffectInstance currentEffect = target.getEffect(ModEffects.ZOMO_BEATENUP_EFFECT);
        
        int currentLevel = currentEffect != null ? currentEffect.getAmplifier() + 1 : 0;
        
        int newLevel = Math.min(currentLevel + 1, ZOMOBeatenUpEffect.MAX_EFFECT_LEVEL);
        
        target.addEffect(new MobEffectInstance(ModEffects.ZOMO_BEATENUP_EFFECT, ZOMOBeatenUpEffect.DURATION * newLevel + 1, newLevel - 1));
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, ZOMOBatItem.RAGDOLL_DURATION, newLevel - 1));
        
        if (newLevel == ZOMOBeatenUpEffect.MAX_EFFECT_LEVEL){
            ZOMOBatItem.applyRagdoll(target, attacker);
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, ZOMOBatItem.RAGDOLL_DURATION, 0));
            target.removeEffect(ModEffects.ZOMO_BEATENUP_EFFECT);
        }
        
    }
}
