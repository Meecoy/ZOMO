/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meecoy.zomo.effect;

/**
 *
 * @author meecoy
 */

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ZOMOBeatenUpEffect extends MobEffect {
    
    public static final int DURATION = 500;
    public static final int MAX_EFFECT_LEVEL = 3;
    
    protected ZOMOBeatenUpEffect(MobEffectCategory category, int color){
        super(category, color);
    }
}
