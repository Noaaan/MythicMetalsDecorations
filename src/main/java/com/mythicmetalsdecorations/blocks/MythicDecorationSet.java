package com.mythicmetalsdecorations.blocks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mythicmetals.AttributeModifier;
import com.mythicmetalsdecorations.MythicDecorations;
import com.mythicmetalsdecorations.MythicMetalsDecorations;
import com.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import com.mythicmetalsdecorations.blocks.chest.MythicChests;
import com.mythicmetalsdecorations.item.RegalSet;
import com.mythicmetalsdecorations.utils.RegHelper;
import io.wispforest.owo.util.TagInjector;
import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_VALUE;

/**
 * This class is a container which is used for the creation for all the decoration blocks.
 * For creating blocks using this you want to start with looking at the {@link Builder}, which
 * contains all the methods for creating blocks.
 *
 * @author Noaaan
 */
public class MythicDecorationSet {
    private final ChainBlock chain;
    private final MythicChestBlock chest;
    private final RegalSet regalSet;
    private final Item crown;

    private final String name;
    private final boolean fireproof;

    private final Multimap<Block, Identifier> miningLevels;
    public static final Multimap<String, MythicChestBlock> CHEST_MAP = HashMultimap.create();

    /**
     * This constructor collects the smaller constructors from the {@link Builder} and creates a set of blocks.
     * Use {@link Builder#begin(String, boolean) BlockSet.Builder.begin} to begin,
     * and call {@link Builder#finish()} when you are done.
     *
     * @param name         Common name for the entire set of blocks, applies to every block created.
     * @param chain        Contains a vanilla {@link ChainBlock}.
     * @param regalSet     Contains a {@link RegalSet}, a set consisting of armor items.
     * @param crown        Contains a single {@link Item}, which is used for a crown (helmet).
     * @param fireproof    Boolean for creating fireproof block sets.
     * @param miningLevels A map containing all the blocks being registered with their corresponding mining levels.
     */
    private MythicDecorationSet(String name,
                                ChainBlock chain,
                                MythicChestBlock chest,
                                RegalSet regalSet,
                                Item crown,
                                boolean fireproof,
                                Multimap<Block, Identifier> miningLevels) {
        this.name = name;
        this.regalSet = regalSet;
        this.crown = crown;
        this.fireproof = fireproof;

        this.chain = chain;
        this.chest = chest;

        this.miningLevels = miningLevels;

    }

