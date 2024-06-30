package com.mekanism.energy.tiles;

import com.mekanism.energy.registers.BlockRegister;
import com.mekanism.energy.tiles.panels.AbstractSolarPanel;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import com.mekanism.energy.tiles.panels.MekaTileEntityGenerator;
import mekanism.api.math.FloatingLong;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.*;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PanelUnifierTile extends MekaTileEntityGenerator implements Container {

    private final List<ItemStack> panels = new ArrayList<>();
    private FloatingLong production = FloatingLong.ZERO;

    public PanelUnifierTile(BlockPos pos, BlockState state) {
        super(BlockRegister.PANEL_UNIFIER, pos, state, () -> FloatingLong.create(100000));

        for (int i = 0; i < 9*4; i++) {
            panels.add(ItemStack.EMPTY);
        }
    }

    private void fillList() {
        for (int i = 0; i < 9*4; i++) {
            panels.set(i, ItemStack.EMPTY);
        }
    }

    @Override
    protected FloatingLong getProductionRate() {
        return production;
    }

    public void setProductionRate(FloatingLong production) {
        this.production = production;
    }

    @Override
    public int getContainerSize() {
        return 4 * 9;
    }

    @Override
    public boolean isEmpty() {
        return panels.stream().anyMatch((r) -> r == ItemStack.EMPTY);
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return panels.get(i);
    }

    @Override
    public @NotNull ItemStack removeItem(int i, int i1) {
        return ContainerHelper.removeItem(panels, i, i1);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        ItemStack is = panels.get(i);

        panels.set(i, ItemStack.EMPTY);

        return is;
    }

    @Override
    public void setItem(int i, @NotNull ItemStack stack) {
        if (stack.getFrame() != null) {
            BlockPos pos = stack.getFrame().blockPosition();
            if (this.getLevel() != null) {
                if (this.getLevel().getBlockEntity(pos) instanceof AbstractSolarPanel entity) {
                    this.production.plusEqual(entity.getProduction());
                }
            }
        }

        panels.set(i, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.position().closerThan((Position) getBlockPos(), 8);
    }

    @Override
    public void clearContent() {
        panels.clear();
        fillList();
    }

    @Override
    public void setSustainedInventory(ListTag nbtTags) {
        System.out.println(nbtTags);
    }


    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        System.out.println(ItemStack.of(tag).getItem());

        ListTag list = new ListTag();

        for (int i = 0; i < 9*4; i++) {
            list.add(i, getItem(i).save(new CompoundTag()));
        }

        tag.put("Panels", list);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);


        ListTag tags = (ListTag) tag.get("Panels");

        if (tags != null) {
            for (int i = 0; i < 9 * 4 && i < tags.size(); i++) {
                Tag itemTag = tags.get(i);

                if (itemTag != null) {
                    setItem(i, ItemStack.of((CompoundTag) itemTag));
                } else {
                    setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }
}
