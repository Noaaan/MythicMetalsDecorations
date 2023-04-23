package nourl.mythicmetalsdecorations.item;

import com.google.common.base.Suppliers;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import nourl.mythicmetals.item.MythicItems;

import java.util.EnumMap;
import java.util.function.Supplier;

@SuppressWarnings("CodeBlock2Expr")
public enum MythicDecorationsArmorMaterials implements ArmorMaterial {

    HYDRARGYM("hydrargym", 30, Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 3);
        map.put(ArmorItem.Type.CHESTPLATE, 8);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.BOOTS, 3);
    }), 22, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicDecorationsItems.HYDRARGYM_INGOT);
    }),
    MANGANESE("manganese_crown", 15, Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.BOOTS, 2);
    }), 7, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0, 0, () -> {
        return Ingredient.ofItems(MythicItems.MANGANESE.getIngot());
    }),
    PLATINUM("platinum_crown", 20, Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.BOOTS, 2);
    }), 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> {
        return Ingredient.ofItems(MythicItems.PLATINUM.getIngot());
    });
    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 12);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.BOOTS, 13);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;

    MythicDecorationsArmorMaterials(
            String name,
            int durabilityMultiplier,
            EnumMap<ArmorItem.Type, Integer> protectionAmounts,
            int enchantability,
            SoundEvent equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredientSupplier)
    {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = Suppliers.memoize(repairIngredientSupplier::get);
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY.get(type) * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts.get(type);
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
