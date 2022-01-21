package nourl.mythicmetalsdecorations.blocks.chest;

import io.wispforest.owo.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import ninjaphenix.container_library.api.v2.OpenableBlockEntityV2;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;

public class MythicChestBlockEntity extends ChestBlockEntity implements OpenableBlockEntityV2, ImplementedInventory {
    private int inventorySize;
    private DefaultedList<ItemStack> inventoryList;


    public MythicChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.inventorySize = ((MythicChestBase) state.getBlock()).getInventorySize();
        this.inventoryList = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
    }

    public MythicChestBlockEntity(BlockPos pos, BlockState state, int inventorySize) {
        this(MythicMetalsDecorations.getBlockEntityType(), pos, state);
        this.inventorySize = inventorySize;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inventoryList;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        inventoryList = list;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x6(syncId, playerInventory);
    }

    @Override
    protected Text getContainerName() {
        return new LiteralText("Chest of size" + inventorySize);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventoryList;
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
    public Inventory getInventory() {
        return this;
    }

    @Override
    public Text getInventoryTitle() {
        return getContainerName();
    }


}
