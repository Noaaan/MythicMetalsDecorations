package nourl.mythicmetalsdecorations.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;

@Config(name = "mythicmetalsdecorations", wrapperName = "MythicDecorationsConfig")
@Modmenu(modId = MythicMetalsDecorations.MOD_ID)
public class MythicDecorationsConfigModel {

    public boolean onlyScrollOnScrollbar = false;
}
