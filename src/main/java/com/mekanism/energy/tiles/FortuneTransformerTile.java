package com.mekanism.energy.tiles;

import com.mekanism.energy.items.upgrade.Upgrade;
import com.mekanism.energy.registers.BlockRegister;
import com.mekanism.energy.registers.ItemRegister;
import com.mekanism.energy.util.ItemUtils;
import mekanism.api.math.FloatingLong;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.*;

public class FortuneTransformerTile extends AbstractMechanism implements Container {

    public static final int PRODUCTION_ENERGY_COST = 16;

    private final List<ItemStack> items = new ArrayList<>();

    private final List<ItemStack> extendItems = new ArrayList<>();
    private final RegistryAccess.Frozen registryAccess;

    private int stackToBreak = -1;
    public int workingTime = -1;
    public int defaultBreakTicks = 0;


    private final List<AbstractCookingRecipe> recipes = new ArrayList<>();

    // SLOT ID'S
    // 0 - 26 - toWork (Верхняя часть)
    // 27 - 53 - afterWork (Нижняя часть)
    // 54 - 56 - modificators (Модификаторы справа)
    // 57 - workingItem (Кирка)

    public FortuneTransformerTile(BlockPos pos, BlockState state) {
        super(BlockRegister.FORTUNE_TRANSFORMER, pos, state);

        for (int i = 0; i <= 57; i++) {
            items.add(ItemStack.EMPTY);
        }

        for (Recipe<?> recipe : ServerLifecycleHooks.getCurrentServer().getRecipeManager().getRecipes()) {
            if ((recipe.getType().equals(RecipeType.BLASTING) || recipe.getType().equals(RecipeType.SMELTING)) && recipe instanceof AbstractCookingRecipe) {
                recipes.add((AbstractCookingRecipe) recipe);
            }
        }

        this.registryAccess = ServerLifecycleHooks.getCurrentServer().registryAccess();
    }

