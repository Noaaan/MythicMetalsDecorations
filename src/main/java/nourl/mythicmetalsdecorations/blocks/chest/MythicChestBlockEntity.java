package nourl.mythicmetalsdecorations.blocks.chest;

import io.wispforest.owo.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ChestLidAnimator;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.block.ChestAnimationProgress;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import ninjaphenix.container_library.api.inventory.AbstractHandler;
import ninjaphenix.container_library.api.v2.OpenableBlockEntityV2;
import nourl.mythicmetalsdecorations.blocks.Decorations;

public class MythicChestBlockEntity extends ChestBlockEntity implements OpenableBlockEntityV2, ImplementedInventory, ChestAnimationProgress {
    private int inventorySize;
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
            MythicChestBlockEntity.this.onInvOpenOrClose(world, pos, state, oldViewerCount, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (!(player.currentScreenHandler instanceof AbstractHandler)) {
                return false;
            } else {
                Inventory inventory = ((AbstractHandler)player.currentScreenHandler).getInventory();
                return inventory == MythicChestBlockEntity.this
                        || inventory instanceof DoubleInventory && ((DoubleInventory)inventory).isPart(MythicChestBlockEntity.this);
            }
        }
    };


    public MythicChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.inventorySize = ((MythicChestBlock)state.getBlock()).getInventorySize();
        this.inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
    }

    public MythicChestBlockEntity(String name, BlockPos pos, BlockState state, int inventorySize) {
        this(MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, pos, state);
        this.inventorySize = inventorySize;
    }

    public MythicChestBlockEntity(BlockPos pos, BlockState state) {
        this(MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        inventory = list;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int size() {
        return inventorySize;
    }

    @Override
    public boolean canBeUsedBy(ServerPlayerEntity player) {
        return OpenableBlockEntityV2.super.canBeUsedBy(player);
    }

    @Override
    public Text getInventoryTitle() {
        return new TranslatableText("container.mythicmetals_decorations.chest." + ((MythicChestBlock)getCachedState().getBlock()).getChestName());
    }

    @Override
    public Inventory getInventory() {
        return Decorations.ADAMANTITE.getChest().getBlockEntitySource(getCachedState(), world, pos, true).apply(MythicChestBlock.INVENTORY_RETRIEVER).orElseThrow();
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, MythicChestBlockEntity blockEntity) {
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
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }

    }

    static void playSound(World world, BlockPos pos, BlockState state, SoundEvent soundEvent) {
        ChestType chestType = state.get(ChestBlock.CHEST_TYPE);
        if (chestType != ChestType.LEFT) {
            double d = (double)pos.getX() + 0.5;
            double e = (double)pos.getY() + 0.5;
            double f = (double)pos.getZ() + 0.5;
            if (chestType == ChestType.RIGHT) {
                Direction direction = ChestBlock.getFacing(state);
                d += (double)direction.getOffsetX() * 0.5;
                f += (double)direction.getOffsetZ() * 0.5;
            }

            world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    public void onScheduledTick() {
        if (!this.removed) {
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }

    }

    @Override
    public float getAnimationProgress(float tickDelta) {
        return this.lidAnimator.getProgress(tickDelta);
    }
}
