package com.gr00ze.toothlessmeme.client.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,MOD_ID);

    public static final ResourceLocation
            DANCE_FILE = new ResourceLocation(MOD_ID,"dance_event"),
            EATING_FILE = new ResourceLocation(MOD_ID,"eating_event");
    public static final RegistryObject<SoundEvent>
            DANCE = SOUNDS.register("dance_event", () -> SoundEvent.createVariableRangeEvent(DANCE_FILE)),
            EATING = SOUNDS.register("eating_event", () -> SoundEvent.createVariableRangeEvent(EATING_FILE));

    public static void register(IEventBus e){
        SOUNDS.register(e);
    }
}
