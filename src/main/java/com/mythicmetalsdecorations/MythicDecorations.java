package com.mythicmetalsdecorations;

import com.mythicmetals.AttributeModifier;
import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetalsdecorations.blocks.MythicDecorationSet;
import com.mythicmetalsdecorations.item.MythicDecorationsArmorMaterials;
import com.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;
import com.mythicmetalsdecorations.utils.RegHelper;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import java.util.List;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.*;
import static net.minecraft.entity.attribute.EntityAttributes.*;

public class MythicDecorations {
    private static final int CROWN_DURA_MOD = ArmorSet.getBaseDurability().get(EquipmentType.HELMET);

    private static final Identifier STONE_MINING_LEVEL = Identifier.of("minecraft:needs_stone_tool");
    private static final Identifier IRON_MINING_LEVEL = Identifier.of("minecraft:needs_iron_tool");
    private static final Identifier DIAMOND_MINING_LEVEL = Identifier.of("minecraft:needs_diamond_tool");
    private static final Identifier NETHERITE_MINING_LEVEL = Identifier.of("minecraft:needs_netherite_tool");

    public static final MythicDecorationSet ADAMANTITE = MythicDecorationSet.Builder.begin("adamantite", false)
        .createDefaultSet(6.0F, 12F, DIAMOND_MINING_LEVEL, 162)
        .createCrown(MythicDecorationsCrownMaterials.ADAMANTITE, 30 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet AQUARIUM = MythicDecorationSet.Builder.begin("aquarium", false)
        .createDefaultSet(4.0F, IRON_MINING_LEVEL, 54)
        .createCrown(MythicDecorationsCrownMaterials.AQUARIUM, 20 * CROWN_DURA_MOD, false, List.of(
            createModifier(SUBMERGED_MINING_SPEED, 1.0, ADD_MULTIPLIED_TOTAL)
        ))
        .finish();
    public static final MythicDecorationSet BANGLUM = MythicDecorationSet.Builder.begin("banglum", false)
        .createDefaultSet(5.0F, 10F, IRON_MINING_LEVEL, 54)
        .createCrown(MythicDecorationsCrownMaterials.BANGLUM, 14 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet BRONZE = MythicDecorationSet.Builder.begin("bronze", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, 63).finish();
    public static final MythicDecorationSet CARMOT = MythicDecorationSet.Builder.begin("carmot", false)
        .createDefaultSet(5.5F, IRON_MINING_LEVEL, 108)
        .createCrown(MythicDecorationsCrownMaterials.CARMOT, 26 * CROWN_DURA_MOD, false, List.of(
            createModifier(MAX_HEALTH, 2.0, ADD_VALUE),
            createModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE)
        ))
        .finish();
    public static final MythicDecorationSet CELESTIUM = MythicDecorationSet.Builder.begin("celestium", false)
        .createDefaultSet(10F, 25F, NETHERITE_MINING_LEVEL, 216)
        .createCrown(MythicDecorationsCrownMaterials.CELESTIUM, 41 * CROWN_DURA_MOD, true, Rarity.RARE, List.of(
            createModifier(MOVEMENT_SPEED, 0.1, ADD_MULTIPLIED_TOTAL),
            createModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE)
        ))
        .finish();
    public static final MythicDecorationSet DURASTEEL = MythicDecorationSet.Builder.begin("durasteel", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108)
        .createCrown(MythicDecorationsCrownMaterials.DURASTEEL, 25 * CROWN_DURA_MOD, false)
        .finish();

