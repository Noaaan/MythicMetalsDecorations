package nourl.mythicmetalsdecorations.blocks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.wispforest.owo.util.TagInjector;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChests;
import nourl.mythicmetalsdecorations.utils.RegHelper;

import java.util.*;
import java.util.function.Consumer;

/**
 * This class is a container which is used for the creation for all the decoration blocks.
 * For creating blocks using this you want to start with looking at the {@link Builder}, which
 * contains all the methods for creating blocks.
 *
 * @author Noaaan
 */
@SuppressWarnings({"unused"})
public class DecorationSet {
    private final ChainBlock chain;
    private final MythicChestBlock chest;

    private final String name;
    private final boolean fireproof;

    private final Multimap<Block, Identifier> miningLevels;
    public static final Multimap<String, MythicChestBlock> CHEST_MAP = HashMultimap.create();

    /**
     * This constructor collects the smaller constructors from the {@link Builder} and creates a set of blocks.
     * Use {@link Builder#begin(String, boolean) BlockSet.Builder.begin} to begin,
     * and call {@link Builder#finish()} when you are done.
     * @param name              Common name for the entire set of blocks, applies to every block created.
     * @param chain             Contains a vanilla {@link ChainBlock}.
     * @param fireproof         Boolean for creating fireproof block sets.
     * @param miningLevels      A map containing all the blocks being registered with their corresponding mining levels.
     */
    private DecorationSet(String name,
                          ChainBlock chain,
                          MythicChestBlock chest,
                          boolean fireproof,
                          Multimap<Block, Identifier> miningLevels) {
        this.name = name;
        this.fireproof = fireproof;

        this.chain = chain;
        this.chest = chest;

        this.miningLevels = miningLevels;

    }

    private void register() {
        if (chain != null)
            RegHelper.chain(name + "_chain", chain, fireproof);
        if (chest != null) {
            RegHelper.chest(name + "_chest", chest, MythicMetalsDecorations.MYTHICMETALS_DECOR, fireproof);
            CHEST_MAP.put(name, chest);
        }

        // Inject all the mining levels into their tags.
        miningLevels.forEach((block, level) -> TagInjector.injectBlocks(level, block));
    }

    /**
     * @return Returns the ore block in the set
     */
    public ChainBlock getChain() {
        return chain;
    }

    /**
     * @return Returns the chest in the set
     */
    public MythicChestBlock getChest() {
        return chest;
    }

    /**
     * This is the BlockSet Builder, which is used for constructing new sets of blocks.
     * <p>
     * To begin creating BlockSets you want to call:
     * {@code public static final BlockSet SETNAME = }{@link Builder#begin(String, boolean) BlockSet.Builder.begin()}
     * where you provide a {@code string} for the name/key, and the {@code fireproof} boolean.
     * <p>
     * When creating blocks it's important to call {@link #strength(float)} before creating a block or any set.
     * This is because the values are grabbed from this method. You can call it multiple times if you wish to
     * specifically tailor the values for individual blocks.
     * <p>
     * When you are finished with adding your blocks to the set,
     * call {@link Builder#finish() Builder.finish} when you are done.
     * If you need any examples on how to apply this builder in practice, see the linked classes.
     *
     * @see Builder#begin(String, boolean) Builder.begin
     * @see Decorations
     * @author Noaaan
     */
    public static class Builder {

        private static final List<DecorationSet> toBeRegistered = new ArrayList<>();

        private final String name;
        private final boolean fireproof;
        private ChainBlock chain = null;
        private MythicChestBlock chest = null;
        private float currentHardness = -1;
        private float currentResistance = -1;
        private final Multimap<Block, Identifier> miningLevels = HashMultimap.create();
        private final Consumer<FabricBlockSettings> settingsProcessor = fabricBlockSettings -> {
        };

        private final Identifier PICKAXE = new Identifier("mineable/pickaxe");

        /**
         * @see #begin(String, boolean)
         */
        private Builder(String name, boolean fireproof) {
            this.name = name;
            this.fireproof = fireproof;
        }

