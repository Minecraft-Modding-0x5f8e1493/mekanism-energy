package com.mekanism.energy.registers;

import com.mekanism.energy.MekaEnergy;
import com.mekanism.energy.items.Module;
import com.mekanism.energy.items.upgrade.Upgrade;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;

public class ItemRegister {
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(MekaEnergy.MODID);

    public static final ItemRegistryObject<Item> MATTER = ITEMS.register("matter");
    public static final ItemRegistryObject<Item> GAMMA_URANIUM = ITEMS.register("gamma_uranium");

    public static final ItemRegistryObject<Item> ORE_MODULEX1 = ITEMS.register("ore_modulex1", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX2 = ITEMS.register("ore_modulex2", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX3 = ITEMS.register("ore_modulex3", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX4 = ITEMS.register("ore_modulex4", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX5 = ITEMS.register("ore_modulex5", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX6 = ITEMS.register("ore_modulex6", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX7 = ITEMS.register("ore_modulex7", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX8 = ITEMS.register("ore_modulex8", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX9 = ITEMS.register("ore_modulex9", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX10 = ITEMS.register("ore_modulex10", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> ORE_MODULEX11 = ITEMS.register("ore_modulex11", () -> {
        return new Module(new Item.Properties());
    });

    public static final ItemRegistryObject<Item> UPGRADE_INFINITE = ITEMS.register("upgrade_infinite", () -> {
        return new Upgrade(new Item.Properties(), () -> {
            return "tooltip.mekaenergy.upgrade.infinite";
        });
    });

    public static final ItemRegistryObject<Item> UPGRADE_SMELTING = ITEMS.register("upgrade_smelting", () -> {
        return new Upgrade(new Item.Properties(), () -> {
            return "tooltip.mekaenergy.upgrade.smelting";
        });
    });

    public static final ItemRegistryObject<Item> UPGRADE_STACK = ITEMS.register("upgrade_stack", () -> {
        return new Upgrade(new Item.Properties(), () -> {
            return "tooltip.mekaenergy.upgrade.stack";
        });
    });
}
