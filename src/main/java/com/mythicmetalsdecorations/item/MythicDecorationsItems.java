package com.mythicmetalsdecorations.item;

import com.mythicmetalsdecorations.MythicMetalsDecorations;
import com.mythicmetalsdecorations.utils.RegHelper;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MythicDecorationsItems {
    public static final Item HYDRARGYM_INGOT = new Item(new Item.Settings()
        .group(MythicMetalsDecorations.MYTHICMETALS_DECOR)
        .registryKey(RegHelper.itemKey("hydrargym_ingot"))
        .tab(2)
    );

    public static void init() {
        Registry.register(Registries.ITEM, RegHelper.id("hydrargym_ingot"), HYDRARGYM_INGOT);
    }
}
