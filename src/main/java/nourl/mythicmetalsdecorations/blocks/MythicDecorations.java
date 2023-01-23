package nourl.mythicmetalsdecorations.blocks;

import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;
import nourl.mythicmetalsdecorations.item.MythicDecorationsArmorMaterials;
import nourl.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;

@SuppressWarnings("CodeBlock2Expr")
public class MythicDecorations {
    private static final Identifier STONE_MINING_LEVEL = new Identifier("minecraft:needs_stone_tool");
    private static final Identifier IRON_MINING_LEVEL = new Identifier("minecraft:needs_iron_tool");
    private static final Identifier DIAMOND_MINING_LEVEL = new Identifier("minecraft:needs_diamond_tool");
    private static final Identifier NETHERITE_MINING_LEVEL = new Identifier("fabric:needs_tool_level_4");

    public static final MythicDecorationSet ADAMANTITE = MythicDecorationSet.Builder.begin("adamantite", false)
            .createDefaultSet(6.0F, 12F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(MythicDecorationsCrownMaterials.ADAMANTITE, false)
            .finish();
    public static final MythicDecorationSet AQUARIUM = MythicDecorationSet.Builder.begin("aquarium", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, 54)
            .createCrown(MythicDecorationsCrownMaterials.AQUARIUM, false)
            .finish();
    public static final MythicDecorationSet BANGLUM = MythicDecorationSet.Builder.begin("banglum", false)
            .createDefaultSet(5.0F, 10F, IRON_MINING_LEVEL, 54)
            .createCrown(MythicDecorationsCrownMaterials.BANGLUM, false)
            .finish();
    public static final MythicDecorationSet BRONZE = MythicDecorationSet.Builder.begin("bronze", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 63).finish();
    public static final MythicDecorationSet CARMOT = MythicDecorationSet.Builder.begin("carmot", false)
            .createDefaultSet(5.5F, IRON_MINING_LEVEL, 108)
            .createCrown(MythicDecorationsCrownMaterials.CARMOT, false)
            .finish();
    public static final MythicDecorationSet CELESTIUM = MythicDecorationSet.Builder.begin("celestium", false)
            .createDefaultSet(10F, 25F, NETHERITE_MINING_LEVEL, 216)
            .createCrown(MythicDecorationsCrownMaterials.CELESTIUM, settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).fireproof().rarity(Rarity.RARE);
            })
            .finish();
    public static final MythicDecorationSet DURASTEEL = MythicDecorationSet.Builder.begin("durasteel", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 108)
            .createCrown(MythicDecorationsCrownMaterials.DURASTEEL, false)
            .finish();

    public static final MythicDecorationSet HYDRARGYM = MythicDecorationSet.Builder.begin("hydrargym", false)
            .createRegalSet(MythicDecorationsArmorMaterials.HYDRARGYM)
            .finish();
    public static final MythicDecorationSet HALLOWED = MythicDecorationSet.Builder.begin("hallowed", false)
            .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(MythicDecorationsCrownMaterials.HALLOWED, settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).rarity(Rarity.UNCOMMON);
            })
            .finish();
    public static final MythicDecorationSet KYBER = MythicDecorationSet.Builder.begin("kyber", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, 81)
            .createCrown(MythicDecorationsCrownMaterials.KYBER, false)
            .finish();
    public static final MythicDecorationSet MANGANESE = MythicDecorationSet.Builder.begin("manganese", false)
            .createDefaultSet(3.0F, IRON_MINING_LEVEL, 54)
            .createCrown(MythicDecorationsArmorMaterials.MANGANESE, false)
            .finish();
    public static final MythicDecorationSet METALLURGIUM = MythicDecorationSet.Builder.begin("metallurgium", true)
            .createDefaultSet(55F, 15000F, NETHERITE_MINING_LEVEL, 216)
            .createCrown(MythicDecorationsCrownMaterials.METALLURGIUM, settings -> {
                settings.group(MythicMetalsDecorations.MYTHICMETALS_DECOR).fireproof().rarity(Rarity.RARE);
            })
            .finish();
    public static final MythicDecorationSet MIDAS_GOLD = MythicDecorationSet.Builder.begin("midas_gold", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 81).finish();
    public static final MythicDecorationSet MYTHRIL = MythicDecorationSet.Builder.begin("mythril", false)
            .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(MythicDecorationsCrownMaterials.MYTHRIL, false)
            .finish();
    public static final MythicDecorationSet ORICHALCUM = MythicDecorationSet.Builder.begin("orichalcum", false)
            .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(MythicDecorationsCrownMaterials.ORICHALCUM, false)
            .finish();
    public static final MythicDecorationSet OSMIUM = MythicDecorationSet.Builder.begin("osmium", false)
            .createDefaultSet(4.5F, IRON_MINING_LEVEL, 81)
            .createCrown(MythicDecorationsCrownMaterials.OSMIUM, false)
            .finish();
    public static final MythicDecorationSet PALLADIUM = MythicDecorationSet.Builder.begin("palladium", true)
            .createDefaultSet(6.0F, DIAMOND_MINING_LEVEL, 162)
            .createCrown(MythicDecorationsCrownMaterials.PALLADIUM, true)
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
            .createCrown(MythicDecorationsCrownMaterials.RUNITE, false)
            .finish();
    public static final MythicDecorationSet SILVER = MythicDecorationSet.Builder.begin("silver", false)
            .createDefaultSet(4.0F, STONE_MINING_LEVEL, 54).finish();
    public static final MythicDecorationSet STAR_PLATINUM = MythicDecorationSet.Builder.begin("star_platinum", false)
            .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, 135)
            .createCrown(MythicDecorationsCrownMaterials.STAR_PLATINUM, false)
            .finish();
    public static final MythicDecorationSet STEEL = MythicDecorationSet.Builder.begin("steel", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, 81)
            .createCrown(MythicDecorationsCrownMaterials.STEEL, false)
            .finish();
    public static final MythicDecorationSet STORMYX = MythicDecorationSet.Builder.begin("stormyx", false)
            .createDefaultSet(6.0F, IRON_MINING_LEVEL, 108).finish();


    public static void init() {
        MythicDecorationSet.Builder.register();
    }
}
