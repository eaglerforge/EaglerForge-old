package net.eaglerforge.api;

import net.eaglerforge.gui.EmptyGui;
import net.eaglerforge.gui.ModGUI;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;
import me.otterdev.UwUAPI;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.block.material.Material;

import java.util.ArrayList;

import static net.lax1dude.eaglercraft.v1_8.EaglercraftVersion.projectForkVersion;
import static net.minecraft.client.Minecraft.getDebugFPS;
import net.minecraft.client.gui.Gui;

public class ModAPI {

    private static Minecraft mc;
    private static ScaledResolution sr;

    private static Gui gui;
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
        switch (global) {
            case "player":
                mc.thePlayer.loadModData(data);
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
        newEvent("postmotionupdate");
        newEvent("motionupdate");
        newEvent("premotionupdate");
        newEvent("sendchatmessage");
        newEvent("update");

        /*newEvent("packetjoingame");
        newEvent("packetspawnobject");
        newEvent("packetspawnxporb");
        newEvent("packetspawnglobalentity");
        newEvent("packetspawnpainting");
        newEvent("packetentityvelocity");
        newEvent("packetentitymetadata");
        newEvent("packetspawnplayer");
        newEvent("packetentityteleport");
        newEvent("packethelditemchange");
        newEvent("packetentity");
        newEvent("packetentityheadlook");
        newEvent("packetdestroyentities");
        newEvent("packetplayerposlook");
        newEvent("packetmultiblockchange");
        newEvent("packetchunkdata");
        newEvent("packetblockchange");
        newEvent("packetdisconnect");
        newEvent("packetcollectitem");
        newEvent("packetchat");
        newEvent("packetanimation");
        newEvent("packetusebed");
        newEvent("packetspawnmob");
        newEvent("packettimeupdate");
        newEvent("packetspawnposition");
        newEvent("packetentityattatch");
        newEvent("packetentitystatus");
        newEvent("packetupdatehealth");
        newEvent("packetsetxp");
        newEvent("packetrespawn");
        newEvent("packetexplosion");
        newEvent("packetopenwindow");
        newEvent("packetsetslot");
        newEvent("packetconfirmtransaction");
        newEvent("packetwindowitems");
        newEvent("packetsigneditoropen");
        newEvent("packetupdatesign");
        newEvent("packetupdatetileentity");
        newEvent("packetwindowproperty");
        newEvent("packetentityequipment");
        newEvent("packetclosewindow");
        newEvent("packetblockaction");
        newEvent("packetblockbreakanim");
        newEvent("packetmapchunkbulk");
        newEvent("packetchangegamestate");
        newEvent("packetmaps");
        newEvent("packeteffect");
        newEvent("packetstatistics");
        newEvent("packetentityeffect");
        newEvent("packetcombatevent");
        newEvent("packetserverdifficulty");
        newEvent("packetcamera");
        newEvent("packetworldborder");
        newEvent("packettitle");
        newEvent("packetsetcompressionlevel");
        newEvent("packetplayerlistheaderfooter");
        newEvent("packetremoveentityeffect");
        newEvent("packetplayerlistitem");
        newEvent("packetkeepalive");
        newEvent("packetplayerabilities");
        newEvent("packettabcomplete");
        newEvent("packetsoundeffect");
        newEvent("packetresourcepack");
        newEvent("packetupdateentitynbt");
        newEvent("packetcustompayload");
        newEvent("packetscoreboardobjective");
        newEvent("packetupdatescore");
        newEvent("packetdisplayscoreboard");
        newEvent("packetteams");
        newEvent("packetparticles");
        newEvent("packetentityproperties");

        newEvent("sendpacketanimation");
        newEvent("sendpacketentityaction");
        newEvent("sendpacketinput");
        newEvent("sendpacketclosewindow");
        newEvent("sendpacketclickwindow");
        newEvent("sendpacketconfirmtransaction");
        newEvent("sendpacketkeepalive");
        newEvent("sendpacketchatmessage");
        newEvent("sendpacketuseentity");
        newEvent("sendpacketplayer");
        newEvent("sendpacketplayerposition");
        newEvent("sendpacketplayerlook");
        newEvent("sendpacketplayerposlook");
        newEvent("sendpacketplayerdigging");
        newEvent("sendpacketplayerblockplacement");
        newEvent("sendpackethelditemchange");
        newEvent("sendpacketcreativeinventoryaction");
        newEvent("sendpacketenchantitem");
        newEvent("sendpacketupdatesign");
        newEvent("sendpacketplayerabilities");
        newEvent("sendpackettabcomplete");
        newEvent("sendpacketclientsettings");
        newEvent("sendpacketclientstatus");
        newEvent("sendpacketcustompayload");
        newEvent("sendpacketspectate");
        newEvent("sendpacketresourcepackstatus");*/
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
        getModAPI().setCallbackInt("getFPS", () -> {
            return getDebugFPS();
        });
        getModAPI().set("clientBrand", ClientBrandRetriever.getClientModName());

        setGlobal("enchantments", Enchantment.makeModDataStatic());
        setGlobal("blocks", Blocks.makeModData());
        setGlobal("items", Items.makeModData());
        setGlobal("materials", Material.makeModDataStatic());
        setGlobal("mcinstance", mc);
        setGlobal("platform", PlatformAPI.makeModData());
        setGlobal("logger", LoggerAPI.makeModData());
        setGlobal("emptygui", EmptyGui.makeModData());
        setGlobal("ScaledResolution", ScaledResolution.makeModData());
        setGlobal("GlStateManager", GlStateManager.makeModData());
        getModAPI().setCallbackString("currentScreen", () -> {
            return mc.currentScreen.toString();
        });
        getModAPI().setCallbackInt("getdisplayHeight", () -> {
            return mc.displayHeight;
        });
        getModAPI().setCallbackInt("getdisplayWidth", () -> {
            return mc.displayWidth;
        });
        getModAPI().setCallbackInt("getdisplayWidth", () -> {
            return mc.displayWidth;
        });
        getModAPI().setCallbackInt("getFONT_HEIGHT", () -> {
            return mc.fontRendererObj.FONT_HEIGHT;
        });
        getModAPI().setCallbackIntWithDataArg("getStringWidth", (BaseData params) -> {
            return mc.fontRendererObj.getStringWidth(params.getString("string"));
        });
        getModAPI().setCallbackVoidWithDataArg("drawStringWithShadow", (BaseData params) -> {
            mc.fontRendererObj.drawStringWithShadow(params.getString("msg"), params.getFloat("x"), params.getFloat("y"), params.getInt("color"));
        });
        getModAPI().setCallbackVoidWithDataArg("drawString", (BaseData params) -> {
            mc.fontRendererObj.drawString(params.getString("msg"), params.getFloat("x"), params.getFloat("y"), params.getInt("color"), false);
        });
        getModAPI().setCallbackVoidWithDataArg("drawRect", (BaseData params) -> {
            gui.drawRect(params.getInt("left"), params.getInt("top"), params.getInt("right"), params.getInt("bottom"), params.getInt("color"));
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
        if (requiredList.contains("player") && mc.thePlayer != null) {
            ModAPI.setGlobal("player", mc.thePlayer.makeModData());
        }
/*        if (requiredList.contains("network") && mc.thePlayer != null && mc.thePlayer.sendQueue != null) {
            ModAPI.setGlobal("network", mc.thePlayer.sendQueue.makeModData());
        }*/
        if (requiredList.contains("settings") && mc.gameSettings != null) {
            ModAPI.setGlobal("settings", mc.gameSettings.makeModData());
        }
        ModAPI.callEvent("update", new ModData());
    }
}
