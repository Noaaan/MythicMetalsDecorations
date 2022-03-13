package nourl.mythicmetalsdecorations.utils;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;


/*
 * This is a helper class containing methods for registering various blocks and items.
 * @author  Noaaan
 */
public class RegistryHelper {

    public static Identifier id(String path) {
        return new Identifier(MythicMetalsDecorations.MOD_ID, path);
    }

    public static void item(String path, Item item) {
        Registry.register(Registry.ITEM, id(path), item);
    }


    public static void block(String path, Block block, OwoItemGroup group, int tab) {
        Registry.register(Registry.BLOCK, id(path), block);
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(group).tab(tab)));
    }

    public static void block(String path, Block block, boolean fireproof, OwoItemGroup group, int tab) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(group).tab(tab).fireproof()));
        } else {
            block(path, block, group, tab);
        }
    }
    public static void block(String path, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, id(path), block);
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static void block(String path, Block block, ItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group).fireproof()));
        } else {
            block(path, block, group);
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

}
