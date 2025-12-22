package com.mythicmetalsdecorations.item;

import com.mythicmetals.armor.MythicArmorMaterials;
import com.mythicmetalsdecorations.utils.RegHelper;
import net.minecraft.item.equipment.ArmorMaterial;

public class MythicDecorationsCrownMaterials {

    public static final ArmorMaterial ADAMANTITE = fromMaterial(MythicArmorMaterials.ADAMANTITE, "adamantite_crown");
    public static final ArmorMaterial AQUARIUM = fromMaterial(MythicArmorMaterials.AQUARIUM, "aquarium_crown");
    public static final ArmorMaterial BANGLUM = fromMaterial(MythicArmorMaterials.BANGLUM, "banglum_crown");
    public static final ArmorMaterial CARMOT = fromMaterial(MythicArmorMaterials.CARMOT, "carmot_crown");
    public static final ArmorMaterial CELESTIUM = fromMaterial(MythicArmorMaterials.CELESTIUM, "celestium_crown");
    public static final ArmorMaterial DURASTEEL = fromMaterial(MythicArmorMaterials.DURASTEEL, "durasteel_crown");
    public static final ArmorMaterial HALLOWED = fromMaterial(MythicArmorMaterials.HALLOWED, "hallowed_crown");
    public static final ArmorMaterial KYBER = fromMaterial(MythicArmorMaterials.KYBER, "kyber_crown");
    public static final ArmorMaterial METALLURGIUM = fromMaterial(MythicArmorMaterials.METALLURGIUM, "metallurgium_crown");
    public static final ArmorMaterial MYTHRIL = fromMaterial(MythicArmorMaterials.MYTHRIL, "mythril_crown");
    public static final ArmorMaterial ORICHALCUM = fromMaterial(MythicArmorMaterials.ORICHALCUM, "orichalcum_crown");
    public static final ArmorMaterial OSMIUM = fromMaterial(MythicArmorMaterials.OSMIUM, "osmium_crown");
    public static final ArmorMaterial PALLADIUM = fromMaterial(MythicArmorMaterials.PALLADIUM, "palladium_crown");
    public static final ArmorMaterial RUNITE = fromMaterial(MythicArmorMaterials.RUNITE, "runite_crown");
    public static final ArmorMaterial STAR_PLATINUM = fromMaterial(MythicArmorMaterials.STAR_PLATINUM, "star_platinum_crown");
    public static final ArmorMaterial STEEL = fromMaterial(MythicArmorMaterials.STEEL, "steel_crown");

    public static ArmorMaterial fromMaterial(ArmorMaterial material, String name) {
        return new ArmorMaterial(
            material.durability(),
            material.defense(),
            material.enchantmentValue(),
            material.equipSound(),
            material.toughness(),
            material.knockbackResistance(),
            material.repairIngredient(),
            RegHelper.equipmentAsset(name)
        );
    }
}
