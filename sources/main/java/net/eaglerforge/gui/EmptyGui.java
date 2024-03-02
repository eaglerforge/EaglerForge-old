package net.eaglerforge.gui;


import net.eaglerforge.api.BaseData;
import net.eaglerforge.api.ModData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;


public class EmptyGui extends GuiScreen {
    public static boolean initialized;
    public static boolean drawdefaultbg;

    private static Minecraft mc;

    public void initGui() {
        if(!this.initialized){
            this.initialized = true;
        }
    }
    public void onGuiClosed() {
        this.initialized = false;
    }

    public static void setdrawdefaultbg(boolean value){
        drawdefaultbg = value;
    }

    public void drawScreen(int i, int j, float f) {
        if(drawdefaultbg) {
            this.drawDefaultBackground();
        }

        super.drawScreen(i, j, f);
    }

    public static ModData makeModData() {
        ModData emptygui = new ModData();
        emptygui.set("drawdefaultbg", drawdefaultbg);
        emptygui.setCallbackVoid("DisplayGUI", () -> {
            mc.displayGuiScreen(new EmptyGui());
        });
        emptygui.setCallbackVoid("CloseGUI", () -> {
            mc.displayGuiScreen((GuiScreen) null);
            mc.setIngameFocus();
        });
        emptygui.setCallbackBoolean("isInitialized", () -> {
            return initialized;
        });
        return emptygui;
    }

    public void loadModData(BaseData data) {
        drawdefaultbg = data.getBoolean("drawdefaultbg");
    }


}
