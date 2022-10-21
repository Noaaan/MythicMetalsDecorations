package nourl.mythicmetalsdecorations;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetalsdecorations.blocks.MythicDecorations;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChests;
import nourl.mythicmetalsdecorations.item.MythicDecorationsItemGroup;
import nourl.mythicmetalsdecorations.item.MythicDecorationsItems;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicMetalsDecorations implements ModInitializer {

    public static final String MOD_ID = "mythicmetals_decorations";
    public static final OwoItemGroup MYTHICMETALS_DECOR = new MythicDecorationsItemGroup(RegHelper.id("main"));

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

        Registry.register(Registry.SCREEN_HANDLER, RegHelper.id("mythic_chest"), MYTHIC_CHEST_SCREEN_HANDLER_TYPE);
        Registry.register(Registry.ITEM, RegHelper.id("crown_chisel"), CROWN_CHISEL);
    }
}
