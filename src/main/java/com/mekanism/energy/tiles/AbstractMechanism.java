package com.mekanism.energy.tiles;

import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.math.FloatingLong;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractMechanism extends TileEntityMekanism {

    BasicEnergyContainer energyContainer;

    public AbstractMechanism(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    public abstract FloatingLong getMaxEnergy();

    @Override
    protected @NotNull IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSide(this::getDirection);
        builder.addContainer(this.energyContainer = BasicEnergyContainer.input(getMaxEnergy(), listener), this.getEnergySides());
        return builder.build();
    }

    protected RelativeSide[] getEnergySides() {
        return new RelativeSide[]{RelativeSide.FRONT, RelativeSide.BACK, RelativeSide.BOTTOM, RelativeSide.LEFT, RelativeSide.RIGHT};
    }

    @Override
    protected void onUpdateServer() {
        super.onUpdateServer();

//        CableUtils.emit(emitDirections, this.energyContainer, this, FloatingLong.create(MAX_INPUT));
    }
}
