package nourl.mythicmetalsdecorations.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.BlockItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;
import nourl.mythicmetalsdecorations.blocks.MythicDecorationSet;
import nourl.mythicmetalsdecorations.blocks.MythicDecorations;
import nourl.mythicmetalsdecorations.blocks.chest.ChestTextureLayers;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlockEntityRenderer;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChests;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicMetalsDecorationsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, MythicChestBlockEntityRenderer::new);

        // Create chest models and sprites
        createChestModelsAndSprites();

        // Register them
        ChestTextureLayers.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        makeOpaque();

        HandledScreens.register(MythicMetalsDecorations.MYTHIC_CHEST_SCREEN_HANDLER_TYPE, MythicChestScreen::new);
    }

    // Makes custom model blocks see through, like chains
    public void makeOpaque() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MythicDecorations.ADAMANTITE.getChain(),
                MythicDecorations.AQUARIUM.getChain(),
                MythicDecorations.BANGLUM.getChain(),
                MythicDecorations.BRONZE.getChain(),
                MythicDecorations.CARMOT.getChain(),
                MythicDecorations.CELESTIUM.getChain(),
                MythicDecorations.DURASTEEL.getChain(),
                MythicDecorations.KYBER.getChain(),
                MythicDecorations.HALLOWED.getChain(),
                MythicDecorations.MANGANESE.getChain(),
                MythicDecorations.METALLURGIUM.getChain(),
                MythicDecorations.MIDAS_GOLD.getChain(),
                MythicDecorations.MYTHRIL.getChain(),
                MythicDecorations.ORICHALCUM.getChain(),
                MythicDecorations.OSMIUM.getChain(),
                MythicDecorations.PLATINUM.getChain(),
                MythicDecorations.PROMETHEUM.getChain(),
                MythicDecorations.QUADRILLUM.getChain(),
                MythicDecorations.RUNITE.getChain(),
                MythicDecorations.SILVER.getChain(),
                MythicDecorations.STAR_PLATINUM.getChain(),
                MythicDecorations.STEEL.getChain(),
                MythicDecorations.STORMYX.getChain(),
                MythicDecorations.PALLADIUM.getChain()
        );
    }

    public void createChestModelsAndSprites() {
        MythicDecorationSet.CHEST_MAP.forEach((name, mythicChestBlock) -> {
            var single = RegHelper.modelLayer("mythic_chests/" + name + "_chest");
            var left = RegHelper.modelLayer("mythic_chests/" + name + "_chest_left");
            var right = RegHelper.modelLayer("mythic_chests/" + name + "_chest_right");

            ChestTextureLayers.modelList.add(single);
            ChestTextureLayers.modelList.add(left);
            ChestTextureLayers.modelList.add(right);

            ChestTextureLayers.chestSpriteMap.put(name + ChestType.SINGLE.name(), RegHelper.chestSprite(single.getId()));
            ChestTextureLayers.chestSpriteMap.put(name + ChestType.LEFT.name(), RegHelper.chestSprite(left.getId()));
            ChestTextureLayers.chestSpriteMap.put(name + ChestType.RIGHT.name(), RegHelper.chestSprite(right.getId()));

            BuiltinItemRendererRegistry.INSTANCE.register(mythicChestBlock, new MythicChestBlockEntityRenderer.ChestItemRenderer());

        });

        // Init chest tooltips
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (stack.getItem() != null) {
                if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof MythicChestBlock chest) {
                    lines.add(1, chest.getChestTooltip());
                }

                if (stack.getItem().equals(MythicMetalsDecorations.CROWN_CHISEL)) {
                    lines.add(1, Text.translatable("item.mythicmetals_decorations.crown_chisel.tooltip").formatted(Formatting.GRAY));
                    lines.add(2, Text.translatable("item.mythicmetals_decorations.crown_chisel.tooltip2").formatted(Formatting.GRAY).formatted(Formatting.UNDERLINE));
                    if (!MythicMetals.CONFIG.enableNuggets()) {
                        lines.add(3, Text.translatable("item.mythicmetals_decorations.crown_chisel.disabled").formatted(Formatting.GRAY));
                    }
                }
            }
        });
    }

}
