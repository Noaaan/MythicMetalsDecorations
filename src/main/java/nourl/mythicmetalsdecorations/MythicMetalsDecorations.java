package nourl.mythicmetalsdecorations;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetalsdecorations.blocks.MythicDecorations;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlock;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChests;
import nourl.mythicmetalsdecorations.config.MythicDecorationsConfig;
import nourl.mythicmetalsdecorations.item.MythicDecorationsItems;
import nourl.mythicmetalsdecorations.utils.RegHelper;

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
            }).build();

    public static final ScreenHandlerType<MythicChestScreenHandler> MYTHIC_CHEST_SCREEN_HANDLER_TYPE = new ExtendedScreenHandlerType<>(MythicChestScreenHandler::new);

    public static final Item CROWN_CHISEL = new Item(new OwoItemSettings().group(MYTHICMETALS_DECOR).tab(2));

    @Override
    public void onInitialize() {
        MythicDecorations.init();
        MythicChests.init();
        FieldRegistrationHandler.register(MythicDecorationsItems.class, MOD_ID, false);
        MYTHICMETALS_DECOR.initialize();

        // Integrate with base MM
        Abilities.AQUA_AFFINITY.addItem(MythicDecorations.AQUARIUM.getCrown(), Style.EMPTY.withColor(Formatting.AQUA));
        Abilities.BLAST_PADDING.addItem(MythicDecorations.BANGLUM.getCrown(), Style.EMPTY.withColor(Formatting.GOLD));
        Abilities.CARMOT_SHIELD.addItem(MythicDecorations.CARMOT.getCrown(), Style.EMPTY.withColor(15089267));
        Abilities.MENDING.addItem(MythicDecorations.PROMETHEUM.getCrown(), Style.EMPTY.withColor(3828310));
        Abilities.FIRE_PROTECTION.addItem(MythicDecorations.PALLADIUM.getCrown(), Style.EMPTY.withColor(14644003));

        Registry.register(Registries.SCREEN_HANDLER, RegHelper.id("mythic_chest"), MYTHIC_CHEST_SCREEN_HANDLER_TYPE);
        Registry.register(Registries.ITEM, RegHelper.id("crown_chisel"), CROWN_CHISEL);
        registerChestStorage();
    }

    @SuppressWarnings({"UnstableApiUsage", "DataFlowIssue", "CodeBlock2Expr"})
    private void registerChestStorage() {
        ItemStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> {
                    return InventoryStorage.of(
                            MythicChestBlock.getInventory(
                                    (MythicChestBlock) blockEntity.getCachedState().getBlock(),
                                    blockEntity.getCachedState(),
                                    blockEntity.getWorld(),
                                    blockEntity.getPos(),
                                    false),
                            direction);
                },
                MythicChests.MYTHIC_CHEST_BLOCK_ENTITY_TYPE);
    }
}
