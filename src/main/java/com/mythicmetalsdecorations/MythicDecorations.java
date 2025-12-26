package com.mythicmetalsdecorations;

import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetalsdecorations.blocks.MythicDecorationSet;
import com.mythicmetalsdecorations.item.MythicDecorationsArmorMaterials;
import com.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;
import com.mythicmetalsdecorations.utils.RegHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

@SuppressWarnings("CodeBlock2Expr")
public class MythicDecorations {
    private static final int CROWN_DURA_MOD = ArmorSet.getBaseDurability().get(EquipmentType.HELMET);

    private static final Identifier STONE_MINING_LEVEL = Identifier.of("minecraft:needs_stone_tool");
    private static final Identifier IRON_MINING_LEVEL = Identifier.of("minecraft:needs_iron_tool");
    private static final Identifier DIAMOND_MINING_LEVEL = Identifier.of("minecraft:needs_diamond_tool");
    private static final Identifier NETHERITE_MINING_LEVEL = Identifier.of("fabric:needs_tool_level_4");

    public static final MythicDecorationSet ADAMANTITE = MythicDecorationSet.Builder.begin("adamantite", false)
            .createDefaultSet(6.0F, 12F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(MythicDecorationsCrownMaterials.ADAMANTITE, 30 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet AQUARIUM = MythicDecorationSet.Builder.begin("aquarium", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, 54)
            .createCrown(MythicDecorationsCrownMaterials.AQUARIUM, 20 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet BANGLUM = MythicDecorationSet.Builder.begin("banglum", false)
            .createDefaultSet(5.0F, 10F, IRON_MINING_LEVEL, 54)
            .createCrown(MythicDecorationsCrownMaterials.BANGLUM, 14 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet BRONZE = MythicDecorationSet.Builder.begin("bronze", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 63).finish();
    public static final MythicDecorationSet CARMOT = MythicDecorationSet.Builder.begin("carmot", false)
            .createDefaultSet(5.5F, IRON_MINING_LEVEL, 108)
            .createCrown(MythicDecorationsCrownMaterials.CARMOT, 26 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet CELESTIUM = MythicDecorationSet.Builder.begin("celestium", false)
            .createDefaultSet(10F, 25F, NETHERITE_MINING_LEVEL, 216)
            .createCrown(MythicDecorationsCrownMaterials.CELESTIUM, settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).maxDamage(41 * CROWN_DURA_MOD).fireproof().rarity(Rarity.RARE);
            })
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
            .createCrown(MythicDecorationsCrownMaterials.HALLOWED, settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).maxDamage(41 * CROWN_DURA_MOD).rarity(Rarity.UNCOMMON);
            })
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
            .createCrown(MythicDecorationsCrownMaterials.METALLURGIUM, settings -> {
                settings
                    .group(MythicMetalsDecorations.MYTHICMETALS_DECOR)
                    .fireproof()
                    .maxDamage(CROWN_DURA_MOD * 69)
                    .rarity(Rarity.RARE);
            })
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
            .createCrown(MythicDecorationsCrownMaterials.PALLADIUM, 28 * CROWN_DURA_MOD, true)
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
            .createCrown(MythicDecorationsCrownMaterials.STAR_PLATINUM, 34 * CROWN_DURA_MOD, false)
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
}
