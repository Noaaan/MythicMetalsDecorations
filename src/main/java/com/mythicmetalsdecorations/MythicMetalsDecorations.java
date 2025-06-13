package com.mythicmetalsdecorations;

import io.wispforest.endec.Endec;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import io.wispforest.owo.serialization.CodecUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import com.mythicmetals.ability.Abilities;
import com.mythicmetalsdecorations.blocks.chest.MythicChests;
import com.mythicmetalsdecorations.config.MythicDecorationsConfig;
import com.mythicmetalsdecorations.item.MythicDecorationsItems;
import com.mythicmetalsdecorations.screen.MythicChestScreenHandler;
import com.mythicmetalsdecorations.utils.RegHelper;

public class MythicMetalsDecorations implements ModInitializer {

    public static final String MOD_ID = "mythicmetals_decorations";
    public static MythicDecorationsConfig CONFIG = MythicDecorationsConfig.createAndLoad();
    public static final OwoItemGroup MYTHICMETALS_DECOR = OwoItemGroup.builder(RegHelper.id("main"),
                    () -> Icon.of(MythicDecorations.CELESTIUM.getChain().asItem().getDefaultStack()))
            .initializer(group -> {
                group.addTab(Icon.of(MythicDecorations.ADAMANTITE.getChain()), "chains", TagKey.of(RegistryKeys.ITEM, RegHelper.id("chains")), false);
                group.addTab(Icon.of(MythicDecorations.MIDAS_GOLD.getChest()), "chests", TagKey.of(RegistryKeys.ITEM, RegHelper.id("chests")), false);
                group.addTab(Icon.of(MythicDecorationsItems.HYDRARGYM_INGOT), "misc", TagKey.of(RegistryKeys.ITEM, RegHelper.id("misc")), false);

                group.addButton(ItemGroupButton.github(group, "https://github.com/Noaaan/MythicMetalsDecorations/issues"));
                group.addButton(ItemGroupButton.curseforge(group, "https://www.curseforge.com/minecraft/mc-mods/mythicmetals-decorations"));
                group.addButton(ItemGroupButton.modrinth(group, "https://modrinth.com/mod/mythicmetals_decorations"));
                group.addButton(ItemGroupButton.discord(group, "https://discord.gg/69cKvQWScC"));
            })
        .disableDynamicTitle()
        .build();

    public static final ScreenHandlerType<MythicChestScreenHandler> MYTHIC_CHEST_SCREEN_HANDLER_TYPE = new ExtendedScreenHandlerType<>(MythicChestScreenHandler::new, CodecUtils.toPacketCodec(Endec.INT));

    public static final Item CROWN_CHISEL = new Item(new Item.Settings().group(MYTHICMETALS_DECOR).tab(2));

    @Override
    public void onInitialize() {
        MythicDecorations.init();
        MythicChests.init();
        FieldRegistrationHandler.register(MythicDecorationsItems.class, MOD_ID, false);
        MYTHICMETALS_DECOR.initialize();

        // Integrate with base MM
        Abilities.BLAST_PADDING.addItem(MythicDecorations.BANGLUM.getCrown(), Style.EMPTY.withColor(Formatting.GOLD));
        Abilities.FIRE_PROTECTION.addItem(MythicDecorations.PALLADIUM.getCrown(), Style.EMPTY.withColor(14644003));

        Registry.register(Registries.SCREEN_HANDLER, RegHelper.id("mythic_chest"), MYTHIC_CHEST_SCREEN_HANDLER_TYPE);
        Registry.register(Registries.ITEM, RegHelper.id("crown_chisel"), CROWN_CHISEL);
    }

}
