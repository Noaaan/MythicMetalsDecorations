package nourl.mythicmetalsdecorations.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {

    // Ignores MythicChestBlocks when dealing with hoppers - makes them by default use Transfer API
    @Inject(method = "getInventoryAt(Lnet/minecraft/world/World;DDD)Lnet/minecraft/inventory/Inventory;", at = @At("HEAD"), cancellable = true)
    private static void mythicmetalsdeco$hopperIgnoreMythicChest(World world, double x, double y, double z, CallbackInfoReturnable<Inventory> cir) {
        BlockPos blockPos = BlockPos.ofFloored(x, y, z);
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if (block instanceof MythicChestBlock) {
            cir.setReturnValue(null);
        }
    }
}
