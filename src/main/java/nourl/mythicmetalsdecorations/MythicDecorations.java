package nourl.mythicmetalsdecorations;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetalsdecorations.blocks.MythicDecorationSet;
import nourl.mythicmetalsdecorations.item.MythicDecorationsArmorMaterials;
import nourl.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;
import nourl.mythicmetalsdecorations.utils.RegHelper;

@SuppressWarnings("CodeBlock2Expr")
public class MythicDecorations {
    // FIXME - make this within Mythic Metals public or something
    private static final int CROWN_DURA_MOD = 12;

    private static final Identifier STONE_MINING_LEVEL = Identifier.of("minecraft:needs_stone_tool");
    private static final Identifier IRON_MINING_LEVEL = Identifier.of("minecraft:needs_iron_tool");
    private static final Identifier DIAMOND_MINING_LEVEL = Identifier.of("minecraft:needs_diamond_tool");
    private static final Identifier NETHERITE_MINING_LEVEL = Identifier.of("fabric:needs_tool_level_4");

    public static final MythicDecorationSet ADAMANTITE = MythicDecorationSet.Builder.begin("adamantite", false)
            .createDefaultSet(6.0F, 12F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.ADAMANTITE), 30 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet AQUARIUM = MythicDecorationSet.Builder.begin("aquarium", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, 54)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.AQUARIUM), 20 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet BANGLUM = MythicDecorationSet.Builder.begin("banglum", false)
            .createDefaultSet(5.0F, 10F, IRON_MINING_LEVEL, 54)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.BANGLUM), 14 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet BRONZE = MythicDecorationSet.Builder.begin("bronze", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 63).finish();
    public static final MythicDecorationSet CARMOT = MythicDecorationSet.Builder.begin("carmot", false)
            .createDefaultSet(5.5F, IRON_MINING_LEVEL, 108)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.CARMOT), 26 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet CELESTIUM = MythicDecorationSet.Builder.begin("celestium", false)
            .createDefaultSet(10F, 25F, NETHERITE_MINING_LEVEL, 216)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.CELESTIUM), settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).maxDamage(41 * CROWN_DURA_MOD).fireproof().rarity(Rarity.RARE);
            })
            .finish();
    public static final MythicDecorationSet DURASTEEL = MythicDecorationSet.Builder.begin("durasteel", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.DURASTEEL), 25 * CROWN_DURA_MOD, false)
            .finish();

    public static final MythicDecorationSet HYDRARGYM = MythicDecorationSet.Builder.begin("hydrargym", false)
            .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 162)
            .createRegalSet(MythicDecorationsArmorMaterials.HYDRARGYM, 30)
            .finish();

    // TODO - Consider refactoring this
    public static final Block HYDRARGYM_BLOCK = new Block(AbstractBlock.Settings.copy(MythicBlocks.MYTHRIL.getStorageBlock()));
    public static final Item HYDRARGYM_NUGGET = new Item(new Item.Settings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(2));
    public static final MythicDecorationSet HALLOWED = MythicDecorationSet.Builder.begin("hallowed", false)
            .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.HALLOWED), settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).maxDamage(41 * CROWN_DURA_MOD).rarity(Rarity.UNCOMMON);
            })
            .finish();
    public static final MythicDecorationSet KYBER = MythicDecorationSet.Builder.begin("kyber", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, 81)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.KYBER), 21 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet MANGANESE = MythicDecorationSet.Builder.begin("manganese", false)
            .createDefaultSet(3.0F, IRON_MINING_LEVEL, 54)
            .createCrown(RegistryEntry.of(MythicDecorationsArmorMaterials.MANGANESE), 15 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet METALLURGIUM = MythicDecorationSet.Builder.begin("metallurgium", true)
            .createDefaultSet(55F, 15000F, NETHERITE_MINING_LEVEL, 216)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.METALLURGIUM), settings -> {
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
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.MYTHRIL), 31 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet ORICHALCUM = MythicDecorationSet.Builder.begin("orichalcum", false)
            .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.ORICHALCUM), 40 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet OSMIUM = MythicDecorationSet.Builder.begin("osmium", false)
            .createDefaultSet(4.5F, IRON_MINING_LEVEL, 81)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.OSMIUM), 25 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet PALLADIUM = MythicDecorationSet.Builder.begin("palladium", true)
            .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.PALLADIUM), 28 * CROWN_DURA_MOD, true)
            .finish();
    public static final MythicDecorationSet PLATINUM = MythicDecorationSet.Builder.begin("platinum", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, 81)
            .createRegalSet(MythicDecorationsArmorMaterials.PLATINUM, 20)
            .finish();
    public static final MythicDecorationSet PROMETHEUM = MythicDecorationSet.Builder.begin("prometheum", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108).finish();
    public static final MythicDecorationSet QUADRILLUM = MythicDecorationSet.Builder.begin("quadrillum", false)
            .createDefaultSet(5.0F, STONE_MINING_LEVEL, 63).finish();
    public static final MythicDecorationSet RUNITE = MythicDecorationSet.Builder.begin("runite", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.RUNITE), 27 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet SILVER = MythicDecorationSet.Builder.begin("silver", false)
            .createDefaultSet(4.0F, STONE_MINING_LEVEL, 54).finish();
    public static final MythicDecorationSet STAR_PLATINUM = MythicDecorationSet.Builder.begin("star_platinum", false)
            .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 135)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.STAR_PLATINUM), 34 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet STEEL = MythicDecorationSet.Builder.begin("steel", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 81)
            .createCrown(RegistryEntry.of(MythicDecorationsCrownMaterials.STEEL), 20 * CROWN_DURA_MOD, false)
            .finish();
    public static final MythicDecorationSet STORMYX = MythicDecorationSet.Builder.begin("stormyx", false)
            .createDefaultSet(6.0F, IRON_MINING_LEVEL, 108).finish();

    public static void init() {
        MythicDecorationSet.Builder.register();
        RegHelper.item("hydrargym_nugget", HYDRARGYM_NUGGET);
        RegHelper.block("hydrargym_block", HYDRARGYM_BLOCK);
    }
}
