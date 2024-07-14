package com.mekanism.energy.client.gui.panel;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.client.menu.SolarPanelMenu;
import com.mekanism.energy.tiles.panels.AbstractSolarPanel;
import com.mekanism.energy.util.Utils;
import mekanism.api.math.FloatingLong;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.registries.GeneratorsContainerTypes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;

public class SolarPanelScreenTest<P extends AbstractSolarPanel> extends AbstractContainerScreen<MekanismTileContainer<P>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MekaEnergy.MODID, "textures/gui/gui_solar_panel.png");
    private static final ResourceLocation ELEMENTS = new ResourceLocation(MekaEnergy.MODID, "textures/gui/gui_elements.png");

    private final MekanismTileContainer<P> container;
    private final Inventory inv;
    private final P tile;

    //private final SolarPanelMenu menuHandler;

    protected int xSize = 214;
    protected int ySize = 210;
    private int guiLeft;
    private int guiTop;

    public SolarPanelScreenTest(MekanismTileContainer<P> container, Inventory inv) {
        super(container, inv, Utils.empty);

        //this.menuHandler = new SolarPanelMenu(container.getType(), container.getStateId());


        this.container = container;
        this.inv = inv;
        this.tile = container.getTileEntity();
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }


    @Override
    public void render(GuiGraphics gui, int p_283661_, int p_281248_, float p_281886_) {
        super.render(gui, p_283661_, p_281248_, p_281886_);
        setText(gui, getCharge(), 0);
        setText(gui, getMaxOutput(), 10);
        setText(gui, getOutput(), 20);
        isEnergy(gui);
        renderTooltip(gui, p_283661_, p_281248_);
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        // Nothing to do.
    }

    @Override
    protected void renderBg(GuiGraphics gui, float p_97788_, int p_97789_, int p_97790_) {
        this.renderBackground(gui);
        gui.blit(TEXTURE, guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    public void setText(@NotNull GuiGraphics gui, String energy, int height) {
        gui.drawString(this.minecraft.font, energy, this.guiLeft + 30, this.guiTop + 23 + height, DyeColor.LIGHT_BLUE.getTextColor());
    }

    public String getCharge() {
        return "Stored: " + EnergyDisplay.of(tile.getEnergyContainer()).getTextComponent().getString();
    }

    public String getMaxOutput() {
        return MekanismLang.MAX_OUTPUT.translateColored(EnumColor.AQUA, tile.getPeakOutput()
                        .multiply(4)
                        .divide(10)
                        .intValue())
                .getString();
    }

    public String getOutput() {
        return GeneratorsLang.OUTPUT_RATE_SHORT.translateColored(EnumColor.GRAY, tile.getProductionRate()
                        .divide(2.5)
                        .intValue())
                .getString();
    }

    public void isEnergy(GuiGraphics gui) {
        FloatingLong multiply = tile.getEnergyContainer().getEnergy().divide(tile.getEnergyContainer().getMaxEnergy()).multiply(129);

        gui.blit(ELEMENTS, this.guiLeft + 46, guiTop + 67, 0, 0, multiply.intValue(), 5);

        if (tile.getActive()) {
            gui.blit(ELEMENTS, this.guiLeft + 36, guiTop + 66, 129, 0, 5, 85);
        }
    }

    @Override
    public @NotNull SolarPanelMenu<P> getMenu() {
        return (SolarPanelMenu<P>) this.menu;
    }
}
