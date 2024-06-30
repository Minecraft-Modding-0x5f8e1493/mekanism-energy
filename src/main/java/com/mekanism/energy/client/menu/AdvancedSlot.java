package com.mekanism.energy.client.menu;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class AdvancedSlot extends Slot {
    private final Function<ItemStack, Boolean> function;
    private final String message;

    public AdvancedSlot(Container container, int number, int x, int y, Function<ItemStack, Boolean> function) {
        this(container, number, x, y, function, "empty");
    }

    public AdvancedSlot(Container container, int number, int x, int y, Function<ItemStack, Boolean> function, String message) {
        super(container, number, x, y);
        this.function = function;
        this.message = message;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return this.function.apply(stack);
    }
}
