package nourl.mythicmetalsdecorations.utils;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.mixin.AccessorEntityModelLayers;
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
        Registry.register(Registry.ITEM, id(path), item);
    }


    public static void chest(String path, Block block, OwoItemGroup group, int tab) {
        Registry.register(Registry.BLOCK, id(path), block);
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(group).tab(tab)));
    }

    public static void chest(String path, Block block, boolean fireproof, OwoItemGroup group, int tab) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(group).tab(tab).fireproof()));
        } else {
            chest(path, block, group, tab);
        }
    }
    public static void chest(String path, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, id(path), block);
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static void chest(String path, Block block, ItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group).fireproof()));
        } else {
            chest(path, block, group);
        }
    }

    public static void chain(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MythicMetalsDecorations.MOD_ID, path), block);
        Registry.register(Registry.ITEM, new Identifier(MythicMetalsDecorations.MOD_ID, path), new BlockItem(block, new Item.Settings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR)));
    }

    public static void chain(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, new Identifier(MythicMetalsDecorations.MOD_ID, path), block);
            Registry.register(Registry.ITEM, new Identifier(MythicMetalsDecorations.MOD_ID, path), new BlockItem(block, new Item.Settings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).fireproof()));
        }
        else chain(path, block);
    }

    public static void blockEntityType(String path, BlockEntityType<?> type) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, id(path), type);
    }

    public static EntityModelLayer modelLayer(String name, String layer) {
        EntityModelLayer result = new EntityModelLayer(id(name), layer);
        AccessorEntityModelLayers.getAllModels().add(result);
        return result;
    }

    @Environment(EnvType.CLIENT)
    public static EntityModelLayer modelLayer(String name) {
        return modelLayer(name, "main");
    }

    @Environment(EnvType.CLIENT)
    public static SpriteIdentifier chestSprite(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.CHEST_ATLAS_TEXTURE, id);
    }

}
