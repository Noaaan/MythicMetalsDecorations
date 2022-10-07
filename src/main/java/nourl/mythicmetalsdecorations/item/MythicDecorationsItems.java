package nourl.mythicmetalsdecorations.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;

public class MythicDecorationsItems implements ItemRegistryContainer {
    public static final Item HYDRARGYM_INGOT = new Item(new OwoItemSettings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(2));

}
