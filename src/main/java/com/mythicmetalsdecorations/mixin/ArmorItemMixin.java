package com.mythicmetalsdecorations.mixin;

import com.mythicmetals.entity.MythicEntityAttributes;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import com.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;
import com.mythicmetalsdecorations.utils.RegHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {

    @Inject(method = "method_56689", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void mythicmetalsdecorations$constructor(RegistryEntry<ArmorMaterial> entry, ArmorItem.Type type, CallbackInfoReturnable<AttributeModifiersComponent> cir, int i, float f, AttributeModifiersComponent.Builder builder, AttributeModifierSlot slot) {

        if (entry.value().equals(MythicDecorationsCrownMaterials.AQUARIUM)) {
         mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("aquarium_crown_underwater_mining_bonus"), EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, 2.0f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, slot);
        }
        if (entry.value().equals(MythicDecorationsCrownMaterials.CELESTIUM)) {
            mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("celestium_crown_speed_bonus"), EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, slot);
            mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("celestium_crown_attack_bonus"), EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
        }
        if (entry.value().equals(MythicDecorationsCrownMaterials.STAR_PLATINUM)) {
            mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("star_platinum_crown_speed_bonus"), EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
        }
        if (entry.value().equals(MythicDecorationsCrownMaterials.CARMOT)) {
            mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("carmot_crown_health_bonus"), EntityAttributes.GENERIC_MAX_HEALTH, 2.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
            mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("carmot_crown_carmot_shield"), MythicEntityAttributes.CARMOT_SHIELD, 5.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
        }
        if (entry.value().equals(MythicDecorationsCrownMaterials.PALLADIUM) && type.getEquipmentSlot().equals(EquipmentSlot.HEAD)) {
            mythicmetalsdecorations$armorMapBuilder(builder, RegHelper.id("palladium_crown_lava_visibility_bonus"), AdditionalEntityAttributes.LAVA_VISIBILITY, 8.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
        }
    }

    @Unique
    private static void mythicmetalsdecorations$armorMapBuilder(AttributeModifiersComponent.Builder builder, Identifier id, RegistryEntry<EntityAttribute> attributeEntry, float value, EntityAttributeModifier.Operation operation, AttributeModifierSlot slot) {
        builder.add(attributeEntry, new EntityAttributeModifier(id, value, operation), slot);
    }
}
