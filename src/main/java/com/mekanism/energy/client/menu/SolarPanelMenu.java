package com.mekanism.energy.client.menu;

import com.mekanism.energy.tiles.panels.AbstractSolarPanel;
import mekanism.api.math.FloatingLong;
import mekanism.client.render.armor.MekaSuitArmor;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.item.ItemConfigurator;
import mekanism.common.item.ItemEnergized;
import mekanism.common.item.block.ItemBlockEnergyCube;
import mekanism.common.item.gear.ItemAtomicDisassembler;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.lib.attribute.IAttributeRefresher;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.tags.MekanismTags;
import mekanism.common.tile.interfaces.IUpgradeTile;
import mekanism.common.util.MekanismUtils;
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
            addSlot(new AdvancedSlot(tile, i, X_SLOT_OFFSET + i * 18, Y_SLOT_OFFSET, stack -> {
                return stack.getItem() instanceof CreativeTabDeferredRegister.ICustomCreativeTabContents;
            }));
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
