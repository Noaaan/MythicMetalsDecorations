package nourl.mythicmetalsdecorations;

import io.wispforest.owo.client.screens.ScreenUtils;
import io.wispforest.owo.client.screens.SlotGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;

public class MythicChestScreenHandler extends ScreenHandler {

    private final Inventory chestInventory;
    private final int inventorySize;

    public MythicChestScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf data) {
        this(syncId, playerInventory, new SimpleInventory(data.readInt()));
    }

    public MythicChestScreenHandler(int syncId, PlayerInventory playerInventory, Inventory chestInventory) {
        super(MythicMetalsDecorations.MYTHIC_CHEST_SCREEN_HANDLER_TYPE, syncId);

        this.inventorySize = chestInventory.size();
        this.chestInventory = chestInventory;

        SlotGenerator.begin(this::addSlot, 0, 0)
                .spacing(-18)
                .grid(this.chestInventory, 0, this.chestInventory.size(), 1)
                .playerInventory(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ScreenUtils.handleSlotTransfer(this, slot, this.chestInventory.size());
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public int inventorySize() {
        return this.inventorySize;
    }

    public Inventory chestInventory() {
        return this.chestInventory;
    }
}
