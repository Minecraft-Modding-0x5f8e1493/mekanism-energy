package com.mekanism.energy.client;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.client.gui.FortuneTransformerScreen;
import com.mekanism.energy.client.gui.MolecularMachineScreen;
import com.mekanism.energy.client.gui.PanelUnifierScreen;
import com.mekanism.energy.client.gui.panel.SolarPanelScreenTest;
import com.mekanism.energy.registers.TilesRegistry;
import com.mekanism.energy.tiles.panels.HybridSolarPanelTile;
import com.mekanism.energy.registers.panel.SolarContainerTypes;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import com.mekanism.energy.tiles.panels.QuantumSolarPanelTile;
import com.mekanism.energy.tiles.panels.UltimateHybridSolarPanelTile;
import mekanism.client.ClientRegistrationUtil;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;


@Mod.EventBusSubscriber(modid = MekaEnergy.MODID, value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScreensClientRegistration {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void register(@NotNull RegisterEvent event) {
        event.register(Registries.MENU, helper -> {

            ClientRegistrationUtil.registerScreen(SolarContainerTypes.ADVANCED_SOLAR_PANEL,
                    (MekanismTileContainer<AdvancedSolarPanelTile> container, Inventory inv, Component title)
                            -> new SolarPanelScreenTest<>(container, inv)); // Переделка под конструктор
            ClientRegistrationUtil.registerScreen(SolarContainerTypes.HYBRID_SOLAR_PANEL,
                    (MekanismTileContainer<HybridSolarPanelTile> container, Inventory inv, Component title)
                            -> new SolarPanelScreenTest<>(container, inv));
            ClientRegistrationUtil.registerScreen(SolarContainerTypes.ULTIMATE_HYBRID_SOLAR_PANEL,
                    (MekanismTileContainer<UltimateHybridSolarPanelTile> container, Inventory inv, Component title)
                            -> new SolarPanelScreenTest<>(container, inv));
            ClientRegistrationUtil.registerScreen(SolarContainerTypes.QUANTUM_SOLAR_PANEL,
                    (MekanismTileContainer<QuantumSolarPanelTile> container, Inventory inv, Component title)
                            -> new SolarPanelScreenTest<>(container, inv));

            // Другие меню
            ClientRegistrationUtil.registerScreen(TilesRegistry.SOLAR_PANEL_UNIFIER, PanelUnifierScreen::new);
            ClientRegistrationUtil.registerScreen(TilesRegistry.FORTUNE_TRANSFORMER, FortuneTransformerScreen::new);
            ClientRegistrationUtil.registerScreen(TilesRegistry.MOLECULAR_MACHINE, MolecularMachineScreen::new);
        });
    }
}
