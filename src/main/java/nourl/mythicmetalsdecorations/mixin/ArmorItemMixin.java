package nourl.mythicmetalsdecorations.mixin;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import nourl.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import java.util.UUID;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {
    @Shadow
    @Final
    private static EnumMap<ArmorItem.Type, UUID> MODIFIERS;
    @Shadow
    @Final
    @Mutable
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void mythicmetalsdecorations$constructor(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, CallbackInfo ci) {
        UUID uUID = MODIFIERS.get(type);

        if (material == MythicDecorationsCrownMaterials.CELESTIUM) {
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_MOVEMENT_SPEED, "Celestium Crown Speed bonus", 0.1F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_ATTACK_DAMAGE, "Celestium Crown Attack bonus", 1.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicDecorationsCrownMaterials.STAR_PLATINUM) {
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_ATTACK_DAMAGE, "Star Platinum Crown Attack bonus", 1.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicDecorationsCrownMaterials.CARMOT) {
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_MAX_HEALTH, "Carmot Health bonus", 2.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicDecorationsCrownMaterials.PALLADIUM && type.getEquipmentSlot().equals(EquipmentSlot.HEAD)) {
            mythicmetalsdecorations$armorMapBuilder(uUID, AdditionalEntityAttributes.LAVA_VISIBILITY, "Palladium Crown Lava visibility bonus", 8.0F, EntityAttributeModifier.Operation.ADDITION);
        }
    }

    @Unique
    private void mythicmetalsdecorations$armorMapBuilder(UUID uUID, EntityAttribute attributes, String name, float value, EntityAttributeModifier.Operation operation) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        this.attributeModifiers.forEach(builder::put);
        builder.put(attributes,
                new EntityAttributeModifier(uUID, name, value, operation));
        this.attributeModifiers = builder.build();
    }
}