    private int getNextItemStackId() {
        if (ItemUtils.isEmptyStack(items.get(57)) || !(items.get(57).getItem() instanceof PickaxeItem)) {
            return -1;
        }

        ItemStack pickaxe = items.get(57);

        for (int i = 0; i <= 26; i++) {
            ItemStack stack = items.get(i);

            if (stack.getItem() instanceof BlockItem) {
                BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();

                if (pickaxe.getItem().isCorrectToolForDrops(state)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public FloatingLong getMaxEnergy() {
        return FloatingLong.create(10000);
    }

    @Override
    public void onUpdateServer() {
        super.onUpdateServer();

        // Deleting air-items
        if (!extendItems.isEmpty()) {
            extendItems.removeIf(stack -> stack.getItem().equals(Items.AIR));
        }

        // If something in extend items...
        if (!extendItems.isEmpty()) {
            List<ItemStack> outputList = ItemUtils.getListOfRange(items,27, 53);

            // Trying to add items
            for (ItemStack itemStack : extendItems) {
                ItemUtils.addStackToArray(outputList, itemStack);
            }

            // Setting output list
            for (int i = 27; i <= 53; i++) {
                items.set(i, outputList.get(i - 27));
            }

            // Clearing extends
            extendItems.clear();

            // If output list more than needs, putting extends in extends
            if (outputList.size() > 9*3) {
                for (int i = 9*3; i < outputList.size(); i++) {
                    extendItems.add(outputList.get(i));
                }
            }

            return;
        }


        // Checking pickaxe
        if (ItemUtils.isEmptyStack(items.get(57)) || !(items.get(57).getItem() instanceof PickaxeItem)) {
            return;
        }

        ItemStack pickaxe = items.get(57);

        // Getting a stack ID
        if (stackToBreak == -1) {
            stackToBreak = getNextItemStackId();
            if (stackToBreak == -1) {
                return;
            }
        }

        // Check stack is not empty
        if (ItemUtils.isEmptyStack(items.get(stackToBreak))) {
            stackToBreak = -1;
            return;
        }

        // Getting a breaking stack
        ItemStack breaking = items.get(stackToBreak);
        if (!(breaking.getItem() instanceof BlockItem blockItem)) {
            items.set(stackToBreak, ItemStack.EMPTY);
            return;
        }

        // Matching breakTicks and fortune level
        int defaultBreakTicks = (int) blockItem.getDestroySpeed(pickaxe, blockItem.getBlock().defaultBlockState()) * 20;

        int fortuneLevel = 0;

        // Applying pickaxe enchants
        if (pickaxe.isEnchanted()) {
            for (Map.Entry<Enchantment, Integer> entry : pickaxe.getAllEnchantments().entrySet()) {
                if (entry.getKey().equals(Enchantments.BLOCK_FORTUNE)) {
                    fortuneLevel += entry.getValue();
                }
            }
        }

        // Getting updates
        boolean infiniteFlag = false;
        boolean smeltingFlag = false;
        boolean stackFlag = false;
        for (ItemStack stack : ItemUtils.filterEmptySlots(ItemUtils.getListOfRange(items, 54, 56))) {
            if (stack.getItem() instanceof Upgrade) {
                if (stack.getItem().equals(ItemRegister.UPGRADE_INFINITE.asItem())) {
                    infiniteFlag = true;
                } else if (stack.getItem().equals(ItemRegister.UPGRADE_SMELTING.asItem())) {
                    smeltingFlag = true;
                } else if (stack.getItem().equals(ItemRegister.UPGRADE_STACK.asItem())) {
                    stackFlag = true;
                    defaultBreakTicks = defaultBreakTicks * (breaking.getCount()/4);
                }
            }
        }

        // Wait to work
        if (workingTime == -1) {
            workingTime = defaultBreakTicks;
            return;
        } else {
            int decreases = PRODUCTION_ENERGY_COST;
            if (infiniteFlag) {
                decreases = decreases / 4;
            }

            if (stackFlag) {
                decreases = decreases / 4;
            }

            FloatingLong energy = this.getEnergy(0);

            if (energy.getValue() >= decreases) {
                this.setEnergy(0, FloatingLong.create(energy.getValue() - decreases));
                System.out.println((workingTime/defaultBreakTicks) * 1000);
                workingTime -= 1;
            }

            if (energy.isZero()) {
                return;
            }

            if (workingTime <= 0) { // If working time is ends, continue...
                workingTime = -1;
            } else { // Else, rerun tick
                return;
            }
        }

        // Adding results
        List<ItemStack> outputList = ItemUtils.getListOfRange(items,27, 53);
        List<ItemStack> breaks = new ArrayList<>();
        if (stackFlag) {
            for (int i = 0; i < breaking.getCount(); i++) {
                List<ItemStack> temp = blockItem.getBlock().defaultBlockState().getDrops(new LootParams.Builder((ServerLevel) this.level)
                        .withParameter(LootContextParams.TOOL, pickaxe)
                        .withParameter(LootContextParams.ORIGIN,
                                new Vec3(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ()))
                        .withLuck(fortuneLevel));
                for (ItemStack itemStack : temp) {
                    ItemUtils.addStackToArray(breaks, itemStack);
                }
            }

            items.set(stackToBreak, ItemStack.EMPTY);
        } else {
            breaks = blockItem.getBlock().defaultBlockState().getDrops(new LootParams.Builder((ServerLevel) this.level)
                            .withParameter(LootContextParams.TOOL, pickaxe)
                            .withParameter(LootContextParams.ORIGIN,
                                    new Vec3(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ()))
                    .withLuck(fortuneLevel));

            int newCount = items.get(stackToBreak).getCount() - 1;
            if (newCount <= 0) {
                items.set(stackToBreak, ItemStack.EMPTY);
            } else {
                items.get(stackToBreak).setCount(newCount);
            }
        }

        // Smelting...
        if (smeltingFlag) {
            breaks = smeltAll(breaks);
        }

        // Adding breaks
        for (ItemStack itemStack : breaks) {
            ItemUtils.addStackToArray(outputList, itemStack);
        }

        // Checking to extends
        if (outputList.size() > (53 - 27)) {
            for (int i = (53 - 27) - 1; i < outputList.size(); i++) {
                extendItems.add(outputList.get(i));
            }
        }

        for (int i = 27; i <= 53; i++) {
            items.set(i, outputList.get(i - 27));
        }
        // DONE!
    }

    private @NotNull List<ItemStack> smeltAll(List<ItemStack> toSmelt) {
        toSmelt = ItemUtils.filterEmptySlots(toSmelt);

        List<ItemStack> result = new ArrayList<>();
        for (ItemStack stack : toSmelt) {
            boolean isSmelted = false;
            for (AbstractCookingRecipe recipe : recipes) {
                if (recipe.getIngredients().get(0).test(stack)) {
                    ItemStack singleResult = recipe.getResultItem(registryAccess).copy();

                    for (int i = 0; i < stack.getCount(); i++) {
                        ItemUtils.addStackToArray(result, singleResult);
                    }

                    isSmelted = true;
                    break;
                }

            }

            if (!isSmelted) {
                ItemUtils.addStackToArray(result, stack);
            }
        }

        return result;
    }

    private void fillList() {
        for (int i = 0; i <= 57; i++) {
            items.add(ItemStack.EMPTY);
        }
    }

    @Override
    public int getContainerSize() {
        return 57;
    }

    @Override
    public boolean isEmpty() {
        return items.stream().anyMatch((r) -> r.equals(ItemStack.EMPTY));
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return items.get(i);
    }

    @Override
    public @NotNull ItemStack removeItem(int i, int i1) {
        return ContainerHelper.removeItem(items, i, i1);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int i) {
        ItemStack is = items.get(i);

        items.set(i, ItemStack.EMPTY);

        return is;
    }

    @Override
    public void setItem(int i, @NotNull ItemStack itemStack) {
        items.set(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.position().closerThan((Position) getBlockPos(), 8);
    }

    @Override
    public void clearContent() {
        items.clear();
        fillList();
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        ListTag list = new ListTag();
        for (int i = 0; i <= 57; i++) {
            list.add(i, getItem(i).save(new CompoundTag()));
        }

        tag.put("Items", list);
    }

    @Override
    public boolean canPlaceItem(int id, @NotNull ItemStack stack) {
        // Result slots
        if (id >= 27 && id <= 53) {
            return false;
        } else if (id >= 54 && id <= 56) { // Modification slots
            return stack.getItem() instanceof Upgrade;
        } else if (id == 57) { // Pickaxe slot
            return stack.getItem() instanceof PickaxeItem;
        } else {
            // Check if stack is block
            return stack.getItem() instanceof BlockItem;
        }
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);

        ListTag tags = (ListTag) tag.get("Items");

        if (tags != null) {
            for (int i = 0; i <= 57 && i < tags.size(); i++) {
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
