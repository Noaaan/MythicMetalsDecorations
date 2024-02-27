package nourl.mythicmetalsdecorations.utils;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.mixin.EntityModelLayersAccessor;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;


/*
 * This is a helper class containing methods for registering various blocks and items.
 * @author  Noaaan
 */
public class RegHelper {

    public static Identifier id(String path) {
        return new Identifier(MythicMetalsDecorations.MOD_ID, path);
    }

    public static void item(String path, Item item) {
        Registry.register(Registries.ITEM, id(path), item);
    }


    public static void chest(String path, Block block, OwoItemGroup group) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(group).tab(1)));
    }

    public static void chest(String path, Block block, boolean fireproof, OwoItemGroup group) {
        if (fireproof) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(group).tab(1).fireproof()));
        } else {
            chest(path, block, group);
        }
    }

    public static void chain(String path, Block block) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(0)));
    }

    public static void chain(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(0).fireproof()));
        }
        else chain(path, block);
    }

    public static void blockEntityType(String path, BlockEntityType<?> type) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, id(path), type);
    }

    @Environment(EnvType.CLIENT)
    public static EntityModelLayer modelLayer(String name, String layer) {
        EntityModelLayer result = new EntityModelLayer(id(name), layer);
        EntityModelLayersAccessor.getLAYERS().add(result);
        return result;
    }

    @Environment(EnvType.CLIENT)
    public static EntityModelLayer modelLayer(String name) {
        return modelLayer(name, "main");
    }

    @Environment(EnvType.CLIENT)
    public static SpriteIdentifier chestSprite(Identifier id) {
        return new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, id);
    }

    public static void block(String path, Block block) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(2)));
    }
}
