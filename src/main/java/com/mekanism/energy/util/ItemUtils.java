package com.mekanism.energy.util;

import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemUtils {

    private static final Item emptyItem = Items.AIR;

    public static <T> @NotNull List<T> getListOfRange(List<T> input, int start, int end) {
        List<T> output = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            output.add(input.get(i));
        }

        return output;
    }

    public static <T extends ItemStack> List<T> filterEmptySlots(@NotNull List<T> input) {
        return input.stream().filter(stack -> !stack.getItem().equals(emptyItem)).collect(Collectors.toList());
    }

    public static boolean isEmptyStack(@NotNull ItemStack itemStack) {
        return itemStack.getItem().equals(emptyItem);
    }

    public static <T extends ItemStack> void addStackToArray(@NotNull List<T> array, T stack) {

        if (stack.getItem().equals(Items.AIR)) {
            return;
        }

        for (T item : array) {
            if (item.getItem().equals(stack.getItem())) {
                int appendable = 64 - item.getCount(); // 0 ~ 64

                if (appendable != 0) {
                    if (appendable > stack.getCount()) {
                        item.setCount(item.getCount() + stack.getCount());
                        return;
                    } else {
                        // If appendable <= count in stack
                        stack.setCount(stack.getCount() - appendable);
                        item.setCount(64);
                    }
                }
            }
        }

        if (stack.getCount() != 0) {
            int index = 0;
            boolean founded = false;
            for (T item : array) {
                if (item.getItem().equals(Items.AIR)) {
                    founded = true;
                    break;
                }

                index += 1;
            }

            if (founded) {
                array.set(index, stack);
            } else {
                array.add(stack);
            }
        }

    }
}