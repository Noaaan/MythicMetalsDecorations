package nourl.mythicmetalsdecorations.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @ModifyVariable(method = "getInventoryAt(Lnet/minecraft/world/World;DDD)Lnet/minecraft/inventory/Inventory;", at = @At(value = "RETURN", shift = At.Shift.BEFORE))
    private static Inventory mythicmetalsdecorations$getMythicDoubleChest(Inventory value, World world, double x, double y, double z) {

        var pos = new BlockPos(x, y, z);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        BlockState state = world.getBlockState(pos);

        if (blockEntity instanceof Inventory inventory) {
            if (inventory instanceof ChestBlockEntity && blockEntity.getCachedState().getBlock() instanceof MythicChestBlock block) {
                value = MythicChestBlock.getInventory(block, state, world, pos, true);
            }
        }
        return value;
    }
}
