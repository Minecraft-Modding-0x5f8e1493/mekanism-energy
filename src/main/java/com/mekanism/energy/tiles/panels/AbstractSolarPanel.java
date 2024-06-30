package com.mekanism.energy.tiles.panels;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.RelativeSide;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.math.FloatingLong;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.SyncableBoolean;
import mekanism.common.inventory.container.sync.SyncableFloatingLong;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolarPanel extends MekaTileEntityGenerator implements Container {

    protected List<ItemStack> chargingItems;

    protected boolean seesSun;
    protected FloatingLong peakOutput;
    protected FloatingLong lastProductionAmount;
    protected final FloatingLong dischargeRate;
    @WrappingComputerMethod(
            wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = {"getEnergyItem"}, docPlaceholder = "")

    @Nullable
    protected SolarCheck solarCheck;

    protected AbstractSolarPanel(IBlockProvider blockProvider, BlockPos pos, BlockState state,
                                 @Nonnull FloatingLong output,
                                 @Nonnull FloatingLong dischargeRate) {
        super(blockProvider, pos, state, () -> output);

        chargingItems = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            chargingItems.add(i, ItemStack.EMPTY);
        }
        this.dischargeRate = dischargeRate;
        this.peakOutput = FloatingLong.ZERO;
        this.lastProductionAmount = FloatingLong.ZERO;
    }

    private void fillList() {
        for (int i = 0; i < 4; i++) {
            chargingItems.set(i, ItemStack.EMPTY);
        }
    }

    @Nonnull
    public abstract <T extends AbstractSolarPanel> ContainerTypeRegistryObject<MekanismTileContainer<T>> getContainerType();

    public FloatingLong getPeakOutput() {
        return peakOutput;
    }

    @ComputerMethod
    public boolean canSeeSun() {
        return this.seesSun;
    }

    protected void onUpdateServer() {
        super.onUpdateServer();

        // Charging items
        for (int i = 0; i <= 3; i++) {
            ItemStack itemStack = chargingItems.get(i);
            IStrictEnergyHandler itemEnergyHandler = EnergyCompatUtils.getStrictEnergyHandler(itemStack);

            if (itemEnergyHandler != null) {
                if (energyContainer.getEnergy().greaterThan(dischargeRate)) {
                    FloatingLong existEnergy = itemEnergyHandler.insertEnergy(dischargeRate, Action.EXECUTE);
                    if (!existEnergy.isZero()) {
                        energyContainer.extract(dischargeRate.subtract(existEnergy), Action.EXECUTE, AutomationType.INTERNAL);
                    } else {
                        energyContainer.extract(dischargeRate, Action.EXECUTE, AutomationType.INTERNAL);
                    }
                }
            }
        }

        if (this.solarCheck == null) {
            this.recheckSettings();
        }

        this.seesSun = this.checkCanSeeSun();

        if (this.seesSun && MekanismUtils.canFunction(this) && !this.getEnergyContainer().getNeeded().isZero()) {
            this.setActive(true);
            FloatingLong production = this.getProduction();
            this.lastProductionAmount = production.subtract(this.getEnergyContainer().insert(production, Action.EXECUTE, AutomationType.INTERNAL));
        } else {
            this.setActive(false);
            this.lastProductionAmount = FloatingLong.ZERO;
        }

    }

    @Override
    public boolean getActive() {
        return super.getActive();
    }

    protected void recheckSettings() {
        if (this.level != null) {
            this.solarCheck = new SolarCheck(this.level, this.worldPosition);
            this.peakOutput = this.getConfiguredMax().multiply(this.solarCheck.getPeakMultiplier()).multiply(4);
        }
    }

    protected boolean checkCanSeeSun() {
        if (this.solarCheck == null) {
            return false;
        } else {
            this.solarCheck.recheckCanSeeSun();
            return this.solarCheck.canSeeSun();
        }
    }

    public FloatingLong getProduction() {
        if (this.level != null && this.solarCheck != null) {
            float brightness = this.getBrightnessMultiplier(this.level);
            if (brightness < 0) return FloatingLong.ZERO;
            float generationMultiplier = brightness * this.solarCheck.getGenerationMultiplier();
            return this.getConfiguredMax().multiply(generationMultiplier);
        } else {
            return FloatingLong.ZERO;
        }
    }

    protected float getBrightnessMultiplier(@Nonnull Level world) {
        return WorldUtils.getSunBrightness(world, 1.0F);
    }

    protected RelativeSide[] getEnergySides() {
        return new RelativeSide[]{RelativeSide.BOTTOM};
    }

    protected abstract FloatingLong getConfiguredMax();

    public FloatingLong getMaxOutput() {
        return this.peakOutput;
    }

    @ComputerMethod(nameOverride = "getProductionRate")
    public FloatingLong getLastProductionAmount() {
        return this.lastProductionAmount;
    }

    public FloatingLong getProductionRate() {
        return this.lastProductionAmount;
    }

    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableBoolean.create(this::canSeeSun, (value) -> this.seesSun = value));
        container.track(SyncableFloatingLong.create(this::getMaxOutput, (value) -> this.peakOutput = value));
        container.track(SyncableFloatingLong.create(this::getLastProductionAmount, (value) -> this.lastProductionAmount = value));
    }

    protected static class SolarCheck {
        private final boolean needsRainCheck;
        private final float peakMultiplier;
        protected final BlockPos pos;
        protected final Level world;
        protected boolean canSeeSun;

        public SolarCheck(Level world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
            Biome b = this.world.getBiomeManager().getBiome(this.pos).value();
            needsRainCheck = b.getPrecipitationAt(this.pos) != Biome.Precipitation.NONE;
            // Consider the best temperature to be 0.8; biomes that are higher than that
            // will suffer an efficiency loss (semiconductors don't like heat); biomes that are cooler
            // get a boost. We scale the efficiency to around 30% so that it doesn't totally dominate

            float tempEff = 0.3F * (0.8F - b.getBaseTemperature());

            // Treat rainfall as a proxy for humidity; any humidity works as a drag on overall efficiency.
            // As with temperature, we scale it so that it doesn't overwhelm production. Note the signedness
            // on the scaling factor. Also note that we only use rainfall as a proxy if it CAN rain; some dimensions
            // (like the End) have rainfall set, but can't actually support rain.
            float humidityEff = needsRainCheck ? -0.3F * b.getModifiedClimateSettings().downfall() : 0;
            peakMultiplier = 1.0F + tempEff + humidityEff;
        }

        public void recheckCanSeeSun() {
            this.canSeeSun = this.world != null && world.canSeeSky(this.pos.atY(this.pos.getY() + 1));
        }

        public Level getWorld() {
            return world;
        }

        public boolean canSeeSun() {
            return this.canSeeSun;
        }

        public float getPeakMultiplier() {
            return this.peakMultiplier;
        }

        public float getGenerationMultiplier() {
            return (!this.needsRainCheck || !this.world.isRaining() && !this.world.isThundering()) && this.getWorld().isDay() ? this.peakMultiplier : this.peakMultiplier * 0.625F;
        }
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return chargingItems.stream().anyMatch((r) -> r == ItemStack.EMPTY);
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return chargingItems.get(i);
    }

    @Override
    public @NotNull ItemStack removeItem(int i, int i1) {
        return ContainerHelper.removeItem(chargingItems, i, i1);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int i) {
        ItemStack is = chargingItems.get(i);

        chargingItems.set(i, ItemStack.EMPTY);

        return is;
    }

    @Override
    public void setItem(int i, @NotNull ItemStack itemStack) {
        chargingItems.set(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.position().closerThan((Position) getBlockPos(), 8);
    }

    @Override
    public void clearContent() {
        chargingItems.clear();
        fillList();
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        ListTag list = new ListTag();
        for (int i = 0; i <= 3; i++) {
            list.add(i, getItem(i).save(new CompoundTag()));
        }

        tag.put("ChargingItems", list);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);

        ListTag tags = (ListTag) tag.get("ChargingItems");

        if (tags != null) {
            for (int i = 0; i <= 3 && i < tags.size(); i++) {
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
