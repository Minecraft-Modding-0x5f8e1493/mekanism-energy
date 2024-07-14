package com.mekanism.energy.registers;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.tiles.FortuneTransformerTile;
import com.mekanism.energy.tiles.MolecularMachineTile;
import com.mekanism.energy.tiles.PanelUnifierTile;
import com.mekanism.energy.util.Utils;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.Attributes;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.content.blocktype.Generator;
import mekanism.generators.common.registries.GeneratorsSounds;

public class TileGeneratorTypes {

    public static final Generator<PanelUnifierTile> PANEL_UNIFIER = (Generator.GeneratorBuilder
            .createGenerator(() -> TileTypes.PANEL_UNIFIER, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> TilesRegistry.SOLAR_PANEL_UNIFIER)
            .withEnergyConfig(MekaEnergy.getStorage().unifier)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withSound(GeneratorsSounds.SOLAR_GENERATOR)
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();

    public static final Generator<FortuneTransformerTile> FORTUNE_TRANSFORMER = (Generator.GeneratorBuilder
            .createGenerator(() -> TileTypes.FORTUNE_TRANSFORMER, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> TilesRegistry.FORTUNE_TRANSFORMER)
            .withEnergyConfig(MekaEnergy.getStorage().fortuneTransformer)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withSound(GeneratorsSounds.SOLAR_GENERATOR)
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();

    public static final Generator<MolecularMachineTile> MOLECULAR_MACHINE = (Generator.GeneratorBuilder
            .createGenerator(() -> TileTypes.MOLECULAR_MACHINE, GeneratorsLang.DESCRIPTION_SOLAR_GENERATOR)
            .withGui(() -> TilesRegistry.MOLECULAR_MACHINE)
            .withEnergyConfig(MekaEnergy.getStorage().molecularMachine)
            .withCustomShape(Utils.BLOCK_PANEL))
            .withSound(GeneratorsSounds.SOLAR_GENERATOR)
            .withComputerSupport("solarGenerator")
            .replace(new Attribute[]{Attributes.ACTIVE})
            .build();
}
