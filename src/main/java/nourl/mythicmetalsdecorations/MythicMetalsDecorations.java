package nourl.mythicmetalsdecorations;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetalsdecorations.blocks.Decorations;

public class MythicMetalsDecorations implements ModInitializer {

    public static final String MOD_ID = "mythicmetals_decorations";

    public static final ItemGroup MYTHICMETALS_DECOR = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "decorations")).icon(() -> new ItemStack(Decorations.ADAMANTITE.getChain())).build();

    @Override
    public void onInitialize() {
        Decorations.init();
    }
}