    private void register() {
        if (chain != null)
            RegHelper.chain(name + "_chain", chain, fireproof);
        if (chest != null) {
            RegHelper.chest(name + "_chest", chest, fireproof, MythicMetalsDecorations.MYTHICMETALS_DECOR);
            CHEST_MAP.put(name, chest);
        }
        if (regalSet != null) {
            regalSet.register(name);
        }
        if (crown != null) {
            RegHelper.item(name + "_crown", crown);
        }

        // Inject all the mining levels into their tags.
        miningLevels.forEach((block, level) -> TagInjector.inject(Registries.BLOCK, level, block));
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

    public Item getCrown() {
        return crown;
    }

    /**
     * This is the BlockSet Builder, which is used for constructing new sets of blocks.
     * <p>
     * To begin creating BlockSets you want to call:
     * {@code public static final BlockSet SET_NAME = }{@link Builder#begin(String, boolean) BlockSet.Builder.begin()}
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
     * @author Noaaan
     * @see Builder#begin(String, boolean) Builder.begin
     * @see MythicDecorations
     */
    public static class Builder {

        private static final List<MythicDecorationSet> toBeRegistered = new ArrayList<>();

        private final String name;
        private final boolean fireproof;
        private ChainBlock chain = null;
        private MythicChestBlock chest = null;
        private float currentHardness = -1;
        private float currentResistance = -1;
        private final Multimap<Block, Identifier> miningLevels = HashMultimap.create();
        private final Consumer<AbstractBlock.Settings> settingsProcessor = fabricBlockSettings -> {
        };

        private final Identifier PICKAXE = Identifier.of("mineable/pickaxe");
        private RegalSet regalSet = null;
        private Item crown = null;

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
         * @param name      The name of the new block set
         * @param fireproof Boolean of whether the entire set should be fireproof
         */
        public static Builder begin(String name, boolean fireproof) {
            return new Builder(name, fireproof);
        }

        public static void register() {
            toBeRegistered.forEach(MythicDecorationSet::register);
            toBeRegistered.clear();
        }

        /**
         * Used internally for configuring blocks
         *
         * @param hardness   Determines the breaking time of the block.
         * @param resistance Determines blast resistance of a block.
         * @param sounds     Determines the sounds that blocks play when interacted with.
         */
        private static AbstractBlock.Settings blockSettings(String blockKey, float hardness, float resistance, BlockSoundGroup sounds) {
            return AbstractBlock.Settings.create()
                    .registryKey(RegHelper.blockKey(blockKey))
                    .strength(hardness, resistance)
                    .sounds(sounds)
                    .requiresTool();
        }

        /**
         * Puts an ore, a storage block and an ore in the blockset, with slightly more configurable settings.
         *
         * @param strength    The strength of the ore block.
         * @param miningLevel The mining level of the ore block.
         * @see #strength(float)     The strength of the block. Higher value takes longer to break.
         */
        public Builder createDefaultSet(float strength, Identifier miningLevel, int slots) {
            return strength(strength)
                    .createChain(miningLevel)
                    .createChest(slots, miningLevel);
        }

        /**
         * Puts an ore, a storage block and an ore in the blockset, with slightly more configurable settings.
         *
         * @param strength    The strength of the ore block.
         * @param miningLevel The mining level of the ore block.
         * @see #strength(float)     The strength of the block. Higher value takes longer to break.
         */
        public Builder createDefaultSet(float strength, float resistance, Identifier miningLevel, int slots) {
            return strength(strength, resistance)
                    .createChain(miningLevel)
                    .createChest(slots, miningLevel);
        }

        /**
         * Applies sounds to the block(s) in the set.
         *
         * @param sounds The {@link BlockSoundGroup} which should be played.
         */
        public Builder sounds(BlockSoundGroup sounds) {
            return this;
        }

        /**
         * A simplified method to create a hardness and resistance value from a single int.
         *
         * @param strength The base int value for the following blocks strength.
         * @return hardness, resistance (strength + 1)
         */
        public Builder strength(float strength) {
            return strength(strength, strength + 1);
        }

        /**
         * Gives the block(s) in the set the specified strength.
         *
         * @param hardness   Hardness of the block, determines breaking speed.
         * @param resistance Blast resistance of the block.
         */
        public Builder strength(float hardness, float resistance) {
            this.currentHardness = hardness;
            this.currentResistance = resistance;
            return this;
        }

        /**
         * Creates a chain block.
         *
         * @param miningLevel The mining level of the ore block.
         * @see Builder
         */
        public Builder createChain(Identifier miningLevel) {
            final var settings = blockSettings(this.name + "_chain", currentHardness, currentResistance, BlockSoundGroup.CHAIN);
            settingsProcessor.accept(settings);
            this.chain = new ChainBlock(settings);
            miningLevels.put(chain, miningLevel);
            miningLevels.put(chain, PICKAXE);
            return this;
        }

        /**
         * Creates a Mythic Chest block.
         *
         * @param slots       The amount of slots the chest has.
         * @param miningLevel The mining level required to break the chest.
         * @see Builder
         */
        public Builder createChest(int slots, Identifier miningLevel) {
            final var settings = blockSettings(this.name + "_chest", currentHardness, currentResistance, BlockSoundGroup.METAL);
            settingsProcessor.accept(settings);
            this.chest = new MythicChestBlock(this.name, settings, () -> MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE, slots);
            miningLevels.put(chest, miningLevel);
            miningLevels.put(chest, PICKAXE);
            return this;
        }

        /**
         * Creates a regal set, which is a regular armor set with a crown instead of a helmet
         *
         * @param material The Armor Material used for the set
         * @see Builder
         */
        public Builder createRegalSet(ArmorMaterial material) {
            this.regalSet = new RegalSet(name, material);
            return this;
        }

        public Builder createCrown(ArmorMaterial material, int maxDamage, boolean fireproof) {
            return createCrown(material, maxDamage, fireproof, List.of());
        }

        public Builder createCrown(ArmorMaterial material, int maxDamage, boolean fireproof, List<AttributeModifier> extraAttributes) {
            return createCrown(material, maxDamage, fireproof, Rarity.COMMON, extraAttributes);
        }

        /**
         * Creates a custom crown item, which is just a fancy looking helmet
         *
         * @param material  The Armor Material used for the crown
         * @param fireproof Whether the crown is fireproof
         * @see Builder
         */
        public Builder createCrown(ArmorMaterial material, int maxDamage, boolean fireproof, Rarity rarity, List<AttributeModifier> extraAttributes) {
            var settings = new Item.Settings()
                .registryKey(RegHelper.itemKey(this.name + "_crown"))
                .attributeModifiers(createAttributeModifiers(this.name, material, EquipmentType.HELMET, extraAttributes))
                .component(DataComponentTypes.EQUIPPABLE, EquippableComponent
                    .builder(EquipmentSlot.HEAD)
                    .model(material.assetId())
                    .equipSound(material.equipSound())
                    .build()
                )
                .rarity(rarity)
                .maxDamage(maxDamage)
                .group(MythicMetalsDecorations.MYTHICMETALS_DECOR)
                .tab(2);
            if (fireproof) {
                settings = settings.fireproof();
            }
            this.crown = new Item(settings);
            return this;
        }

        private static AttributeModifiersComponent createAttributeModifiers(String name, ArmorMaterial material, EquipmentType equipmentType, List<AttributeModifier> extraModifiers) {
            int armor = material.defense().getOrDefault(equipmentType, 0);
            double toughness = material.toughness();
            double knockbackResistance = material.knockbackResistance();
            var builder = AttributeModifiersComponent.builder();
            var equipmentSlot = AttributeModifierSlot.forEquipmentSlot(equipmentType.getEquipmentSlot());
            var identifier = Identifier.ofVanilla("armor." + equipmentType.getName());
            builder.add(
                EntityAttributes.ARMOR,
                new EntityAttributeModifier(identifier, armor, ADD_VALUE),
                equipmentSlot
            );
            builder.add(
                EntityAttributes.ARMOR_TOUGHNESS,
                new EntityAttributeModifier(identifier, toughness, ADD_VALUE),
                equipmentSlot
            );
            if (knockbackResistance > 0.0F) {
                builder.add(
                    EntityAttributes.KNOCKBACK_RESISTANCE,
                    new EntityAttributeModifier(identifier, knockbackResistance, ADD_VALUE),
                    equipmentSlot
                );
            }
            extraModifiers.forEach(modifier -> {
                if (modifier.requiredSlot().matches(equipmentType.getEquipmentSlot())) {
                    var id = RegHelper.id(name + "_" + modifier.attribute().getKey().orElseThrow().getValue().getPath());
                    builder.add(
                        modifier.attribute(),
                        new EntityAttributeModifier(id, modifier.value(), modifier.operation()),
                        equipmentSlot
                    );
                }
            });

            return builder.build();
        }

        /**
         * Finishes the creation of the block set, and returns the entire set using the settings declared.
         * For registering the blocks call {@link Builder#register() Builder.register} during mod initialization.
         *
         * @return BlockSet
         */
        public MythicDecorationSet finish() {
            final var set = new MythicDecorationSet(this.name, this.chain, this.chest, this.regalSet, this.crown, this.fireproof, this.miningLevels);
            Builder.toBeRegistered.add(set);
            return set;
        }

    }

}
