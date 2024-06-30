package com.mekanism.energy.util;

import mekanism.common.util.EnumUtils;
import mekanism.common.util.VoxelShapeUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.block.Block.box;

public class Utils {

    public static Component empty = new Component() {
        @Override
        public @NotNull Style getStyle() {
            return Style.EMPTY;
        }

        @Override
        public @NotNull ComponentContents getContents() {
            return ComponentContents.EMPTY;
        }

        @Override
        public @NotNull List<Component> getSiblings() {
            return new ArrayList<>();
        }

        @Override
        public @NotNull FormattedCharSequence getVisualOrderText() {
            return FormattedCharSequence.EMPTY;
        }
    };

    public static final VoxelShape[] BLOCK_PANEL;

    static {
        BLOCK_PANEL = new VoxelShape[EnumUtils.HORIZONTAL_DIRECTIONS.length];

        VoxelShapeUtils.setShape(VoxelShapeUtils.combine(
                box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        ), BLOCK_PANEL);
    }
}
