/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meecoy.zomo.item;

/**
 *
 * @author meecoy
 */

import com.meecoy.zomo.ZOMO;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.meecoy.zomo.item.ZOMOBatItem;
import com.meecoy.zomo.item.ZOMODocumentItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = 
            DeferredRegister.createItems(ZOMO.MODID);
    
    public static final DeferredItem<ZOMOBatItem> ZOMO_BAT =
            ITEMS.registerItem("zomo_bat", ZOMOBatItem::new, new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            Tiers.DIAMOND,
                            ZOMOBatItem.DAMAGE,
                            ZOMOBatItem.ATTACK_SPEED)).stacksTo(1));
    
    public static final DeferredItem<ZOMODocumentItem> ZOMO_DOCUMENT = 
            ITEMS.registerItem("zomo_document", ZOMODocumentItem::new);
    
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
