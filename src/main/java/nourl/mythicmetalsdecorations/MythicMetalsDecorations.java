package nourl.mythicmetalsdecorations;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetalsdecorations.blocks.Decorations;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChests;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicMetalsDecorations implements ModInitializer {

    public static final String MOD_ID = "mythicmetals_decorations";
    public static final ItemGroup MYTHICMETALS_DECOR = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "decorations")).icon(() -> new ItemStack(Decorations.ADAMANTITE.getChain())).build();

    public static final ScreenHandlerType<MythicChestScreenHandler> MYTHIC_CHEST_SCREEN_HANDLER_TYPE = new ExtendedScreenHandlerType<>(MythicChestScreenHandler::new);

    @Override
    public void onInitialize() {
        Decorations.init();
        MythicChests.init();

        Registry.register(Registry.SCREEN_HANDLER, RegHelper.id("mythic_chest"), MYTHIC_CHEST_SCREEN_HANDLER_TYPE);
    }
}
