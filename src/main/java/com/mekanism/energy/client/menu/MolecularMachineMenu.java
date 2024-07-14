package com.mekanism.energy.client.menu;

import com.mekanism.energy.registers.TilesRegistry;
import com.mekanism.energy.tiles.MolecularMachineTile;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public class MolecularMachineMenu extends MekanismTileContainer<MolecularMachineTile> {

    private static final int X_FIRST_SLOT_OFFSET = -1;
    private static final int Y_FIRST_SLOT_OFFSET = 10;

    private static final int X_SECOND_SLOT_OFFSET = -1;
    private static final int Y_SECOND_SLOT_OFFSET = 51;

    public MolecularMachineMenu(int id, Inventory inv, @NotNull MolecularMachineTile panelUnifierTile) {
        super(TilesRegistry.MOLECULAR_MACHINE, id, inv, panelUnifierTile);
    }

    @Override
    protected void addSlots() {
        addSlot(new Slot(tile, 0, X_FIRST_SLOT_OFFSET, Y_FIRST_SLOT_OFFSET));
        addSlot(new Slot(tile, 1, X_SECOND_SLOT_OFFSET, Y_SECOND_SLOT_OFFSET));
    }

    @Override
    protected void addSlotsAndOpen() {
        this.addSlots();
        this.addInventorySlots(this.inv);
        this.openInventory(this.inv);
    }

    @Override
    protected int getInventoryYOffset() {
        return 81;
    }

    @Override
    protected int getInventoryXOffset() {
        return 7;
    }

}
