package nourl.mythicmetalsdecorations.blocks.chest;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ninjaphenix.container_library.api.v2.OpenableBlockEntityProviderV2;
import ninjaphenix.container_library.api.v2.OpenableBlockEntityV2;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class MythicChestBase extends AbstractChestBlock<ChestBlockEntity> implements OpenableBlockEntityProviderV2 {
    private final int inventorySize;
    public static final DirectionProperty FACING;
    public static final EnumProperty<ChestType> CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED;

    public MythicChestBase(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, int inventorySize) {
        super(settings, supplier);
        this.inventorySize = inventorySize;
    }

    @Override
    public ActionResult ncl_onBlockUse(World world, BlockState state, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return OpenableBlockEntityProviderV2.super.ncl_onBlockUse(world, state, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MythicChestBlockEntity(pos, state, inventorySize);
    }

    @Override
    public OpenableBlockEntityV2 getOpenableBlockEntity(World world, BlockState state, BlockPos pos) {
        return OpenableBlockEntityProviderV2.super.getOpenableBlockEntity(world, state, pos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ncl_onBlockUse(world, state, pos, player, hand, hit);
    }

    @Override
    public boolean ncl_cOpenInventory(BlockPos pos, Hand hand, BlockHitResult hit) {

        return OpenableBlockEntityProviderV2.super.ncl_cOpenInventory(pos, hand, hit);
    }

    @Override
    public void ncl_sOpenInventory(World world, BlockState state, BlockPos pos, ServerPlayerEntity player) {

        OpenableBlockEntityProviderV2.super.ncl_sOpenInventory(world, state, pos, player);
    }

    public int getInventorySize() {
        return inventorySize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        return (DoubleBlockProperties.PropertySource<? extends ChestBlockEntity>) getOpenableBlockEntity(world, state, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(CHEST_TYPE);
        builder.add(WATERLOGGED);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        CHEST_TYPE = Properties.CHEST_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
