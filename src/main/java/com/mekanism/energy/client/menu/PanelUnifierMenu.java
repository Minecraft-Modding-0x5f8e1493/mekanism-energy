package com.mekanism.energy.client.menu;

import com.mekanism.energy.registers.TilesRegistry;
import com.mekanism.energy.tiles.PanelUnifierTile;
import com.mekanism.energy.tiles.panels.AbstractSolarPanel;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.generators.common.content.blocktype.Generator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.Block;
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
                addSlot(new AdvancedSlot(tile, i, X_SLOT_OFFSET + x * 18, Y_SLOT_OFFSET + y * 18, stack -> {
                    if (stack.getFrame() != null) {
                        BlockPos pos = stack.getFrame().blockPosition();
                        if (tile.getLevel() != null) {

                            System.out.println(tile.getLevel().getBlockEntity(pos) instanceof AbstractSolarPanel);
                            return tile.getLevel().getBlockEntity(pos) instanceof AbstractSolarPanel;
                        }
                    }

                    return false;
                }));
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
