package com.mekanism.energy.tiles.panels;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.registers.BlockRegister;
import com.mekanism.energy.registers.panel.SolarContainerTypes;
import mekanism.api.math.FloatingLong;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AdvancedSolarPanelTile extends AbstractSolarPanel {

    public AdvancedSolarPanelTile(BlockPos pos, BlockState state) {
        super(BlockRegister.ADVANCED_SOLAR_PANEL, pos, state, MekaEnergy.getSolar().advancedSolarPanel.get().multiply(2L), FloatingLong.create(50));
    }

    @Override
    @NotNull
    @SuppressWarnings(value = "unchecked")
    public ContainerTypeRegistryObject<MekanismTileContainer<AdvancedSolarPanelTile>> getContainerType() {
        return SolarContainerTypes.ADVANCED_SOLAR_PANEL;
    }

    @Override
    protected FloatingLong getConfiguredMax() {
        return MekaEnergy.getSolar().advancedSolarPanel.get();
    }
}
