package com.gr00ze.toothlessmeme.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import static com.gr00ze.toothlessmeme.ToothlessMemeMod.MOD_ID;
import static com.gr00ze.toothlessmeme.entity.EntityInit.TOOTHLESS_ENTITY;

public class ItemsInitializer {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> TOOTHLESS_EGG_ITEM = ITEMS.register("toothless_egg", () -> new ForgeSpawnEggItem(TOOTHLESS_ENTITY,0x111111,0x111A1A, new Item.Properties()));

    public static void register(IEventBus e){
        ITEMS.register(e);
    }

}
