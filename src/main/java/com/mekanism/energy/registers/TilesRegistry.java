package com.mekanism.energy.registers;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.client.menu.FortuneTransformerMenu;
import com.mekanism.energy.client.menu.MolecularMachineMenu;
import com.mekanism.energy.client.menu.PanelUnifierMenu;
import com.mekanism.energy.tiles.FortuneTransformerTile;
import com.mekanism.energy.tiles.MolecularMachineTile;
import com.mekanism.energy.tiles.PanelUnifierTile;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

public class TilesRegistry {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekaEnergy.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<PanelUnifierTile>> SOLAR_PANEL_UNIFIER = CONTAINER_TYPES.register("panel_unifier", PanelUnifierTile.class, PanelUnifierMenu::new);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<FortuneTransformerTile>> FORTUNE_TRANSFORMER = CONTAINER_TYPES.register("fortune_transformer", FortuneTransformerTile.class, FortuneTransformerMenu::new);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<MolecularMachineTile>> MOLECULAR_MACHINE = CONTAINER_TYPES.register("molecular_machine", MolecularMachineTile.class, MolecularMachineMenu::new);
}
