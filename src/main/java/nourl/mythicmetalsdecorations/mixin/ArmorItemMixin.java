package nourl.mythicmetalsdecorations.mixin;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import nourl.mythicmetalsdecorations.item.MythicDecorationsCrownMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
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
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_MOVEMENT_SPEED, "Speed bonus", 0.08F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        if (material == MythicDecorationsCrownMaterials.STAR_PLATINUM) {
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_ATTACK_DAMAGE, "Attack bonus", 0.5F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicDecorationsCrownMaterials.CARMOT) {
            mythicmetalsdecorations$armorMapBuilder(uUID, EntityAttributes.GENERIC_MAX_HEALTH, "Health bonus", 2.0F, EntityAttributeModifier.Operation.ADDITION);
        }
    }

    private void mythicmetalsdecorations$armorMapBuilder(UUID uUID, EntityAttribute attributes, String name, float value, EntityAttributeModifier.Operation operation) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        this.attributeModifiers.forEach(builder::put);
        builder.put(attributes,
                new EntityAttributeModifier(uUID, name, value, operation));
        this.attributeModifiers = builder.build();
    }
}
