package com.mythicmetalsdecorations.item;

import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetalsdecorations.utils.RegHelper;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import java.util.Map;

@SuppressWarnings("CodeBlock2Expr")
public class MythicDecorationsArmorMaterials {

    public static final ArmorMaterial HYDRARGYM = new ArmorMaterial(30, Map.of(
        EquipmentType.HELMET, 3,
        EquipmentType.CHESTPLATE, 8,
        EquipmentType.LEGGINGS, 6,
        EquipmentType.BOOTS, 3), 22, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2, 0, decoRepairTag("hydrargym"), RegHelper.equipmentAsset("hydrargym"));
    public static final ArmorMaterial MANGANESE = new ArmorMaterial(15, Map.of(
        EquipmentType.HELMET, 2,
        EquipmentType.CHESTPLATE, 5,
        EquipmentType.LEGGINGS, 4,
        EquipmentType.BOOTS, 2), 7, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0, 0, repairTag("manganese"), RegHelper.equipmentAsset("manganese_crown"));
    public static final ArmorMaterial PLATINUM = new ArmorMaterial(20, Map.of(
        EquipmentType.HELMET, 2,
        EquipmentType.CHESTPLATE, 6,
        EquipmentType.LEGGINGS, 5,
    EquipmentType.BOOTS, 2), 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, repairTag("platinum"), RegHelper.equipmentAsset("platinum"));

    private static TagKey<Item> repairTag(String material) {
        return TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/" + material));
    }

    @SuppressWarnings("SameParameterValue")
    private static TagKey<Item> decoRepairTag(String material) {
        return TagKey.of(RegistryKeys.ITEM, RegHelper.id("ingots/" + material));
    }
}
