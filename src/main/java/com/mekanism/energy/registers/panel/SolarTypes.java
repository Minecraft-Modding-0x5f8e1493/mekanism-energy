package com.mekanism.energy.registers.panel;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.tiles.panels.HybridSolarPanelTile;
import com.mekanism.energy.tiles.panels.UltimateHybridSolarPanelTile;
import com.mekanism.energy.util.Utils;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import com.mekanism.energy.tiles.panels.QuantumSolarPanelTile;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.Attributes;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.content.blocktype.Generator;
import mekanism.generators.common.registries.GeneratorsSounds;


public class SolarTypes {

    public static final Generator<AdvancedSolarPanelTile> ADVANCED_SOLAR_PANEL_TYPE = (Generator.GeneratorBuilder
            .createGenerator(() -> SolarTileTypes.ADVANCED_SOLAR_PANEL, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> SolarContainerTypes.ADVANCED_SOLAR_PANEL)
            .withEnergyConfig(MekaEnergy.getStorage().advancedSolarPanel)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withSound(GeneratorsSounds.SOLAR_GENERATOR)
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();

    public static final Generator<HybridSolarPanelTile> HYBRID_SOLAR_PANEL_TYPE = (Generator.GeneratorBuilder
            .createGenerator(() -> SolarTileTypes.HYBRID_SOLAR_PANEL, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> SolarContainerTypes.HYBRID_SOLAR_PANEL)
            .withEnergyConfig(MekaEnergy.getStorage().hybridSolarPanel)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();

    public static final Generator<UltimateHybridSolarPanelTile> ULTIMATE_HYBRID_SOLAR_PANEL_TYPE = (Generator.GeneratorBuilder
            .createGenerator(() -> SolarTileTypes.ULTIMATE_HYBRID_SOLAR_PANEL, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> SolarContainerTypes.ULTIMATE_HYBRID_SOLAR_PANEL)
            .withEnergyConfig(MekaEnergy.getStorage().ultimateHybridSolarPanel)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withSound(GeneratorsSounds.SOLAR_GENERATOR)
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();

    public static final Generator<QuantumSolarPanelTile> QUANTUM_SOLAR_PANEL_TYPE = (Generator.GeneratorBuilder
            .createGenerator(() -> SolarTileTypes.QUANTUM_SOLAR_PANEL, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> SolarContainerTypes.QUANTUM_SOLAR_PANEL)
            .withEnergyConfig(MekaEnergy.getStorage().quantumSolarPanel)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withSound(GeneratorsSounds.SOLAR_GENERATOR)
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();
}
