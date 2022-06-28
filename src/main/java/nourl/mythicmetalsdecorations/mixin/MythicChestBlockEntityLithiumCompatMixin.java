package nourl.mythicmetalsdecorations.mixin;

import me.jellysquid.mods.lithium.api.inventory.LithiumInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MythicChestBlockEntity.class)
public abstract class MythicChestBlockEntityLithiumCompatMixin implements LithiumInventory {

    @Shadow
    private int size;

    @Shadow
    private DefaultedList<ItemStack> inventory;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public DefaultedList<ItemStack> getInventoryLithium() {
        return this.inventory;
    }
}
