package com.mekanism.energy.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Module extends Item {
    private final boolean foil;

    public Module(@NotNull Properties properties) {
        this(properties, true);
    }

    public Module(Properties properties, boolean foil) {
        super(properties
                .fireResistant()
                .setNoRepair());

        this.foil = foil;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return this.foil;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack item, @Nullable Level level, @NotNull List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("tooltip.mekaenergy.module.level").append(item.toString().split("modulex")[1]));
        super.appendHoverText(item, level, components, flag);
    }
}
