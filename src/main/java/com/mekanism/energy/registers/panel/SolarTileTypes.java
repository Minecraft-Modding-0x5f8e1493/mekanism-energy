package com.mekanism.energy.registers.panel;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.registers.BlockRegister;
import com.mekanism.energy.tiles.panels.HybridSolarPanelTile;
import com.mekanism.energy.tiles.panels.UltimateHybridSolarPanelTile;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import com.mekanism.energy.tiles.panels.QuantumSolarPanelTile;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;


public class SolarTileTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekaEnergy.MODID);

    public static final TileEntityTypeRegistryObject<AdvancedSolarPanelTile> ADVANCED_SOLAR_PANEL = TILE_ENTITY_TYPES.register(BlockRegister.ADVANCED_SOLAR_PANEL, AdvancedSolarPanelTile::new);
    public static final TileEntityTypeRegistryObject<HybridSolarPanelTile> HYBRID_SOLAR_PANEL = TILE_ENTITY_TYPES.register(BlockRegister.HYBRID_SOLAR_PANEL, HybridSolarPanelTile::new);
    public static final TileEntityTypeRegistryObject<UltimateHybridSolarPanelTile> ULTIMATE_HYBRID_SOLAR_PANEL = TILE_ENTITY_TYPES.register(BlockRegister.ULTIMATE_HYBRID_SOLAR_PANEL, UltimateHybridSolarPanelTile::new);
    public static final TileEntityTypeRegistryObject<QuantumSolarPanelTile> QUANTUM_SOLAR_PANEL = TILE_ENTITY_TYPES.register(BlockRegister.QUANTUM_SOLAR_PANEL, QuantumSolarPanelTile::new);
}
