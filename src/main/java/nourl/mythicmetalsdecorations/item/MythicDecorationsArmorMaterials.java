package nourl.mythicmetalsdecorations.item;

import com.google.common.base.Suppliers;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import nourl.mythicmetals.item.MythicItems;

import java.util.function.Supplier;

@SuppressWarnings("CodeBlock2Expr")
public enum MythicDecorationsArmorMaterials implements ArmorMaterial {

    HYDRARGYM("hydrargym", 30, new int[]{3, 6, 8, 3}, 22, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicDecorationsItems.HYDRARGYM_INGOT);
    }),
    MANGANESE("manganese_crown", 15, new int[]{2, 4, 5, 2}, 7, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0, 0, () -> {
        return Ingredient.ofItems(MythicItems.Ingots.MANGANESE_INGOT);
    }),
    PLATINUM("platinum_crown", 20, new int[]{2, 5, 6, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> {
        return Ingredient.ofItems(MythicItems.Ingots.PLATINUM_INGOT);
    });

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 12};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;

    MythicDecorationsArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = Suppliers.memoize(repairIngredientSupplier::get);
    }

    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
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
