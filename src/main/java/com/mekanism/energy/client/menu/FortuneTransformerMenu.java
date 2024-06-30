package com.mekanism.energy.client.menu;

import com.mekanism.energy.items.Module;
import com.mekanism.energy.items.upgrade.Upgrade;
import com.mekanism.energy.registers.TilesRegistry;
import com.mekanism.energy.tiles.FortuneTransformerTile;
import mekanism.common.block.BlockOre;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FortuneTransformerMenu extends MekanismTileContainer<FortuneTransformerTile> {

    private static final int X_TO_WORK_SLOT_OFFSET = -25; // 21
    private static final int Y_TO_WORK_SLOT_OFFSET = -30; // 2

    private static final int X_AFTER_WORK_SLOT_OFFSET = -25;
    private static final int Y_AFTER_WORK_SLOT_OFFSET = 50;

    private static final int X_MODIFICATORS_SLOT_OFFSET = 141;
    private static final int Y_MODIFICATORS_SLOT_OFFSET = -26;

    private static final int X_WORK_ITEM_SLOT_OFFSET = -25;
    private static final int Y_WORK_ITEM_SLOT_OFFSET = 28;

    public FortuneTransformerMenu(int id, Inventory inv, @NotNull FortuneTransformerTile panelUnifierTile) {
        super(TilesRegistry.FORTUNE_TRANSFORMER, id, inv, panelUnifierTile);
    }

    @Override
    protected void addSlots() {
        int i = 0;
        // ToWork slots

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {

                addSlot(new AdvancedSlot(tile, i, X_TO_WORK_SLOT_OFFSET + x * 18,
                        Y_TO_WORK_SLOT_OFFSET + y * 18, (stack) -> {
                    return Block.byItem(stack.getItem()) instanceof DropExperienceBlock;
                }, "ToWork!"));
                i += 1;
            }
        }

        // AfterWork slots
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlot(new AdvancedSlot(tile, i, X_AFTER_WORK_SLOT_OFFSET + x * 18,
                        Y_AFTER_WORK_SLOT_OFFSET + y * 18, (stack) -> {
                    return false;
                }, "AfterWork!"));
                i += 1;
            }
        }

        // Modificators slots
        for (int y = 0; y < 3; y++) {
            addSlot(new AdvancedSlot(tile, i, X_MODIFICATORS_SLOT_OFFSET,
                    Y_MODIFICATORS_SLOT_OFFSET + y * 18, (stack) -> {
                return stack.getItem() instanceof Upgrade;
            }));
            i += 1;
        }

        // WorkingItemSlot
        addSlot(new AdvancedSlot(tile, i, X_WORK_ITEM_SLOT_OFFSET, Y_WORK_ITEM_SLOT_OFFSET, (stack) -> {
            return stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof Module;
        }, "WorkingItemSlot!"));
    }

    @Override
    protected void addSlotsAndOpen() {
        this.addSlots();
        this.addInventorySlots(this.inv);
        this.openInventory(this.inv);
    }

    @Override
    protected int getInventoryYOffset() {
        return 120;
    }

    @Override
    protected int getInventoryXOffset() {
        return -14;
    }

}
