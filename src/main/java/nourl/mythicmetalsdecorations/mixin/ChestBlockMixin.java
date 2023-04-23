package nourl.mythicmetalsdecorations.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {

    @Inject(method = "getInventory", at = @At("RETURN"), cancellable = true)
    private static void mythicmetalsdecorations$getInventory(ChestBlock block, BlockState state, World world, BlockPos pos, boolean ignoreBlocked, CallbackInfoReturnable<Inventory> cir) {
        if (world.getBlockState(pos).getBlock() instanceof MythicChestBlock chest) {
            cir.setReturnValue(MythicChestBlock.getInventory(chest, state, world, pos, ignoreBlocked));
        }
    }
}
