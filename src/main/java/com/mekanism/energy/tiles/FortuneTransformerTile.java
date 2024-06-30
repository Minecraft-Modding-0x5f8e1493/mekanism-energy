package com.mekanism.energy.tiles;

import com.mekanism.energy.registers.BlockRegister;
import com.mekanism.energy.tiles.panels.MekaTileEntityGenerator;
import mekanism.api.math.FloatingLong;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.*;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FortuneTransformerTile extends MekaTileEntityGenerator implements Container {

    private final List<ItemStack> items = new ArrayList<>();

    // SLOT ID'S
    // 0 - 26 - toWork (Верхняя часть)
    // 27 - 53 - afterWork (Нижняя часть)
    // 54 - 56 - modificators (Модификаторы справа)
    // 57 - workingItem (Кирка)

    public FortuneTransformerTile(BlockPos pos, BlockState state) {
        super(BlockRegister.FORTUNE_TRANSFORMER, pos, state, () -> FloatingLong.create(100000));

        for (int i = 0; i <= 57; i++) {
            items.add(ItemStack.EMPTY);
        }
    }

    private void fillList() {
        for (int i = 0; i <= 57; i++) {
            items.add(ItemStack.EMPTY);
        }
    }

    @Override
    protected FloatingLong getProductionRate() {
        return FloatingLong.ZERO; // TODO
    }

    @Override
    public int getContainerSize() {
        return 4*9;
    }

    @Override
    public boolean isEmpty() {
        return items.stream().anyMatch((r) -> r.equals(ItemStack.EMPTY));
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return items.get(i);
    }

    @Override
    public @NotNull ItemStack removeItem(int i, int i1) {
        return ContainerHelper.removeItem(items, i, i1);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        ItemStack is = items.get(i);

        items.set(i, ItemStack.EMPTY);

        return is;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        items.set(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.position().closerThan((Position) getBlockPos(), 8);
    }

    @Override
    public void clearContent() {
        items.clear();
        fillList();
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        ListTag list = new ListTag();
        for (int i = 0; i <= 57; i++) {
            list.add(i, getItem(i).save(new CompoundTag()));
        }

        tag.put("Items", list);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);

        ListTag tags = (ListTag) tag.get("Items");

        if (tags != null) {
            for (int i = 0; i <= 57 && i < tags.size(); i++) {
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
