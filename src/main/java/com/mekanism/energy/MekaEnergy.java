package com.mekanism.energy;

import com.mekanism.energy.client.menu.SolarPanelMenu;
import com.mekanism.energy.config.SolarConfig;
import com.mekanism.energy.config.SolarStorageConfig;
import com.mekanism.energy.registers.*;
import com.mekanism.energy.registers.panel.SolarContainerTypes;
import com.mekanism.energy.registers.panel.SolarTileTypes;
import com.mekanism.energy.tab.ModCreativeTab;
import com.mekanism.energy.tiles.panels.AbstractSolarPanel;
import mekanism.common.config.MekanismConfigHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MekaEnergy.MODID)
public class MekaEnergy {
    public static final String MODID = "mekaenergy";

    private static final SolarStorageConfig storage = new SolarStorageConfig();
    private static final SolarConfig solar = new SolarConfig();

    private static MekaEnergy INSTANCE;

    public static MekaEnergy getInstance() {
        return INSTANCE;
    }

    private final RegistryObject<MenuType<SolarPanelMenu<? extends AbstractSolarPanel>>> menuRegistry;

    public RegistryObject<MenuType<SolarPanelMenu<? extends AbstractSolarPanel>>> getMenuRegistry() {
        return menuRegistry;
    }

    private final IEventBus eventBus;

    public IEventBus getEventBus() {
        return eventBus;
    }

    public MekaEnergy() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        menuRegistry = RegistryObject.create(new ResourceLocation(MODID, "solar_menus"), ForgeRegistries.MENU_TYPES);
        this.eventBus = eventBus;

        INSTANCE = this;

        ModContainer container = ModLoadingContext.get().getActiveContainer();
        MekanismConfigHelper.registerConfig(container, storage);
        MekanismConfigHelper.registerConfig(container, solar);

        ItemRegister.ITEMS.register(eventBus);
        BlockRegister.REGISTRY_BLOCK.register(eventBus);

        SolarTileTypes.TILE_ENTITY_TYPES.register(eventBus);
        TileTypes.TILE_ENTITY_TYPES.register(eventBus);
        SolarContainerTypes.CONTAINER_TYPES.register(eventBus);
        TilesRegistry.CONTAINER_TYPES.register(eventBus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(eventBus);

        eventBus.register(this);
    }

    public static SolarStorageConfig getStorage() {
        return storage;
    }

    public static SolarConfig getSolar() {
        return solar;
    }
}
