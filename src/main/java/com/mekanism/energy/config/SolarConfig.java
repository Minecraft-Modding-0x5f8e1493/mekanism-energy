package com.mekanism.energy.config;

import com.mekanism.energy.MekaEnergy;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class SolarConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue advancedSolarPanel;
    public final CachedFloatingLongValue hybridSolarPanel;
    public final CachedFloatingLongValue ultimateHybridSolarPanel;
    public final CachedFloatingLongValue quantumSolarPanel;

    public SolarConfig() {
        final String note = "Peak output for the Solar Generator. Note: It can go higher than this value in some extreme environments.";
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Moremekaenergy Config. This config is synced between server and client.").push(MekaEnergy.MODID);

        this.advancedSolarPanel = CachedFloatingLongValue.define(this, builder, note, "advancedSolarPanel", getEnergy(1));
        this.hybridSolarPanel = CachedFloatingLongValue.define(this, builder, note, "hybridSolarPanel", getEnergy(8));
        this.ultimateHybridSolarPanel = CachedFloatingLongValue.define(this, builder, note, "ultimateHybridSolarPanel", getEnergy(64));
        this.quantumSolarPanel = CachedFloatingLongValue.define(this, builder, note, "quantumSolarPanel", getEnergy(512));

        builder.pop();
        this.configSpec = builder.build();
    }

    private FloatingLong getEnergy(long x) {
        return FloatingLong.createConst(23 * x);
    }

    public String getFileName() {
        return MekaEnergy.MODID;
    }

    public ForgeConfigSpec getConfigSpec() {
        return this.configSpec;
    }

    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
