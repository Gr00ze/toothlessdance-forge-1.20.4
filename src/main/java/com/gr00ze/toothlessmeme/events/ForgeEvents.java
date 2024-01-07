package com.gr00ze.toothlessmeme.events;

import com.gr00ze.toothlessmeme.entity.ToothlessEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void onSpawnAttempt(net.minecraftforge.event.entity.EntityJoinLevelEvent event){
        if (event.getEntity() instanceof ToothlessEntity toothless)
            System.out.println("Toothless spawnato a : "+toothless.position());
    }
}
