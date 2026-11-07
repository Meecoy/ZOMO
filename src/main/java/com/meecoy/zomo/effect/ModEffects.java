/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meecoy.zomo.effect;

/**
 *
 * @author meecoy
 */

import com.meecoy.zomo.effect.ZOMOBeatenUpEffect;
import com.meecoy.zomo.ZOMO;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = 
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ZOMO.MODID);
    
    public static final Holder<MobEffect> ZOMO_BEATENUP_EFFECT = MOB_EFFECTS.register("zomo_beatenup", () -> new ZOMOBeatenUpEffect(MobEffectCategory.HARMFUL, 0x2c3d59));
    
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
