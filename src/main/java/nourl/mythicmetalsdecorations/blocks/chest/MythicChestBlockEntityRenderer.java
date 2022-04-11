package nourl.mythicmetalsdecorations.blocks.chest;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import nourl.mythicmetalsdecorations.blocks.DecorationSet;
import nourl.mythicmetalsdecorations.blocks.Decorations;

import java.util.HashMap;
import java.util.Map;

/**
 * Mostly a Vanilla Copy, but with changes to fit my rendering
 */
public class MythicChestBlockEntityRenderer implements BlockEntityRenderer<MythicChestBlockEntity> {
    private static final String BASE = "bottom";
    private static final String LID = "lid";
    private static final String LATCH = "lock";
    private final ModelPart singleChestLid;
    private final ModelPart singleChestBase;
    private final ModelPart singleChestLatch;
    private final ModelPart doubleChestLeftLid;
    private final ModelPart doubleChestLeftBase;
    private final ModelPart doubleChestLeftLatch;
    private final ModelPart doubleChestRightLid;
    private final ModelPart doubleChestRightBase;
    private final ModelPart doubleChestRightLatch;
    private static final Dilation D = new Dilation(0.01F);


    public MythicChestBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Actually use my own rendering
        ModelPart modelSingle = getSingleTexturedModelData().createModel();
        this.singleChestBase = modelSingle.getChild(BASE);
        this.singleChestLid = modelSingle.getChild(LID);
        this.singleChestLatch = modelSingle.getChild(LATCH);
        ModelPart modelLeft = getLeftDoubleTexturedModelData().createModel();
        this.doubleChestLeftBase = modelLeft.getChild(BASE);
        this.doubleChestLeftLid = modelLeft.getChild(LID);
        this.doubleChestLeftLatch = modelLeft.getChild(LATCH);
        ModelPart modelRight = getRightDoubleTexturedModelData().createModel();
        this.doubleChestRightBase = modelRight.getChild(BASE);
        this.doubleChestRightLid = modelRight.getChild(LID);
        this.doubleChestRightLatch = modelRight.getChild(LATCH);


    }

    public static TexturedModelData getSingleTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(BASE, ModelPartBuilder.create().uv(0, 19).cuboid(1.0f, 0.0f, 1.0f, 14.0f, 10.0f, 14.0f), ModelTransform.NONE);
        modelPartData.addChild(LID, ModelPartBuilder.create().uv(0, 0).cuboid(1.0f, 0.0f, 0.0f, 14.0f, 5.0f, 14.0f, D), ModelTransform.pivot(0.0f, 9.0f, 1.0f));
        modelPartData.addChild(LATCH, ModelPartBuilder.create().uv(0, 0).cuboid(7.0f, -1.0f, 15.0f, 2.0f, 4.0f, 1.0f), ModelTransform.pivot(0.0f, 8.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getRightDoubleTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(BASE, ModelPartBuilder.create().uv(0, 19).cuboid(1.0f, 0.0f, 1.0f, 15.0f, 10.0f, 14.0f), ModelTransform.NONE);
        modelPartData.addChild(LID, ModelPartBuilder.create().uv(0, 0).cuboid(1.0f, 0.0f, 0.0f, 15.0f, 5.0f, 14.0f, D), ModelTransform.pivot(0.0f, 9.0f, 1.0f));
        modelPartData.addChild(LATCH, ModelPartBuilder.create().uv(0, 0).cuboid(15.0f, -1.0f, 15.0f, 1.0f, 4.0f, 1.0f), ModelTransform.pivot(0.0f, 8.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getLeftDoubleTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(BASE, ModelPartBuilder.create().uv(0, 19).cuboid(0.0f, 0.0f, 1.0f, 15.0f, 10.0f, 14.0f), ModelTransform.NONE);
        modelPartData.addChild(LID, ModelPartBuilder.create().uv(0, 0).cuboid(0.0f, 0.0f, 0.0f, 15.0f, 5.0f, 14.0f, D), ModelTransform.pivot(0.0f, 9.0f, 1.0f));
        modelPartData.addChild(LATCH, ModelPartBuilder.create().uv(0, 0).cuboid(0.0f, -1.0f, 15.0f, 1.0f, 4.0f, 1.0f), ModelTransform.pivot(0.0f, 8.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MythicChestBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        boolean bl = world != null;
        BlockState blockState = bl ? entity.getCachedState() : Decorations.ADAMANTITE.getChest().getDefaultState().with(MythicChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockState.contains(MythicChestBlock.CHEST_TYPE) ? blockState.get(MythicChestBlock.CHEST_TYPE) : ChestType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractChestBlock abstractChestBlock) {

            boolean isDoubleChest = chestType != ChestType.SINGLE;
            matrices.push();
            float f = blockState.get(MythicChestBlock.FACING).asRotation();
            matrices.translate(0.5, 0.5, 0.5);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-f));
            matrices.translate(-0.5, -0.5, -0.5);

            DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> propertySource;

            if (bl) {
                propertySource = abstractChestBlock.getBlockEntitySource(blockState, world, entity.getPos(), true);
            } else {
                propertySource = DoubleBlockProperties.PropertyRetriever::getFallback;
            }

            // Get animation progress from our custom chest block entity
            float g = propertySource.apply(MythicChestBlock.getAnimationProgressRetriever(entity)).get(tickDelta);
            g = 1.0f - g;
            g = 1.0f - g * g * g;
            int i = propertySource.apply(new LightmapCoordinatesRetriever<>()).applyAsInt(light);

            // Get custom chest sprites
            SpriteIdentifier spriteIdentifier = ChestTextureLayers.getChestIdentifier((entity.getChestName()), chestType);
            VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);

            if (isDoubleChest) {
                // Left chest rendering
                if (chestType == ChestType.LEFT) {
                    this.render(matrices, vertexConsumer, this.doubleChestLeftLid, this.doubleChestLeftLatch, this.doubleChestLeftBase, g, i, overlay);
                }
                // Right chest rendering
                else {
                    this.render(matrices, vertexConsumer, this.doubleChestRightLid, this.doubleChestRightLatch, this.doubleChestRightBase, g, i, overlay);
                }
            }
            // Single chest rendering
            else {
                this.render(matrices, vertexConsumer, this.singleChestLid, this.singleChestLatch, this.singleChestBase, g, i, overlay);
            }
            matrices.pop();
        }
    }

    private void render(MatrixStack matrices, VertexConsumer vertices, ModelPart lid, ModelPart latch, ModelPart base, float openFactor, int light, int overlay) {
        latch.pitch = lid.pitch = -(openFactor * 1.5707964f);
        lid.render(matrices, vertices, light, overlay);
        latch.render(matrices, vertices, light, overlay);
        base.render(matrices, vertices, light, overlay);
    }

    /**
     *  An inner class that contains an item renderer for the chests
     *  This class initializes a Chest to ChestBE map when loaded from the chest blocks in {@link DecorationSet#CHEST_MAP}
     */
    public static class ChestItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
        private final Map<Block, MythicChestBlockEntity> betterchestEntityMap = new HashMap<>();

        public ChestItemRenderer() {
            DecorationSet.CHEST_MAP.forEach((s, mythicChestBlock) -> betterchestEntityMap.put(mythicChestBlock, new MythicChestBlockEntity(mythicChestBlock.getChestName(), BlockPos.ORIGIN, mythicChestBlock.getDefaultState(), 0)));
        }

        /**
         *  Used to render the MythicChestBlockEntity on the BlockItem
         */
        public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
            MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(betterchestEntityMap.get(((BlockItem)stack.getItem()).getBlock()), matrices, vertexConsumers, light, overlay);
        }
    }

}
