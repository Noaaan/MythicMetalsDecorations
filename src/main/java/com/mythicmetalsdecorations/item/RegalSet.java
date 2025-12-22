package com.mythicmetalsdecorations.item;

import com.mythicmetals.armor.ArmorSet;
import com.mythicmetalsdecorations.utils.RegHelper;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.*;

public class RegalSet extends ArmorSet {

    public RegalSet(String name, ArmorMaterial material) {
        super(name, material);
    }

    @Override
    public void register(String name) {
        Registry.register(Registries.ITEM, RegHelper.id(name + "_crown"), helmet);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_chestplate"), chestplate);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_leggings"), leggings);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_boots"), boots);
    }

    @Override
    protected RegistryKey<Item> keyFromType(String name, EquipmentType type) {
        var typeName = switch (type) {
            case HELMET -> "crown";
            case CHESTPLATE -> "chestplate";
            case LEGGINGS -> "leggings";
            case BOOTS -> "boots";
            case BODY -> "body";
        };
        return RegHelper.itemKey(name + "_" + typeName);
    }
}
