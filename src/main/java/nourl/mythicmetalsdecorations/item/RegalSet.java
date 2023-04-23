package nourl.mythicmetalsdecorations.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;
import nourl.mythicmetalsdecorations.utils.RegHelper;

import java.util.List;
import java.util.function.Consumer;

public class RegalSet {

    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;
    private final List<Item> armorSet;

    public RegalSet(ArmorMaterial material) {
        this(material, (settings) -> {
        });
    }

    public RegalSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        this.helmet = this.baseArmorItem(material, ArmorItem.Type.HELMET, settingsProcessor);
        this.chestplate = this.baseArmorItem(material, ArmorItem.Type.CHESTPLATE, settingsProcessor);
        this.leggings = this.baseArmorItem(material, ArmorItem.Type.LEGGINGS, settingsProcessor);
        this.boots = this.baseArmorItem(material, ArmorItem.Type.BOOTS, settingsProcessor);
        this.armorSet = List.of(this.helmet, this.chestplate, this.leggings, this.boots);
    }

    public ArmorItem baseArmorItem(ArmorMaterial material, ArmorItem.Type type, Consumer<Item.Settings> settingsProcessor) {
        OwoItemSettings settings = (new OwoItemSettings()).group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(2);
        settingsProcessor.accept(settings);
        return this.makeItem(material, type, settings);
    }

    public void register(String name) {
        Registry.register(Registries.ITEM, RegHelper.id(name + "_crown"), this.helmet);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_chestplate"), this.chestplate);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_leggings"), this.leggings);
        Registry.register(Registries.ITEM, RegHelper.id(name + "_boots"), this.boots);
    }

    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        return new ArmorItem(material, type, settings);
    }

    public List<Item> getArmorSet() {
        return armorSet;
    }
}
