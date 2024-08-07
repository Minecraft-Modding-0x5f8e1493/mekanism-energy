package com.mekanism.energy.client.menu;

import com.mekanism.energy.tiles.panels.AbstractSolarPanel;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class SolarPanelMenu<P extends AbstractSolarPanel> extends MekanismTileContainer<P> {

    private static final int X_SLOT_OFFSET = 52;
    private static final int Y_SLOT_OFFSET = 59;

    public SolarPanelMenu(int id, Inventory inv, P tile) {
        super(tile.getContainerType(), id, inv, tile);
    }

    @Override
    protected void addSlots() {
        for (int i = 0; i <= 3; i++) {
            addSlot(new Slot(tile, i, X_SLOT_OFFSET + i * 18, Y_SLOT_OFFSET));
        }
    }

    @Override
    protected void addSlotsAndOpen() {
        this.addSlots();
        this.addInventorySlots(this.inv);
        this.openInventory(this.inv);
    }

    @Override
    protected int getInventoryYOffset() {
        return 95;
    }

    @Override
    protected int getInventoryXOffset() {
        return 7;
    }
}
