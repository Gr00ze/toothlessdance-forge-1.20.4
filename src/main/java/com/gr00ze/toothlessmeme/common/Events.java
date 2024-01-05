package com.gr00ze.toothlessmeme.common;

import com.gr00ze.toothlessmeme.entity.EntityInit;
import com.gr00ze.toothlessmeme.entity.ToothlessEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {
    @SubscribeEvent
    public static void registerMobAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.TOOTHLESS_ENTITY.get(), ToothlessEntity.getMobAttributes());
    }
}
