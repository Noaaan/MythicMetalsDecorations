package com.mythicmetalsdecorations.blocks.chest;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.mythicmetalsdecorations.screen.MythicChestScreenHandler;

public class MythicChestBlockEntity extends ChestBlockEntity implements LidOpenable {
    private final int size;
    private final String name;

    private DefaultedList<ItemStack> inventory;

    private final ChestLidAnimator lidAnimator = new ChestLidAnimator();
    private final ViewerCountManager stateManager = new ViewerCountManager() {
        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            MythicChestBlockEntity.playSound(world, pos, state, SoundEvents.BLOCK_CHEST_OPEN);
        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            MythicChestBlockEntity.playSound(world, pos, state, SoundEvents.BLOCK_CHEST_CLOSE);
        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            MythicChestBlockEntity.this.onViewerCountUpdate(world, pos, state, oldViewerCount, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (player.currentScreenHandler instanceof MythicChestScreenHandler handler) {
                var inventory = handler.chestInventory();
                return inventory == MythicChestBlockEntity.this || inventory instanceof DoubleInventory && ((DoubleInventory) inventory).isPart(MythicChestBlockEntity.this);
            } else {
                return false;
            }
        }
    };

    public MythicChestBlockEntity(BlockPos pos, BlockState state) {
        super(MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, pos, state);
        this.name = ((MythicChestBlock) state.getBlock()).getChestName();
        this.size = ((MythicChestBlock) state.getBlock()).getSize();
        this.inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    public DefaultedList<ItemStack> getMythicChestInventory() {
        return inventory;
    }

    @Override
    public int size() {
        return size;
    }

    public String getChestName() {
        return name;
    }

    public static void clientTick(World ignored, BlockPos ignored2, BlockState ignored3, MythicChestBlockEntity blockEntity) {
        blockEntity.lidAnimator.step();
    }

    @Override
    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1) {
            this.lidAnimator.setOpen(data > 0);
            return true;
        } else {
            return super.onSyncedBlockEvent(type, data);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        this.lock = ContainerLock.fromNbt(nbt);

        if (!this.readLootTable(nbt)) {
            this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
            Inventories.readNbt(nbt, this.inventory, lookup);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        this.lock.writeNbt(nbt);

        if (!this.readLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory, lookup);
        }

    }

    static void playSound(World world, BlockPos pos, BlockState state, SoundEvent soundEvent) {
        double x = pos.getX() + .5d;
        double y = pos.getY() + .5d;
        double z = pos.getZ() + .5d;
        world.playSound(null, x, y, z, soundEvent, SoundCategory.BLOCKS, .5f, world.random.nextFloat() * .1f + .9f);
    }

    public void onScheduledTick() {
        if (!this.removed) {
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new MythicChestScreenHandler(syncId, playerInventory, this.size);
    }

    @Override
    public float getAnimationProgress(float tickDelta) {
        return this.lidAnimator.getProgress(tickDelta);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world == null) return false;
        return this.world.getBlockEntity(this.pos) == this && player.canInteractWithBlockAt(this.pos, 4);
    }
}
