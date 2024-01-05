package com.gr00ze.toothlessmeme.entity;

import com.gr00ze.toothlessmeme.client.render.ToothlessRender;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,MOD_ID);

    public static RegistryObject<EntityType<ToothlessEntity>> TOOTHLESS_ENTITY = ENTITIES.register("toothless",() -> EntityType.Builder.of(ToothlessEntity::new, MobCategory.CREATURE)
            .build(ToothlessRender.TEXTURE.toString()));

    public static void register(IEventBus e) {
        ENTITIES.register(e);
    }
}
