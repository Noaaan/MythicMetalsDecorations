package nourl.mythicmetalsdecorations.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import nourl.mythicmetalsdecorations.blocks.DecorationSet;
import nourl.mythicmetalsdecorations.blocks.Decorations;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlockEntityRenderer;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChests;
import nourl.mythicmetalsdecorations.blocks.chest.ChestTextureLayers;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicMetalsDecorationsClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, MythicChestBlockEntityRenderer::new);

        registerChestModels();

        ClientSpriteRegistryCallback.event(TexturedRenderLayers.CHEST_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            ChestTextureLayers.modelList.forEach(entityModelLayer -> registry.register(entityModelLayer.getId()));
        });

        ChestTextureLayers.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        makeOpaque();
    }

    // Makes custom model blocks see through, like chains
    public void makeOpaque() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                Decorations.ADAMANTITE.getChain(),
                Decorations.AQUARIUM.getChain(),
                Decorations.BANGLUM.getChain(),
                Decorations.BRONZE.getChain(),
                Decorations.CARMOT.getChain(),
                Decorations.CELESTIUM.getChain(),
                Decorations.DURASTEEL.getChain(),
                Decorations.KYBER.getChain(),
                Decorations.HALLOWED.getChain(),
                Decorations.MANGANESE.getChain(),
                Decorations.METALLURGIUM.getChain(),
                Decorations.MIDAS_GOLD.getChain(),
                Decorations.MYTHRIL.getChain(),
                Decorations.ORICHALCUM.getChain(),
                Decorations.OSMIUM.getChain(),
                Decorations.PLATINUM.getChain(),
                Decorations.PROMETHEUM.getChain(),
                Decorations.QUADRILLUM.getChain(),
                Decorations.RUNITE.getChain(),
                Decorations.SILVER.getChain(),
                Decorations.STAR_PLATINUM.getChain(),
                Decorations.STEEL.getChain(),
                Decorations.STORMYX.getChain(),
                Decorations.PALLADIUM.getChain()
        );
    }

    public void registerChestModels() {
        DecorationSet.CHEST_MAP.forEach((name, mythicChestBlock) -> {
            var single = RegHelper.modelLayer("chests/" + name + "_chest");
            var left = RegHelper.modelLayer("chests/" + name + "_chest_left");
            var right = RegHelper.modelLayer("chests/" + name + "_chest_right");

            ChestTextureLayers.modelList.add(single);
            ChestTextureLayers.modelList.add(left);
            ChestTextureLayers.modelList.add(right);

            ChestTextureLayers.chestSpriteList.put(name + ChestType.SINGLE.name(), RegHelper.chestSprite(single.getId()));
            ChestTextureLayers.chestSpriteList.put(name + ChestType.LEFT.name(), RegHelper.chestSprite(left.getId()));
            ChestTextureLayers.chestSpriteList.put(name + ChestType.RIGHT.name(), RegHelper.chestSprite(right.getId()));
        });
    }
}
