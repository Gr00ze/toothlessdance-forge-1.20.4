package com.gr00ze.toothlessmeme.events;

import com.gr00ze.toothlessmeme.entity.EntityInit;
import com.gr00ze.toothlessmeme.entity.ToothlessEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    @SubscribeEvent
    public static void registerMobAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.TOOTHLESS_ENTITY.get(), ToothlessEntity.getMobAttributes());
    }
    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event){
        event.register(
                EntityInit.TOOTHLESS_ENTITY.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                ToothlessEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
    }




}
