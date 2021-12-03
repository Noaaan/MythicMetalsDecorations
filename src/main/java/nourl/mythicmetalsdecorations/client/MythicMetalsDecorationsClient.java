package nourl.mythicmetalsdecorations.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import nourl.mythicmetalsdecorations.blocks.Decorations;

public class MythicMetalsDecorationsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
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

}
