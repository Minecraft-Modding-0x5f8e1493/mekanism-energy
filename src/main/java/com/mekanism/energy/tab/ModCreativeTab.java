package com.mekanism.energy.tab;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.registers.BlockRegister;
import com.mekanism.energy.registers.ItemRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MekaEnergy.MODID);

    public static final RegistryObject<CreativeModeTab> HEXTALE_TAB = CREATIVE_MODE_TABS.register("mekaenergy_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mekaenergy.tab"))
            .icon(ItemRegister.MATTER.asItem()::getDefaultInstance)
            .displayItems((displayParameters, output) -> {
                output.accept(BlockRegister.ADVANCED_SOLAR_PANEL.getBlock().asItem().getDefaultInstance());
                output.accept(BlockRegister.HYBRID_SOLAR_PANEL.getBlock().asItem().getDefaultInstance());
                output.accept(BlockRegister.ULTIMATE_HYBRID_SOLAR_PANEL.getBlock().asItem().getDefaultInstance());
                output.accept(BlockRegister.QUANTUM_SOLAR_PANEL.getBlock().asItem().getDefaultInstance());
                output.accept(BlockRegister.QUANTUM_GENERATOR.getBlock().asItem().getDefaultInstance());

                output.accept(BlockRegister.PANEL_UNIFIER.getBlock().asItem().getDefaultInstance());
                output.accept(BlockRegister.FORTUNE_TRANSFORMER.getBlock().asItem().getDefaultInstance());
                output.accept(BlockRegister.MOLECULAR_MACHINE.getBlock().asItem().getDefaultInstance());

                output.accept(ItemRegister.ORE_MODULEX1.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX2.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX3.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX4.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX5.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX6.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX7.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX8.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX9.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX10.asItem().getDefaultInstance());
                output.accept(ItemRegister.ORE_MODULEX11.asItem().getDefaultInstance());

                output.accept(ItemRegister.UPGRADE_INFINITE.asItem().getDefaultInstance());
                output.accept(ItemRegister.UPGRADE_SMELTING.asItem().getDefaultInstance());
                output.accept(ItemRegister.UPGRADE_STACK.asItem().getDefaultInstance());

                output.accept(ItemRegister.MATTER.asItem().getDefaultInstance());
                output.accept(ItemRegister.GAMMA_URANIUM.asItem().getDefaultInstance());

            })
            .build());

}