    public static final MythicDecorationSet HYDRARGYM = MythicDecorationSet.Builder.begin("hydrargym", false)
        .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 162)
        .createRegalSet(MythicDecorationsArmorMaterials.HYDRARGYM)
        .finish();

    // TODO - Consider refactoring this
    public static final Block HYDRARGYM_BLOCK = new Block(AbstractBlock.Settings.copy(MythicBlocks.MYTHRIL.getStorageBlock()).registryKey(RegHelper.blockKey("hydrargym_block")));
    public static final Item HYDRARGYM_NUGGET = new Item(new Item.Settings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(2).registryKey(RegHelper.itemKey("hydrargym_nugget")));
    public static final MythicDecorationSet HALLOWED = MythicDecorationSet.Builder.begin("hallowed", false)
        .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
        .createCrown(MythicDecorationsCrownMaterials.HALLOWED, 41 * CROWN_DURA_MOD, false, Rarity.UNCOMMON, List.of())
        .finish();
    public static final MythicDecorationSet KYBER = MythicDecorationSet.Builder.begin("kyber", false)
        .createDefaultSet(4.0F, IRON_MINING_LEVEL, 81)
        .createCrown(MythicDecorationsCrownMaterials.KYBER, 21 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet MANGANESE = MythicDecorationSet.Builder.begin("manganese", false)
        .createDefaultSet(3.0F, IRON_MINING_LEVEL, 54)
        .createCrown(MythicDecorationsArmorMaterials.MANGANESE, 15 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet METALLURGIUM = MythicDecorationSet.Builder.begin("metallurgium", true)
        .createDefaultSet(55F, 15000F, NETHERITE_MINING_LEVEL, 216)
        .createCrown(MythicDecorationsCrownMaterials.METALLURGIUM, CROWN_DURA_MOD * 69, true, Rarity.RARE, List.of())
        .finish();
    public static final MythicDecorationSet MIDAS_GOLD = MythicDecorationSet.Builder.begin("midas_gold", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, 81).finish();
    public static final MythicDecorationSet MYTHRIL = MythicDecorationSet.Builder.begin("mythril", false)
        .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 162)
        .createCrown(MythicDecorationsCrownMaterials.MYTHRIL, 31 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet ORICHALCUM = MythicDecorationSet.Builder.begin("orichalcum", false)
        .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
        .createCrown(MythicDecorationsCrownMaterials.ORICHALCUM, 40 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet OSMIUM = MythicDecorationSet.Builder.begin("osmium", false)
        .createDefaultSet(4.5F, IRON_MINING_LEVEL, 81)
        .createCrown(MythicDecorationsCrownMaterials.OSMIUM, 25 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet PALLADIUM = MythicDecorationSet.Builder.begin("palladium", true)
        .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
        .createCrown(MythicDecorationsCrownMaterials.PALLADIUM, 28 * CROWN_DURA_MOD, true, List.of(
            createModifier(AdditionalEntityAttributes.LAVA_VISIBILITY, 2.0, ADD_VALUE),
            createModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE)
        ))
        .finish();
    public static final MythicDecorationSet PLATINUM = MythicDecorationSet.Builder.begin("platinum", false)
        .createDefaultSet(4.0F, IRON_MINING_LEVEL, 81)
        .createRegalSet(MythicDecorationsArmorMaterials.PLATINUM)
        .finish();
    public static final MythicDecorationSet PROMETHEUM = MythicDecorationSet.Builder.begin("prometheum", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108).finish();
    public static final MythicDecorationSet QUADRILLUM = MythicDecorationSet.Builder.begin("quadrillum", false)
        .createDefaultSet(5.0F, STONE_MINING_LEVEL, 63).finish();
    public static final MythicDecorationSet RUNITE = MythicDecorationSet.Builder.begin("runite", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108)
        .createCrown(MythicDecorationsCrownMaterials.RUNITE, 27 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet SILVER = MythicDecorationSet.Builder.begin("silver", false)
        .createDefaultSet(4.0F, STONE_MINING_LEVEL, 54).finish();
    public static final MythicDecorationSet STAR_PLATINUM = MythicDecorationSet.Builder.begin("star_platinum", false)
        .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 135)
        .createCrown(MythicDecorationsCrownMaterials.STAR_PLATINUM, 34 * CROWN_DURA_MOD, false, List.of(
            createModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE)
        ))
        .finish();
    public static final MythicDecorationSet STEEL = MythicDecorationSet.Builder.begin("steel", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, 81)
        .createCrown(MythicDecorationsCrownMaterials.STEEL, 20 * CROWN_DURA_MOD, false)
        .finish();
    public static final MythicDecorationSet STORMYX = MythicDecorationSet.Builder.begin("stormyx", false)
        .createDefaultSet(6.0F, IRON_MINING_LEVEL, 108).finish();

    public static void init() {
        MythicDecorationSet.Builder.register();
        RegHelper.item("hydrargym_nugget", HYDRARGYM_NUGGET);
        RegHelper.block("hydrargym_block", HYDRARGYM_BLOCK);
    }

    private static AttributeModifier createModifier(RegistryEntry<EntityAttribute> attribute, double value, EntityAttributeModifier.Operation operation) {
        return new AttributeModifier(
            attribute, value, operation, AttributeModifierSlot.HEAD
        );
    }
}
