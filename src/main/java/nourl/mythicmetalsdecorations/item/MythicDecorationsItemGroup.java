package nourl.mythicmetalsdecorations.item;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetalsdecorations.blocks.MythicDecorations;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicDecorationsItemGroup extends OwoItemGroup {

    public MythicDecorationsItemGroup(Identifier id) {
        super(id);
    }

    @Override
    protected void setup() {
        this.addTab(Icon.of(MythicDecorations.ADAMANTITE.getChain().asItem().getDefaultStack()), "chains", TagKey.of(Registry.ITEM_KEY, RegHelper.id("chains")));
        this.addTab(Icon.of(MythicDecorations.MIDAS_GOLD.getChest().asItem().getDefaultStack()), "chests", TagKey.of(Registry.ITEM_KEY, RegHelper.id("chests")));
        this.addTab(Icon.of(MythicDecorationsItems.HYDRARGYM_INGOT.getDefaultStack()), "misc", TagKey.of(Registry.ITEM_KEY, RegHelper.id("misc")));

        this.addButton(ItemGroupButton.github("https://github.com/Noaaan/MythicMetalsDecorations/issues"));
        this.addButton(ItemGroupButton.curseforge("https://www.curseforge.com/minecraft/mc-mods/mythicmetals-decorations"));
        this.addButton(ItemGroupButton.modrinth("https://modrinth.com/mod/mythicmetals_decorations"));
        this.addButton(ItemGroupButton.discord("https://discord.gg/69cKvQWScC"));
    }

    @Override
    public ItemStack createIcon() {
        return MythicDecorations.CELESTIUM.getChain().asItem().getDefaultStack();
    }
}
