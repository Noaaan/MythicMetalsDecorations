package com.mythicmetalsdecorations.mixin;

import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {
//
//    @Inject(method = "method_56689", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"), locals = LocalCapture.CAPTURE_FAILSOFT)
//    private static void mythicmetalsdecorations$constructor(RegistryEntry<ArmorMaterial> entry, ArmorItem.Type type, CallbackInfoReturnable<AttributeModifiersComponent> cir, int i, float f, AttributeModifiersComponent.Builder builder, AttributeModifierSlot slot) {
//
//        if (entry.value().equals(MythicDecorationsCrownMaterials.AQUARIUM)) {
//         mythicmetalsdecorations$armorMapBuilder(builder, "aquarium_crown_underwater_mining_bonus", EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, 2.0f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, slot);
//        }
//        if (entry.value().equals(MythicDecorationsCrownMaterials.CELESTIUM)) {
//            mythicmetalsdecorations$armorMapBuilder(builder, "celestium_crown_speed_bonus", EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, slot);
//            mythicmetalsdecorations$armorMapBuilder(builder, "celestium_crown_attack_bonus", EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
//        }
//        if (entry.value().equals(MythicDecorationsCrownMaterials.STAR_PLATINUM)) {
//            mythicmetalsdecorations$armorMapBuilder(builder, "star_platinum_crown_speed_bonus", EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
//        }
//        if (entry.value().equals(MythicDecorationsCrownMaterials.CARMOT)) {
//            mythicmetalsdecorations$armorMapBuilder(builder, "carmot_crown_health_bonus", EntityAttributes.GENERIC_MAX_HEALTH, 2.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
//            mythicmetalsdecorations$armorMapBuilder(builder, "carmot_crown_carmot_shield", MythicEntityAttributes.CARMOT_SHIELD, 5.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
//        }
//        if (entry.value().equals(MythicDecorationsCrownMaterials.PALLADIUM) && type.getEquipmentSlot().equals(EquipmentSlot.HEAD)) {
//            mythicmetalsdecorations$armorMapBuilder(builder, "palladium_crown_burn_time_reduction_bonus", EntityAttributes.GENERIC_BURNING_TIME, -0.25f, ADD_MULTIPLIED_BASE, slot);
//            mythicmetalsdecorations$armorMapBuilder(builder, "palladium_crown_lava_visibility_bonus", AdditionalEntityAttributes.LAVA_VISIBILITY, 8.0F, EntityAttributeModifier.Operation.ADD_VALUE, slot);
//        }
//    }
//
//    @Unique
//    private static void mythicmetalsdecorations$armorMapBuilder(AttributeModifiersComponent.Builder builder, String id, RegistryEntry<EntityAttribute> attributeEntry, float value, EntityAttributeModifier.Operation operation, AttributeModifierSlot slot) {
//        builder.add(attributeEntry, new EntityAttributeModifier(RegHelper.id(id), value, operation), slot);
//    }
}
