package com.mythicmetalsdecorations.item;

import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;
import com.mythicmetalsdecorations.MythicMetalsDecorations;

public class MythicDecorationsItems implements ItemRegistryContainer {
    public static final Item HYDRARGYM_INGOT = new Item(new Item.Settings().group(MythicMetalsDecorations.MYTHICMETALS_DECOR).tab(2));

}