        /**
         * This method begins the creation of a block set.
         * You can add as many blocks as you want in the set
         * Call {@link Builder#finish()} when you are done.
         *
         * @param name          The name of the new block set
         * @param fireproof     Boolean of whether or not the entire set should be fireproof
         */
        public static Builder begin(String name, boolean fireproof) {
            return new Builder(name, fireproof);
        }

        public static void register() {
            toBeRegistered.forEach(DecorationSet::register);
            toBeRegistered.clear();
        }

        /**
         * Used internally for configuring blocks
         * @param hardness      Determines the breaking time of the block.
         * @param resistance    Determines blast resistance of a block.
         * @param sounds        Determines the sounds that blocks play when interacted with.
         */
        private static FabricBlockSettings blockSettings(float hardness, float resistance, BlockSoundGroup sounds) {
            return FabricBlockSettings.of(Material.METAL)
                    .strength(hardness, resistance)
                    .sounds(sounds)
                    .requiresTool();
        }

        /**
         * Puts an ore, a storage block and an ore in the blockset, with slightly more configurable settings.
         * @param strength           The strength of the ore block.
         * @param miningLevel        The mining level of the ore block.
         * @see #strength(float)     The strength of the block. Higher value takes longer to break.
         */
        public Builder createDefaultSet(float strength, Identifier miningLevel, int slots) {
            return  strength(strength)
                    .createChain(miningLevel)
                    .createChest(slots, miningLevel);
        }

        /**
         * Puts an ore, a storage block and an ore in the blockset, with slightly more configurable settings.
         * @param strength           The strength of the ore block.
         * @param miningLevel        The mining level of the ore block.
         * @see #strength(float)     The strength of the block. Higher value takes longer to break.
         */
        public Builder createDefaultSet(float strength, float resistance, Identifier miningLevel, int slots) {
            return  strength(strength, resistance)
                    .createChain(miningLevel)
                    .createChest(slots, miningLevel);
        }

        /**
         * Applies sounds to the block(s) in the set.
         * @param sounds    The {@link BlockSoundGroup} which should be played.
         */
        public Builder sounds(BlockSoundGroup sounds) {
            return this;
        }

        /**
         * A simplified method to create a hardness and resistance value from a single int.
         * @param strength  The base int value for the blocks strength.
         * @return hardness, resistance (strength + 1)
         */
        public Builder strength(float strength) {
            return strength(strength, strength + 1);
        }

        /**
         * Gives the block(s) in the set the specified strength.
         * @param hardness      Hardness of the block, determines breaking speed.
         * @param resistance    Blast resistance of the block.
         */
        public Builder strength(float hardness, float resistance) {
            this.currentHardness = hardness;
            this.currentResistance = resistance;
            return this;
        }

        /**
         * Creates a chain block.
         * @param miningLevel   The mining level of the ore block.
         * @see Builder
         */
        public Builder createChain(Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, BlockSoundGroup.CHAIN);
            settingsProcessor.accept(settings);
            this.chain = new ChainBlock(settings);
            miningLevels.put(chain, miningLevel);
            miningLevels.put(chain, PICKAXE);
            return this;
        }

        /**
         * Creates a Mythic Chest block.
         * @param slots         The amount of slots the chest has.
         * @param miningLevel   The mining level required to break the chest.
         * @see Builder
         */
        public Builder createChest(int slots, Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, BlockSoundGroup.METAL);
            settingsProcessor.accept(settings);
            this.chest = new MythicChestBlock(this.name, settings, () -> MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, slots);
            miningLevels.put(chest, miningLevel);
            miningLevels.put(chest, PICKAXE);
            return this;
        }

        /**
         * Finishes the creation of the block set, and returns the entire set using the settings declared.
         * For registering the blocks call {@link Builder#register() Builder.register} during mod initialization.
         * @return BlockSet
         */
        public DecorationSet finish() {
            final var set = new DecorationSet(this.name, this.chain, this.chest, this.fireproof, this.miningLevels);
            Builder.toBeRegistered.add(set);
            return set;
        }

    }

}
