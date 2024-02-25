package nourl.mythicmetalsdecorations.utils;

import net.minecraft.util.math.MathHelper;

public record ChestScreenSize(int columns, int rows, int extraRowSlots, boolean needsScrolling) {

    public static final int PLAYER_INVENTORY_WIDTH = 176;
    public static final int PLAYER_INVENTORY_HEIGHT = 97;

    public static final int HORIZONTAL_PADDING = 7;
    public static final int TOP_PADDING = 17;

    public static ChestScreenSize decompose(int slotCount, int maxWidth, int maxHeight) {
        int columns;
        if (slotCount <= 81) {
            columns = 9;
        } else if (slotCount <= 108) {
            columns = 12;
        } else if (slotCount <= 135) {
            columns = 15;
        } else {
            columns = 18;
        }

        int rows = slotCount / columns;
        if (rows * columns < slotCount) rows++;

        var tempSize = new ChestScreenSize(columns, rows, 0, false);
        boolean needsScrolling = false;

        if (tempSize.paddedWidth() > maxWidth) {
            columns = Math.max(9, columns - MathHelper.ceilDiv((tempSize.paddedWidth() - maxWidth), 18));
            needsScrolling = true;
        }
        if (tempSize.paddedHeight() + PLAYER_INVENTORY_HEIGHT > maxHeight || rows > 15) {
            rows = MathHelper.clamp(rows - MathHelper.ceilDiv(tempSize.paddedHeight() + PLAYER_INVENTORY_HEIGHT - maxHeight, 18), 6, 15);
            needsScrolling = true;
        }

        return new ChestScreenSize(columns, rows, rows * columns < slotCount ? slotCount % columns : 0, needsScrolling);
    }

    public int width() {
        return this.columns * 18;
    }

    public int height() {
        return this.extraRowSlots != 0 && !this.needsScrolling
                ? (this.rows + 1) * 18
                : this.rows * 18;
    }

    public int paddedHeight() {
        return this.height() + TOP_PADDING;
    }

    public int paddedWidth() {
        return this.width() + HORIZONTAL_PADDING * 2;
    }

    public int playerInventoryX() {
        return (this.paddedWidth() - PLAYER_INVENTORY_WIDTH) / 2;
    }

    public boolean hasExtraRow() {
        return this.extraRowSlots != 0;
    }
}
