package com.mythicmetalsdecorations.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import com.mythicmetals.item.MythicItems;
import com.mythicmetalsdecorations.utils.RegHelper;
import java.util.*;

@SuppressWarnings("CodeBlock2Expr")
public class MythicDecorationsArmorMaterials {

    public static final ArmorMaterial HYDRARGYM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 22, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, () -> {
        return Ingredient.ofItems(MythicDecorationsItems.HYDRARGYM_INGOT);
    }, List.of(layer("hydrargym")), 2.0f, 0.0f);
    public static final ArmorMaterial MANGANESE = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 4,
        ArmorItem.Type.BOOTS, 2), 7, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> {
        return Ingredient.ofItems(MythicItems.MANGANESE.getIngot());
    }, List.of(layer("manganese_crown")), 0.0f, 0.0f);
    public static final ArmorMaterial PLATINUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
    ArmorItem.Type.BOOTS, 2), 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> {
        return Ingredient.ofItems(MythicItems.PLATINUM.getIngot());
    }, List.of(layer("platinum_crown")), 0.0f, 0.0f);

    static ArmorMaterial.Layer layer(String name) {
        return new ArmorMaterial.Layer(RegHelper.id(name));
    }
}
