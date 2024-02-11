package net.eaglerforge;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;

import java.util.List;

public class GuiMods extends GuiScreen {
    private final GuiScreen parentScreen;
    private IChatComponent message;
    private List<String> multilineMessage;
    private int field_175353_i;

    public GuiMods(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }
    public void initGui() {
        this.buttonList.clear();
        this.multilineMessage = this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(),
                this.width - 50);
        this.field_175353_i = this.multilineMessage.size() * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(69, this.width / 2 - 100,
                this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT,
                I18n.format("gui.toMenu", new Object[0])));
    }
    protected void actionPerformed(GuiButton parGuiButton) {
        if (parGuiButton.id == 0) {
            this.mc.displayGuiScreen(this.parentScreen);
        }

    }
    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "testing", this.width / 2,
                this.height / 2 - this.field_175353_i / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
        int k = this.height / 2 - this.field_175353_i / 2;
        if (this.multilineMessage != null) {
            for (String s : this.multilineMessage) {
                this.drawCenteredString(this.fontRendererObj, s, this.width / 2, k, 16777215);
                k += this.fontRendererObj.FONT_HEIGHT;
            }
        }

        super.drawScreen(i, j, f);
    }
}
