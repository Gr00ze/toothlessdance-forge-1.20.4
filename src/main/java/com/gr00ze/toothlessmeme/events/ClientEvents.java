package com.gr00ze.toothlessmeme.events;

import com.gr00ze.toothlessmeme.client.model.ToothlessModel;
import com.gr00ze.toothlessmeme.client.render.ToothlessRender;
import com.gr00ze.toothlessmeme.entity.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {


    @SubscribeEvent
    public static void registerRenderers (EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityInit.TOOTHLESS_ENTITY.get(), ToothlessRender::new);
    }

    @SubscribeEvent
    public static void registerModels (EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ToothlessModel.MODEL_LAYER_LOCATION, ToothlessModel::getParts);
    }

}