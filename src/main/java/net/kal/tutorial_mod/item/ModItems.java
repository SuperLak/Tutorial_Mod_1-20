package net.kal.tutorial_mod.item;

import net.kal.tutorial_mod.Tutorial_Mod;
import net.kal.tutorial_mod.item.custom.MetalDetectorItem;
import net.kal.tutorial_mod.item.custom.YellowSnowballItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial_Mod.MOD_ID);

    public static final RegistryObject<Item> GEM =
            ITEMS.register("gem", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GEM =
            ITEMS.register("raw_gem", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR =
            ITEMS.register("metal_detector", () -> new MetalDetectorItem(new Item.Properties()
                    .durability(70)));

    public static final RegistryObject<Item> YELLOW_SNOWBALL =
            ITEMS.register("yellow_snowball", () -> new YellowSnowballItem(new Item.Properties()
                    .stacksTo(16)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
