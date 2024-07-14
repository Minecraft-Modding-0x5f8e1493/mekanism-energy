package com.mekanism.energy.registers;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.tiles.FortuneTransformerTile;
import com.mekanism.energy.tiles.MolecularMachineTile;
import com.mekanism.energy.tiles.PanelUnifierTile;
import com.mekanism.energy.tiles.panels.QuantumSolarPanelTile;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

public class TileTypes {
    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekaEnergy.MODID);

    public static final TileEntityTypeRegistryObject<PanelUnifierTile> PANEL_UNIFIER = TILE_ENTITY_TYPES.register(BlockRegister.PANEL_UNIFIER, PanelUnifierTile::new);
    public static final TileEntityTypeRegistryObject<FortuneTransformerTile> FORTUNE_TRANSFORMER = TILE_ENTITY_TYPES.register(BlockRegister.FORTUNE_TRANSFORMER, FortuneTransformerTile::new);
    public static final TileEntityTypeRegistryObject<MolecularMachineTile> MOLECULAR_MACHINE = TILE_ENTITY_TYPES.register(BlockRegister.MOLECULAR_MACHINE, MolecularMachineTile::new);
}
