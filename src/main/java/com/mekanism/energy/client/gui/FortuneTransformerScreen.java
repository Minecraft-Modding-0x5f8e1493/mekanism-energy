package com.mekanism.energy.client.gui;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.client.menu.FortuneTransformerMenu;
import com.mekanism.energy.tiles.FortuneTransformerTile;
import mekanism.api.math.FloatingLong;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class FortuneTransformerScreen extends AbstractContainerScreen<MekanismTileContainer<FortuneTransformerTile>> {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(MekaEnergy.MODID, "textures/gui/gui_fortune_transformer.png");
    private static final ResourceLocation ELEMENTS = new ResourceLocation(MekaEnergy.MODID, "textures/gui/gui_elements.png");

    protected int xSize = 256;
    protected int ySize = 256;

    private int guiLeft;
    private int guiTop;

    public FortuneTransformerScreen(MekanismTileContainer<FortuneTransformerTile> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    public void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void render(GuiGraphics gui, int p_283661_, int p_281248_, float p_281886_) {
        super.render(gui, p_283661_, p_281248_, p_281886_);

        FortuneTransformerTile tileEntity = this.menu.getTileEntity();

        FloatingLong multiply = tileEntity.getEnergy(0).divide(tileEntity.getMaxEnergy()).multiply(129);

        int breakTick = 0;

        if (tileEntity.defaultBreakTicks != 0 && tileEntity.workingTime != -1) {
            breakTick = tileEntity.workingTime / tileEntity.defaultBreakTicks;
        }

        gui.blit(ELEMENTS, this.guiLeft + 46, guiTop + 73, 0, 0, multiply.intValue(), 5);
        gui.blit(ELEMENTS, this.guiLeft + 46, guiTop + 77, 0, 0, breakTick, 5);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        // Nothing to do
    }

    @Override
    protected void renderBg(@Nonnull GuiGraphics gui, float v, int i, int i1) {
        this.renderBackground(gui);
        gui.blit(BACKGROUND, guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public @NotNull FortuneTransformerMenu getMenu() {
        return (FortuneTransformerMenu) this.menu;
    }
}
