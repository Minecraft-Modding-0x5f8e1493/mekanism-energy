package com.mekanism.energy.client.gui;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.client.menu.PanelUnifierMenu;
import com.mekanism.energy.tiles.PanelUnifierTile;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class PanelUnifierScreen extends AbstractContainerScreen<MekanismTileContainer<PanelUnifierTile>> {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(MekaEnergy.MODID, "textures/gui/gui_unifier.png");

    protected int xSize = 256;
    protected int ySize = 256;

    private int guiLeft;
    private int guiTop;

    public PanelUnifierScreen(MekanismTileContainer<PanelUnifierTile> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    public void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        // Nothing to do.
    }

    @Override
    protected void renderBg(GuiGraphics gui, float v, int i, int i1) {
        this.renderBackground(gui);
        gui.blit(BACKGROUND, guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public @NotNull PanelUnifierMenu getMenu() {
        return (PanelUnifierMenu) this.menu;
    }
}