package nourl.mythicmetalsdecorations.client;

import io.wispforest.owo.mixin.ui.SlotAccessor;
import io.wispforest.owo.util.pond.OwoSlotExtension;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import nourl.mythicmetalsdecorations.MythicChestScreenHandler;
import nourl.mythicmetalsdecorations.MythicMetalsDecorations;
import nourl.mythicmetalsdecorations.utils.ChestScreenSize;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicChestScreen extends HandledScreen<MythicChestScreenHandler> {

    public static final Identifier TEXTURE = RegHelper.id("textures/gui/mythic_chest_screen.png");

    private ChestScreenSize size;

    private boolean scrolling = false;
    private int scrollOffset = 0;
    private int maxScroll = 0;

    public MythicChestScreen(MythicChestScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        this.size = ChestScreenSize.decompose(handler.inventorySize(), this.width - 8, this.height - 8);

        this.backgroundWidth = this.size.paddedWidth();
        this.backgroundHeight = this.size.paddedHeight() + ChestScreenSize.PLAYER_INVENTORY_HEIGHT;

        this.playerInventoryTitleY = this.backgroundHeight - 94;

        this.maxScroll = this.size.hasExtraRow()
                ? (this.handler.inventorySize() - this.size.rows() * this.size.columns()) / this.size.columns() + 1
                : (this.handler.inventorySize() - this.size.rows() * this.size.columns()) / this.size.columns();
        this.scrollOffset = MathHelper.clamp(this.scrollOffset, 0, this.maxScroll);

        this.configureSlots(true);

        super.init();
    }

    private void configureSlots(boolean movePlayerInventory) {
        int pointer = 0;

        for (int i = 0; i < this.scrollOffset * this.size.columns(); i++) {
            this.toggleSlot(this.handler.getSlot(i), false);

            pointer++;
        }

        // Arrange visible chest slots
        arrange:
        for (int row = 0; row < this.size.rows(); row++) {
            for (int column = 0; column < this.size.columns(); column++) {
                var slot = this.handler.getSlot(pointer);
                this.toggleSlot(slot, true);
                this.moveSlot(slot, 8 + column * 18, 18 + row * 18);

                pointer++;
                if (pointer >= this.handler.chestInventory().size()) break arrange;
            }
        }

        // Hide overflowing slots
        for (int i = pointer; i < this.handler.chestInventory().size(); i++) {
            this.toggleSlot(this.handler.getSlot(i), false);

            pointer++;
        }

        if (movePlayerInventory) {
            // Player Inventory
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 9; column++) {
                    var slot = this.handler.slots.get(pointer);
                    this.moveSlot(slot, this.size.playerInventoryX() + 8 + column * 18, this.size.paddedHeight() + 15 + row * 18);

                    pointer++;
                }
            }

            // Player hotbar
            for (int i = 0; i < 9; i++) {
                var slot = this.handler.getSlot(pointer);
                this.moveSlot(slot, this.size.playerInventoryX() + 8 + i * 18, this.size.paddedHeight() + 15 + 58);

                pointer++;
            }
        }
    }

    private void scroll(int offset) {
        this.scrollOffset = MathHelper.clamp(offset, 0, this.maxScroll);
        this.configureSlots(false);
    }

    private void moveSlot(Slot slot, int x, int y) {
        ((SlotAccessor) slot).owo$setX(x);
        ((SlotAccessor) slot).owo$setY(y);
    }

    // I am not scared of the ducks in the pond
    @SuppressWarnings("UnstableApiUsage")
    private void toggleSlot(Slot slot, boolean enabled) {
        ((OwoSlotExtension) slot).owo$setDisabledOverride(!enabled);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        super.render(drawContext, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(drawContext, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext drawContext, float delta, int mouseX, int mouseY) {
        this.renderBackground(drawContext);

        drawContext.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.size.width() + ChestScreenSize.HORIZONTAL_PADDING, this.size.paddedHeight(), 368, 416);
        drawContext.drawTexture(TEXTURE, this.x + this.size.width() + ChestScreenSize.HORIZONTAL_PADDING, this.y, 331, 0, ChestScreenSize.HORIZONTAL_PADDING, this.size.paddedHeight(), 368, 416);

        int playerInventoryX = this.size.playerInventoryX();
        drawContext.drawTexture(TEXTURE, this.x + playerInventoryX, this.y + this.size.paddedHeight(), 81, 287, 176, 97, 368, 416);

        if (this.size.columns() > 9) {
            drawContext.drawTexture(TEXTURE, this.x, this.y + this.size.paddedHeight(), 0, 384, playerInventoryX, 17, 368, 416);
            drawContext.drawTexture(TEXTURE, this.x + playerInventoryX, this.y + this.size.paddedHeight(), 81, 384, 3, 17, 368, 416);

            drawContext.drawTexture(TEXTURE, this.x + playerInventoryX + 176, this.y + this.size.paddedHeight(), 257 + 81 - playerInventoryX, 384, playerInventoryX, 17, 368, 416);
            drawContext.drawTexture(TEXTURE, this.x + playerInventoryX + 176 - 3, this.y + this.size.paddedHeight(), 254, 384, 3, 17, 368, 416);
        }

        if (this.scrollOffset == this.maxScroll && (this.size.hasExtraRow() || this.size.rows() * this.size.columns() > this.handler.inventorySize())) {
            var finalRowSlots = this.size.hasExtraRow() ? this.size.extraRowSlots() : (this.handler.inventorySize()) % this.size.columns();
            drawContext.drawTexture(TEXTURE,
                    this.x + ChestScreenSize.HORIZONTAL_PADDING + finalRowSlots * 18, this.y + this.size.paddedHeight() - 18,
                    this.size.width() - (finalRowSlots * 18), 18,
                    339, 287, 14, 14,
                    368, 416
            );
        }

        if (this.size.needsScrolling()) {
            drawContext.drawTexture(TEXTURE, this.x + this.size.paddedWidth() - 4, this.y, 338, 0, 22, this.size.paddedHeight(), 368, 416);
            if (this.size.columns() > 9) {
                drawContext.drawTexture(TEXTURE, this.x + this.size.paddedWidth() - 4, this.y + size.paddedHeight() - 1, 338, 286, 22, 18, 368, 416);
                drawContext.drawTexture(TEXTURE, this.x + this.size.paddedWidth() - 2, this.y + 18 + this.scrollOffset * (this.size.paddedHeight() - 34) / this.maxScroll, 338, 322, 12, 15, 368, 416);
            } else {
                drawContext.drawTexture(TEXTURE, this.x + this.size.paddedWidth() - 4, this.y + size.paddedHeight() - 18, 338, 304, 22, 18, 368, 416);
                drawContext.drawTexture(TEXTURE, this.x + this.size.paddedWidth() - 2, this.y + 18 + this.scrollOffset * (this.size.paddedHeight() - 51) / this.maxScroll, 338, 322, 12, 15, 368, 416);
            }
        }
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.scrolling) {
            this.scrollTo(mouseY);
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isInScrollbar(mouseX, mouseY)) {
            this.scrolling = true;
            this.scrollTo(mouseY);
            return true;
        } else {
            this.scrolling = false;
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }

    private void scrollTo(double mouseY) {
        double scrollbarTop = this.y + ChestScreenSize.TOP_PADDING + 1;
        double scrollbarBottom = this.size.rows() * 18 + (this.size.columns() > 9 ? 17 : 0);

        this.scroll((int) (((mouseY - scrollbarTop) / (scrollbarBottom - scrollbarTop)) * this.maxScroll));
    }

    private boolean isInScrollbar(double mouseX, double mouseY) {
        return mouseX >= this.x + this.size.paddedWidth() - 2
                && mouseX <= this.x + this.size.paddedWidth() + 10
                && mouseY >= this.y + ChestScreenSize.TOP_PADDING + 1
                && mouseY <= this.y + ChestScreenSize.TOP_PADDING + 1 + this.size.rows() * 18 + (this.size.columns() > 9 ? 17 : 0);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (MythicMetalsDecorations.CONFIG.onlyScrollOnScrollbar()) {
            if (isInScrollbar(mouseX, mouseY)) {
                this.scroll((int) (this.scrollOffset - amount));
                return true;
            }
            return false;
        }
        this.scroll((int) (this.scrollOffset - amount));
        return true;
    }

    public ChestScreenSize getSize() {
        return size;
    }

    public int getHandledScreenX() {
        return this.x;
    }

    public int getHandledScreenY() {
        return this.y;
    }

    public int getBackgroundWidth() {
        return this.backgroundWidth;
    }
}
