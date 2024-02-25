package nourl.mythicmetalsdecorations.blocks.chest;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import nourl.mythicmetalsdecorations.MythicChestScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MythicChestBlock extends ChestBlock implements Waterloggable {

    private final int size;
    private final String name;

    public static final DirectionProperty FACING;
    public static final EnumProperty<ChestType> CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED;

    public static final DoubleBlockProperties.PropertyRetriever<MythicChestBlockEntity, MythicChest> CHEST_RETRIEVER = new DoubleBlockProperties.PropertyRetriever<>() {
        public MythicChest getFromBoth(MythicChestBlockEntity first, MythicChestBlockEntity second) {
            var name = first.hasCustomName()
                    ? first.getCustomName()
                    : second.hasCustomName() ? second.getCustomName() : Text.translatable("text.mythicmetals_decorations.large_chest", second.getCachedState().getBlock().getName());

            return new MythicChest(
                    new DoubleInventory(first, second),
                    name,
                    player -> first.checkUnlocked(player) && second.checkUnlocked(player),
                    player -> {
                        first.checkLootInteraction(player);
                        second.checkLootInteraction(player);
                    });
        }

        public MythicChest getFrom(MythicChestBlockEntity chest) {
            return new MythicChest(chest, chest.hasCustomName() ? chest.getCustomName() : chest.getCachedState().getBlock().getName(), chest::checkUnlocked, chest::checkLootInteraction);
        }

        public MythicChest getFallback() {
            return null;
        }
    };

    public MythicChestBlock(String name, Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, int inventorySize) {
        super(settings, supplier);
        this.size = inventorySize;
        this.name = name;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof MythicChestBlockEntity) {
            ((MythicChestBlockEntity) blockEntity).onScheduledTick();
        }
    }

    public static Direction getFacing(BlockState state) {
        Direction direction = state.get(FACING);
        return state.get(CHEST_TYPE) == ChestType.LEFT ? direction.rotateYClockwise() : direction.rotateYCounterclockwise();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MythicChestBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? checkType(type, MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, MythicChestBlockEntity::clientTick) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!itemStack.hasCustomName()) return;

        if (world.getBlockEntity(pos) instanceof MythicChestBlockEntity chest) {
            chest.setCustomName(itemStack.getName());
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 0);
            var chest = MythicChestBlock.this.getBlockEntitySource(state, world, pos, false).apply(CHEST_RETRIEVER);
            if (chest != null) {
                player.openHandledScreen(
                        new ExtendedScreenHandlerFactory() {
                            @Override
                            public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                                buf.writeInt(chest.inventory.size());
                            }

                            @Override
                            public Text getDisplayName() {
                                return chest.name;
                            }

                            @Override
                            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                                if (!chest.canOpen.test(player)) return null;
                                chest.lootGenerator.accept(player);
                                return new MythicChestScreenHandler(syncId, inv, chest.inventory);
                            }
                        }
                );
            }
        }

        return ActionResult.SUCCESS;
    }

    public int getSize() {
        return size;
    }

    @Override
    public DoubleBlockProperties.PropertySource<? extends MythicChestBlockEntity> getBlockEntitySource(
            BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {

        BiPredicate<WorldAccess, BlockPos> fallbackTester;
        if (ignoreBlocked) {
            fallbackTester = (w, p) -> false;
        } else {
            fallbackTester = MythicChestBlock::isChestBlocked;
        }

        return DoubleBlockProperties.toPropertySource(
                MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, ChestBlock::getDoubleBlockType,
                MythicChestBlock::getFacing, FACING, state, world, pos, fallbackTester
        );
    }

    public String getChestName() {
        return name;
    }

    public static DoubleBlockProperties.PropertyRetriever<ChestBlockEntity, Float2FloatFunction> getAnimationProgressRetriever(final LidOpenable progress) {
        return new DoubleBlockProperties.PropertyRetriever<>() {
            public Float2FloatFunction getFromBoth(ChestBlockEntity chestBlockEntity, ChestBlockEntity chestBlockEntity2) {
                return tickDelta -> Math.max(chestBlockEntity.getAnimationProgress(tickDelta), chestBlockEntity2.getAnimationProgress(tickDelta));
            }

            public Float2FloatFunction getFrom(ChestBlockEntity chestBlockEntity) {
                return chestBlockEntity::getAnimationProgress;
            }

            public Float2FloatFunction getFallback() {
                return progress::getAnimationProgress;
            }
        };
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        CHEST_TYPE = Properties.CHEST_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
    }

    public Text getChestTooltip() {
        return Text.translatable("tooltip.mythicmetals_decorations.chest_size", this.getSize()).formatted(Formatting.GRAY);
    }

    public record MythicChest(Inventory inventory, Text name, Predicate<PlayerEntity> canOpen, Consumer<PlayerEntity> lootGenerator) {
    }

}
