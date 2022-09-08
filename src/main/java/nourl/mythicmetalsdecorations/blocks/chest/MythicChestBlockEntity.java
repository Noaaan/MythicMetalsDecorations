package nourl.mythicmetalsdecorations.blocks.chest;

import io.wispforest.owo.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ChestLidAnimator;
import net.minecraft.block.entity.LidOpenable;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetalsdecorations.MythicChestScreenHandler;
import nourl.mythicmetalsdecorations.mixin.LockableContainerBlockEntityAccessor;

public class MythicChestBlockEntity extends ChestBlockEntity implements ImplementedInventory, LidOpenable {

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
            MythicChestBlockEntity.this.onInvOpenOrClose(world, pos, state, oldViewerCount, newViewerCount);
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
        return size;
    }

    public String getChestName() {
        return name;
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
        ((LockableContainerBlockEntityAccessor) this).mm$setLock(ContainerLock.fromNbt(nbt));

        if (nbt.contains("CustomName", NbtElement.STRING_TYPE)) {
            this.setCustomName(Text.Serializer.fromJson(nbt.getString("CustomName")));
        }

        if (!this.deserializeLootTable(nbt)) {
            this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
            NbtList nbtList = nbt.getList("Items", 10);

            for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                int j = nbtCompound.getShort("Slot") & Short.MAX_VALUE;
                if (j < this.inventory.size()) {
                    this.inventory.set(j, ItemStack.fromNbt(nbtCompound));
                }
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        ((LockableContainerBlockEntityAccessor) this).mm$getLock().writeNbt(nbt);

        if (this.hasCustomName()) {
            nbt.putString("CustomName", Text.Serializer.toJson(this.getCustomName()));
        }

        if (!this.serializeLootTable(nbt)) {
            NbtList nbtList = new NbtList();

            for (int i = 0; i < this.inventory.size(); i++) {
                ItemStack itemStack = this.inventory.get(i);
                if (!itemStack.isEmpty()) {
                    NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putShort("Slot", (short) i);
                    itemStack.writeNbt(nbtCompound);
                    nbtList.add(nbtCompound);
                }
            }

            if (!nbtList.isEmpty()) {
                nbt.put("Items", nbtList);
            }
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
    public float getAnimationProgress(float tickDelta) {
        return this.lidAnimator.getProgress(tickDelta);
    }
}
