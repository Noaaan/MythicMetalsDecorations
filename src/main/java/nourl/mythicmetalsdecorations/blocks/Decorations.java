package nourl.mythicmetalsdecorations.blocks;

import net.minecraft.util.Identifier;

public class Decorations {
    private static final Identifier STONE_MINING_LEVEL = new Identifier("minecraft:needs_stone_tool");
    private static final Identifier IRON_MINING_LEVEL = new Identifier("minecraft:needs_iron_tool");
    private static final Identifier DIAMOND_MINING_LEVEL = new Identifier("minecraft:needs_diamond_tool");
    private static final Identifier NETHERITE_MINING_LEVEL = new Identifier("fabric:needs_tool_level_4");

    public static final DecorationSet ADAMANTITE = DecorationSet.Builder.begin("adamantite", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet AQUARIUM = DecorationSet.Builder.begin("aquarium", false)
            .createDefaultSet(5.0f, STONE_MINING_LEVEL)
            .finish();
    public static final DecorationSet BANGLUM = DecorationSet.Builder.begin("banglum", false)
            .createDefaultSet(5.0f, STONE_MINING_LEVEL)
            .finish();
    public static final DecorationSet BRONZE = DecorationSet.Builder.begin("bronze", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet CARMOT = DecorationSet.Builder.begin("carmot", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet CELESTIUM = DecorationSet.Builder.begin("celestium", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet DURASTEEL = DecorationSet.Builder.begin("durasteel", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet HALLOWED = DecorationSet.Builder.begin("hallowed", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet KYBER = DecorationSet.Builder.begin("kyber", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet MANGANESE = DecorationSet.Builder.begin("manganese", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet METALLURGIUM = DecorationSet.Builder.begin("metallurgium", true)
            .createDefaultSet(5.0f, NETHERITE_MINING_LEVEL)
            .finish();
    public static final DecorationSet MIDAS_GOLD = DecorationSet.Builder.begin("midas_gold", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet MYTHRIL = DecorationSet.Builder.begin("mythril", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet ORICHALCUM = DecorationSet.Builder.begin("orichalcum", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet OSMIUM = DecorationSet.Builder.begin("osmium", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet PALLADIUM = DecorationSet.Builder.begin("palladium", true)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet PLATINUM = DecorationSet.Builder.begin("platinum", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet PROMETHEUM = DecorationSet.Builder.begin("prometheum", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet QUADRILLUM = DecorationSet.Builder.begin("quadrillum", false)
            .createDefaultSet(5.0f, STONE_MINING_LEVEL)
            .finish();
    public static final DecorationSet RUNITE = DecorationSet.Builder.begin("runite", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet SILVER = DecorationSet.Builder.begin("silver", false)
            .createDefaultSet(5.0f, STONE_MINING_LEVEL)
            .finish();
    public static final DecorationSet STAR_PLATINUM = DecorationSet.Builder.begin("star_platinum", false)
            .createDefaultSet(5.0f, DIAMOND_MINING_LEVEL)
            .finish();
    public static final DecorationSet STEEL = DecorationSet.Builder.begin("steel", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();
    public static final DecorationSet STORMYX = DecorationSet.Builder.begin("stormyx", false)
            .createDefaultSet(5.0f, IRON_MINING_LEVEL)
            .finish();


    public static void init() {
        DecorationSet.Builder.register();
    }
}
