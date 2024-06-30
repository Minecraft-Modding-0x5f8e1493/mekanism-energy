package com.mekanism.energy.registers;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.registers.panel.SolarTypes;
import com.mekanism.energy.tiles.FortuneTransformerTile;
import com.mekanism.energy.tiles.MolecularMachineTile;
import com.mekanism.energy.tiles.PanelUnifierTile;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import com.mekanism.energy.tiles.panels.HybridSolarPanelTile;
import com.mekanism.energy.tiles.panels.QuantumSolarPanelTile;
import com.mekanism.energy.tiles.panels.UltimateHybridSolarPanelTile;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.generators.common.content.blocktype.Generator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockRegister {
    public static final BlockDeferredRegister REGISTRY_BLOCK = new BlockDeferredRegister(MekaEnergy.MODID);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<PanelUnifierTile,
            Generator<PanelUnifierTile>>,
            ItemBlockMachine> PANEL_UNIFIER = REGISTRY_BLOCK.register("panel_unifier",
            () -> new BlockTile.BlockTileModel<>(TileGeneratorTypes.PANEL_UNIFIER, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<FortuneTransformerTile,
            Generator<FortuneTransformerTile>>,
            ItemBlockMachine> FORTUNE_TRANSFORMER = REGISTRY_BLOCK.register("fortune_transformer",
            () -> new BlockTile.BlockTileModel<>(TileGeneratorTypes.FORTUNE_TRANSFORMER, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<MolecularMachineTile,
            Generator<MolecularMachineTile>>,
            ItemBlockMachine> MOLECULAR_MACHINE = REGISTRY_BLOCK.register("molecular_machine",
            () -> new BlockTile.BlockTileModel<>(TileGeneratorTypes.MOLECULAR_MACHINE, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);


    public static final BlockRegistryObject<BlockTile.BlockTileModel<AdvancedSolarPanelTile,
            Generator<AdvancedSolarPanelTile>>,
            ItemBlockMachine> ADVANCED_SOLAR_PANEL = REGISTRY_BLOCK.register("advanced_solar_panel",
            () -> new BlockTile.BlockTileModel<>(SolarTypes.ADVANCED_SOLAR_PANEL_TYPE, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<HybridSolarPanelTile,
            Generator<HybridSolarPanelTile>>,
            ItemBlockMachine> HYBRID_SOLAR_PANEL = REGISTRY_BLOCK.register("hybrid_solar_panel",
            () -> new BlockTile.BlockTileModel<>(SolarTypes.HYBRID_SOLAR_PANEL_TYPE, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<UltimateHybridSolarPanelTile,
            Generator<UltimateHybridSolarPanelTile>>,
            ItemBlockMachine> ULTIMATE_HYBRID_SOLAR_PANEL = REGISTRY_BLOCK.register("ultimate_hybrid_solar_panel",
            () -> new BlockTile.BlockTileModel<>(SolarTypes.ULTIMATE_HYBRID_SOLAR_PANEL_TYPE, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<QuantumSolarPanelTile,
            Generator<QuantumSolarPanelTile>>,
            ItemBlockMachine> QUANTUM_SOLAR_PANEL = REGISTRY_BLOCK.register("quantum_solar_panel",
            () -> new BlockTile.BlockTileModel<>(SolarTypes.QUANTUM_SOLAR_PANEL_TYPE, properties -> properties.mapColor(MapColor.COLOR_BLUE)), ItemBlockMachine::new);

    public static final BlockRegistryObject<Block, BlockItem> QUANTUM_GENERATOR = REGISTRY_BLOCK.register("quantum_generator", () -> {
        return new Block(BlockBehaviour.Properties.of());
    });
}
