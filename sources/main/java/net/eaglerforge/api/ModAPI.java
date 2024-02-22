package net.eaglerforge.api;

import net.eaglerforge.gui.ModGUI;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import me.otterdev.UwUAPI;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import net.eaglerforge.EaglerForge;

import java.util.ArrayList;

import static net.lax1dude.eaglercraft.v1_8.EaglercraftVersion.projectForkVersion;
import static net.minecraft.client.Minecraft.getDebugFPS;
import net.minecraft.client.gui.Gui;

public class ModAPI {

    private static Minecraft mc;
    public ArrayList<String> requiredList;
    public static final Logger log = LogManager.getLogger();
    public static String version = projectForkVersion;
    public static boolean clientPacketSendEventsEnabled = true;
    @JSBody(params = { "version" }, script = "initAPI(version)")
    public static native void initAPI(String version);
    @JSBody(params = { "name" }, script = "ModAPI.events.newEvent(name);")
    private static native void newEvent(String name);

    @JSBody(params = { "name", "data" }, script = "ModAPI.events.callEvent(name, data); return data;")
    public static native BaseData callEvent(String name, BaseData data);

    @JSBody(params = { "name", "data" }, script = "ModAPI[name]=data;")
    public static native void setGlobal(String name, BaseData data);

    @JSBody(params = { "name" }, script = "return ModAPI[name] || {};")
    public static native BaseData getGlobal(String name);

    @JSBody(params = {}, script = "return ModAPI;")
    public static native BaseData getModAPI();

    @JSBody(params = { "data" }, script = "console.log(data);")
    public static native void logJSObj(JSObject data);

    public void onGlobalUpdated(String global) {
        // logger.info("Global update request: "+global);
        BaseData data = getGlobal(global);
        // logJSObj(data);
        if (data == null) {
            return;
        }
    }

    public void onRequire(String global) {
        if (!requiredList.contains(global)) {
            log.info("Required global: " + global);
            requiredList.add(global);
        }
    }

    public ModAPI(Minecraft mcIn) {
        this.mc = mcIn;
        requiredList = new ArrayList<String>();
        initAPI(version);

        newEvent("load");
        newEvent("gui");
        newEvent("drawhud");
        newEvent("key");

        newEvent("update");
        globalsFunctor(this);
        globalsRequireFunctor(this);
        globalsUpdateFunctor(this);
        getModAPI().setCallbackVoidWithDataArg("displayToChat", (BaseData params) -> {
            mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(params.getString("msg")));
        });
        getModAPI().setCallbackStringWithDataArg("uwuify", (BaseData params) -> {
            return UwUAPI.uwuify(params.getString("string"));
        });
        getModAPI().setCallbackVoid("clickMouse", () -> {
            mc.clickMouse();
        });
        getModAPI().setCallbackVoid("rightClickMouse", () -> {
            mc.rightClickMouse();
        });
        getModAPI().setCallbackVoid("getFPS", () -> {
            getDebugFPS();
        });
        getModAPI().set("clientBrand", ClientBrandRetriever.getClientModName());

        setGlobal("mcinstance", mc);
        setGlobal("platform", PlatformAPI.makeModData());
        setGlobal("logger", LoggerAPI.makeModData());
        getModAPI().setCallbackVoidWithDataArg("drawStringWithShadow", (BaseData params) -> {
            mc.fontRendererObj.drawStringWithShadow(params.getString("msg"), params.getFloat("x"), params.getFloat("y"), params.getInt("color"));
            EaglerForge.jsconsolelog("your params : " + params.getString("msg")+" "+params.getFloat("x")+" "+params.getFloat("y")+" "+params.getInt("color"));
        });
        getModAPI().setCallbackInt("getdisplayHeight", () -> {
            return mc.displayHeight;
        });
        getModAPI().setCallbackInt("getdisplayWidth", () -> {
            return mc.displayWidth;
        });
        getModAPI().setCallbackVoidWithDataArg("drawStringWithShadow", (BaseData params) -> {
            mc.fontRendererObj.drawStringWithShadow(params.getString("msg"), params.getFloat("x"), params.getFloat("y"), params.getInt("color"));
        });
        getModAPI().setCallbackVoidWithDataArg("drawString", (BaseData params) -> {
            mc.fontRendererObj.drawString(params.getString("msg"), params.getFloat("x"), params.getFloat("y"), params.getInt("color"), false);
        });
        getModAPI().setCallbackVoidWithDataArg("drawRect", (BaseData params) -> {
            Gui.drawRect(params.getInt("left"), params.getInt("top"), params.getInt("right"), params.getInt("bottom"), params.getInt("color"));
        });
        ModGUI.loadFont();
    }
    static void globalsFunctor(ModAPI modAPI) {
        GlobalsListener.provideCallback((String name) -> {
            modAPI.onGlobalUpdated(name);
        });
    }

    static void globalsRequireFunctor(ModAPI modAPI) {
        GlobalsListener.provideRequireCallback((String name) -> {
            modAPI.onRequire(name);
        });
    }

    static void globalsUpdateFunctor(ModAPI modAPI) {
        GlobalsListener.provideUpdateCallback(() -> {
            modAPI.onUpdate();
        });
    }


    public void onUpdate() {
        ModAPI.callEvent("update", new ModData());
    }
}
