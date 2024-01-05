package com.gr00ze.toothlessmeme;

import com.gr00ze.toothlessmeme.client.sounds.Sounds;
import com.gr00ze.toothlessmeme.entity.EntityInit;
import com.gr00ze.toothlessmeme.items.ItemsInitializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

@Mod(MOD_ID)
public class ToothlessMemeMod {
    public static final String MOD_ID = "toothless_meme_mod";


    public ToothlessMemeMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Sounds.register(modEventBus);
        EntityInit.register(modEventBus);
        ItemsInitializer.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);


    }


}
