package com.meecoy.zomo.item;

/**
 *
 * @author meecoy
 */

import dev.leo.sableplayerragdoll.api.RagdollAPI;
import dev.leo.sableplayerragdoll.api.RagdollLaunchOptions;
import dev.leo.sableplayerragdoll.api.DespawnCondition;
import dev.leo.sableplayerragdoll.mob.api.MobRagdollLaunchOptions;

import java.util.List;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;


public class ZOMOBatItem extends Item {
    public  static final int    RAGDOLL_DURATION = 100;
    public  static final double KNOCKBACK = 3.0;
    public  static final int    DAMAGE = 3;
    public  static final float  ATTACK_SPEED = -2.4f;
    
    public ZOMOBatItem(Item.Properties properties){
        super(properties);
    }
    
    public static void applyRagdoll(LivingEntity target, LivingEntity attacker){
        if (!(target.level() instanceof ServerLevel level)){
            return;
        }
        
        Vec3 direction = target.position().subtract(attacker.position());
        direction = new Vec3(direction.x,0,direction.z);
        
        if (direction.lengthSqr() > 0.0001){
            direction = direction.normalize();
        } else {
            direction = attacker.getLookAngle();
            direction = new Vec3(direction.x, 0, direction.z);
            
            if (direction.lengthSqr() > 0.0001) {
                direction = direction.normalize();
            } else {
                direction = new Vec3(0, 0, 1);
            }
        }
        
        Vec3 velocity = direction.scale(KNOCKBACK).add(0, 1.0, 0);
        
        if (target instanceof ServerPlayer player){
            if (!RagdollAPI.isRagdolled(player)){
                RagdollLaunchOptions options = RagdollLaunchOptions.builder().despawnConditions(List.of(
                    DespawnCondition.afterTicks(RAGDOLL_DURATION))).lockDismount(true).build();
            
                RagdollAPI.launch(player, velocity, options);
            } 
        } else if (!RagdollAPI.isMobRagdolled(target)){
            MobRagdollLaunchOptions options = MobRagdollLaunchOptions.builder().durationTicks(RAGDOLL_DURATION).build();
            
            RagdollAPI.launchMob(level, target, velocity, Vec3.ZERO, options);
        }
    }
}
