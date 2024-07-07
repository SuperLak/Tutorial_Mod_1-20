package net.kal.tutorial_mod.misc;

import net.kal.tutorial_mod.Tutorial_Mod;
import net.kal.tutorial_mod.block.ModBlocks;
import net.kal.tutorial_mod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tutorial_Mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register(
            "tutorial_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEM.get()))
                    .title(Component.translatable("creative_tab.tutorial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // adding items to the creative mode tab
                        pOutput.accept(ModItems.GEM.get());
                        pOutput.accept(ModItems.RAW_GEM.get());

                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.YELLOW_SNOWBALL.get());

                        // adding blocks to the creative mode tab
                        pOutput.accept(ModBlocks.GEM_BLOCK.get());
                        pOutput.accept(ModBlocks.GEM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_GEM_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_GEM_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_GEM_ORE.get());

                        pOutput.accept(ModBlocks.SOUND_BLOCk.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
