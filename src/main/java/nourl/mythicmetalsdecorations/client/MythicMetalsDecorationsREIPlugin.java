package nourl.mythicmetalsdecorations.client;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;

import java.util.ArrayList;

public class MythicMetalsDecorationsREIPlugin implements REIClientPlugin {
    @Override
    public void registerExclusionZones(ExclusionZones zones) {
        zones.register(MythicChestScreen.class, screen -> {
            final var rectangles = new ArrayList<Rectangle>();

            // The scrollbar is kinda taped onto the side of the screen
            // So we politely push REI a bit to the side
            if (screen.getSize().needsScrolling()) {
                int x = screen.getHandledScreenX() + screen.getBackgroundWidth();
                int y = screen.getHandledScreenY();
                rectangles.add(new Rectangle(x, y, 16, screen.getSize().paddedHeight() + 10));
            }

            return rectangles;
        });
    }
}
