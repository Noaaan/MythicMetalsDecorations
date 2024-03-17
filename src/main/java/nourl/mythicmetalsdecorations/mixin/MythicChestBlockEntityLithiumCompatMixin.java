package nourl.mythicmetalsdecorations.mixin;

import me.jellysquid.mods.lithium.api.inventory.LithiumInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MythicChestBlockEntity.class)
public abstract class MythicChestBlockEntityLithiumCompatMixin implements LithiumInventory {

    @Override
    public int size() {
        MythicChestBlock block = (MythicChestBlock) ((MythicChestBlockEntity) (Object) this).getCachedState().getBlock();
        return block.getSize();
    }

    @Override
    public DefaultedList<ItemStack> getInventoryLithium() {
        return ((MythicChestBlockEntity) (Object) this).getMythicChestInventory();
    }
}
