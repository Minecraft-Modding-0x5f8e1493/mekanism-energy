package com.mekanism.energy.registers.panel;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.client.menu.SolarPanelMenu;
import com.mekanism.energy.tiles.panels.HybridSolarPanelTile;
import com.mekanism.energy.tiles.panels.UltimateHybridSolarPanelTile;
import com.mekanism.energy.tiles.panels.AdvancedSolarPanelTile;
import com.mekanism.energy.tiles.panels.QuantumSolarPanelTile;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.container.type.MekanismContainerType;
import mekanism.common.registration.WrappedRegistryObject;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;


public class SolarContainerTypes {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekaEnergy.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<AdvancedSolarPanelTile>> ADVANCED_SOLAR_PANEL
            = CONTAINER_TYPES.register("advanced_solar_panel", AdvancedSolarPanelTile.class, SolarPanelMenu::new);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<HybridSolarPanelTile>> HYBRID_SOLAR_PANEL
            = CONTAINER_TYPES.register("hybrid_solar_panel", HybridSolarPanelTile.class, SolarPanelMenu::new);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<UltimateHybridSolarPanelTile>> ULTIMATE_HYBRID_SOLAR_PANEL
            = CONTAINER_TYPES.register("ultimate_hybrid_solar_panel", UltimateHybridSolarPanelTile.class, SolarPanelMenu::new);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<QuantumSolarPanelTile>> QUANTUM_SOLAR_PANEL
            = CONTAINER_TYPES.register("quantum_solar_panel", QuantumSolarPanelTile.class, SolarPanelMenu::new);
}
