package com.mekanism.energy.config;

import com.mekanism.energy.MekaEnergy;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class SolarStorageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue advancedSolarPanel;
    public final CachedFloatingLongValue hybridSolarPanel;
    public final CachedFloatingLongValue ultimateHybridSolarPanel;
    public final CachedFloatingLongValue quantumSolarPanel;

    public final CachedFloatingLongValue unifier;
    public final CachedFloatingLongValue fortuneTransformer;
    public final CachedFloatingLongValue molecularMachine;


    public SolarStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Generator Energy Storage Config. This config is synced from server to client.").push("storage");
        this.advancedSolarPanel = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "advancedSolarPanel", getStorage(100_000));
        this.hybridSolarPanel = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "hybridSolarPanel", getStorage(1_000_000));
        this.ultimateHybridSolarPanel = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "ultimateHybridSolarPanel", getStorage(5_000_000));
        this.quantumSolarPanel = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "quantumSolarPanel", getStorage(10_000_000));

        this.unifier = CachedFloatingLongValue.define(this, builder, "TileEntityUnifier", "unifier", getStorage(8_000_000));
        this.fortuneTransformer = CachedFloatingLongValue.define(this, builder, "FortuneTransformer", "fortuneTransformer", getStorage(8_000_000));
        this.molecularMachine = CachedFloatingLongValue.define(this, builder, "MolecularMachine", "molecularMachine", getStorage(8_000_000));

        builder.pop();
        this.configSpec = builder.build();
    }

    public FloatingLong getStorage(long x) {
        return FloatingLong.createConst(x * 2.5);
    }

    public String getFileName() {
        return MekaEnergy.MODID + "-storage";
    }

    public ForgeConfigSpec getConfigSpec() {
        return this.configSpec;
    }

    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }

    public boolean addToContainer() {
        return false;
    }
}
