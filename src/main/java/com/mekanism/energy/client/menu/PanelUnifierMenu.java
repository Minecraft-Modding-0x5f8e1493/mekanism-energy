package com.mekanism.energy.client.menu;

import com.mekanism.energy.registers.TilesRegistry;
import com.mekanism.energy.tiles.PanelUnifierTile;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public class PanelUnifierMenu extends MekanismTileContainer<PanelUnifierTile> {

    private static final int X_SLOT_OFFSET = -14; // 21
    private static final int Y_SLOT_OFFSET = -28; // 2


    public PanelUnifierMenu(int id, Inventory inv, @NotNull PanelUnifierTile panelUnifierTile) {
        super(TilesRegistry.SOLAR_PANEL_UNIFIER, id, inv, panelUnifierTile);
    }

    @Override
    protected void addSlots() {
        int i = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 9; x++) {
                addSlot(new Slot(tile, i, X_SLOT_OFFSET + x * 18, Y_SLOT_OFFSET + y * 18));
                i += 1;
            }
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
        return 116;
    }

    @Override
    protected int getInventoryXOffset() {
        return -14;
    }

}
