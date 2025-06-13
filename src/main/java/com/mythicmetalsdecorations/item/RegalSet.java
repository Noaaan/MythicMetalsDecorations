package com.mythicmetalsdecorations.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import com.mythicmetalsdecorations.MythicMetalsDecorations;
import com.mythicmetalsdecorations.utils.RegHelper;
import java.util.Map;
import java.util.function.Consumer;

public class RegalSet {

    //FIXME - Make this public in MM please
    private static final Map<ArmorItem.Type, Integer> BASE_DURABILITY = Map.of(
        ArmorItem.Type.HELMET, 12,
        ArmorItem.Type.CHESTPLATE, 16,
        ArmorItem.Type.LEGGINGS, 15,
        ArmorItem.Type.BOOTS, 13
    );

    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;

    public RegalSet(ArmorMaterial material, int duraMod) {
        this(material, duraMod, settings -> {});
    }

    public RegalSet(ArmorMaterial material, int duraMod, Consumer<Item.Settings> settingsProcessor) {
        this.helmet = this.baseArmorItem(material, duraMod, ArmorItem.Type.HELMET, settingsProcessor);
        this.chestplate = this.baseArmorItem(material, duraMod, ArmorItem.Type.CHESTPLATE, settingsProcessor);
        this.leggings = this.baseArmorItem(material, duraMod, ArmorItem.Type.LEGGINGS, settingsProcessor);
        this.boots = this.baseArmorItem(material, duraMod, ArmorItem.Type.BOOTS, settingsProcessor);
    }


    public ArmorItem baseArmorItem(ArmorMaterial material, int duraMod, ArmorItem.Type type, Consumer<Item.Settings> settingsProcessor) {
        Item.Settings settings = (new Item.Settings())
            .maxDamage(BASE_DURABILITY.get(type) * duraMod)
            .group(MythicMetalsDecorations.MYTHICMETALS_DECOR)
            .tab(2);
        settingsProcessor.accept(settings);
        return this.makeItem(material, type, settings);
    }

    public void register(String name) {
        Registry.register(Registries.ITEM, RegHelper.id(name + "_crown"), helmet);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_chestplate"), chestplate);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_leggings"), leggings);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_boots"), boots);
    }

    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        return new ArmorItem(RegistryEntry.of(material), type, settings);
    }
}
