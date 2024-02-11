package net.eaglerforge.api;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import java.util.ArrayList;

import static net.lax1dude.eaglercraft.v1_8.EaglercraftVersion.projectForkVersion;

public class ModAPI {
    private static Minecraft mc;
    public ArrayList<String> requiredList;
    public static final Logger log = LogManager.getLogger();
    public static String version = projectForkVersion;
    public static boolean clientPacketSendEventsEnabled = true;
    @JSBody(params = { "version" }, script = "var ModAPI = {};\r\n" + //
            "ModAPI.events = {};\r\n" + //
            "ModAPI.events.types = [\"event\"];\r\n" + //
            "ModAPI.events.listeners = {\"event\": []};\r\n" + //
            "ModAPI.globals = {};\r\n" + //
            "ModAPI.version = version;\r\n" + //
            "ModAPI.addEventListener = function newEventListener(name, callback) {\r\n" + //
            "  if (!callback) {\r\n" + //
            "    throw new Error(\"Invalid callback!\");\r\n" + //
            "  }\r\n" + //
            "  if (ModAPI.events.types.includes(name)) {\r\n" + //
            "    if (!Array.isArray(ModAPI.events.listeners[name])) {\r\n" + //
            "      ModAPI.events.listeners[name] = [];\r\n" + //
            "    }\r\n" + //
            "    ModAPI.events.listeners[name].push(callback);\r\n" + //
            "    console.log(\"Added new event listener.\");\r\n" + //
            "  } else {\r\n" + //
            "    throw new Error(\"This event does not exist!\");\r\n" + //
            "  }\r\n" + //
            "};\r\n" + //
            "ModAPI.removeEventListener = function removeEventListener(name, func, slow) {\r\n" + //
            "  if (!func) {\r\n" + //
            "    throw new Error(\"Invalid callback!\");\r\n" + //
            "  }\r\n" + //
            "  if (!Array.isArray(ModAPI.events.listeners[name])) {\r\n" + //
            "    ModAPI.events.listeners[name] = [];\r\n" + //
            "  }\r\n" + //
            "  var targetArr = ModAPI.events.listeners[name];\r\n" + //
            "  if (!slow) {\r\n" + //
            "    if (targetArr.indexOf(func) !== -1) {\r\n" + //
            "      targetArr.splice(targetArr.indexOf(func), 1);\r\n" + //
            "      console.log(\"Removed event listener.\");\r\n" + //
            "    }\r\n" + //
            "  } else {\r\n" + //
            "    var functionString = func.toString();\r\n" + //
            "    targetArr.forEach((f, i) => {\r\n" + //
            "      if (f.toString() === functionString) {\r\n" + //
            "        targetArr.splice(i, 1);\r\n" + //
            "        console.log(\"Removed event listener.\");\r\n" + //
            "      }\r\n" + //
            "    });\r\n" + //
            "  }\r\n" + //
            "};\r\n" + //
            "ModAPI.events.newEvent = function newEvent(name) {\r\n" + //
            "  ModAPI.events.types.push(name);\r\n" + //
            "};\r\n" + //
            "ModAPI.events.callEvent = function callEvent(name, data) {\r\n" + //
            "  if (\r\n" + //
            "    !ModAPI.events.types.includes(name) ||\r\n" + //
            "    !Array.isArray(ModAPI.events.listeners[name])\r\n" + //
            "  ) {\r\n" + //
            "    if (!Array.isArray(ModAPI.events.listeners[name])) {\r\n" + //
            "      if (ModAPI.events.types.includes(name)) {\r\n" + //
            "        ModAPI.events.listeners.event.forEach((func) => {\r\n" + //
            "          func({event: name, data: data});\r\n" + //
            "        });\r\n" + //
            "        return;\r\n" + //
            "      }\r\n" + //
            "      return;\r\n" + //
            "    }\r\n" + //
            "    console.error(\"The ModAPI has been called with an invalid event name: \"+name);\r\n" + //
            "    console.error(\"Please report this bug to the repo.\");\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  ModAPI.events.listeners[name].forEach((func) => {\r\n" + //
            "    func(data);\r\n" + //
            "  });\r\n" + //
            "  ModAPI.events.listeners.event.forEach((func) => {\r\n" + //
            "    func({event: name, data: data});\r\n" + //
            "  });\r\n" + //
            "\r\n" + //
            "  ModAPI.globals._initUpdate();\r\n" + //
            "};\r\n" + //
            "ModAPI.updateComponent = function updateComponent(component) {\r\n" + //
            "  if (\r\n" + //
            "    typeof component !== \"string\" ||\r\n" + //
            "    ModAPI[component] === null ||\r\n" + //
            "    ModAPI[component] === undefined\r\n" + //
            "  ) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  if (!ModAPI.globals || !ModAPI.globals.onGlobalsUpdate) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  if (!ModAPI.globals.toUpdate) {\r\n" + //
            "    ModAPI.globals.toUpdate = [];\r\n" + //
            "  }\r\n" + //
            "  if (ModAPI.globals.toUpdate.indexOf(component) === -1) {\r\n" + //
            "    ModAPI.globals.toUpdate.push(component);\r\n" + //
            "  }\r\n" + //
            "};\r\n" + //
            "ModAPI.require = function require(component) {\r\n" + //
            "  if (typeof component !== \"string\") {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  if (!ModAPI.globals || !ModAPI.globals.onRequire) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  ModAPI.globals.onRequire(component);\r\n" + //
            "};\r\n" + //
            "ModAPI.globals._initUpdate = function _initUpdate() {\r\n" + //
            "  if (!ModAPI.globals.toUpdate) {\r\n" + //
            "    ModAPI.globals.toUpdate = [];\r\n" + //
            "  }\r\n" + //
            "  ModAPI.globals.toUpdate.forEach((id) => {\r\n" + //
            "    ModAPI.globals.onGlobalsUpdate(id);\r\n" + //
            "  });\r\n" + //
            "  ModAPI.globals.toUpdate = [];\r\n" + //
            "};\r\n" + //
            "window.ModAPI = ModAPI;\r\n" + //
            "")
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
        getModAPI().setCallbackVoidWithDataArg("displayToChat", (BaseData params) -> {
            mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(params.getString("msg")));
        });
        getModAPI().setCallbackVoid("clickMouse", () -> {
            mc.clickMouse();
        });
        getModAPI().setCallbackVoid("rightClickMouse", () -> {
            mc.rightClickMouse();
        });
        getModAPI().set("clientBrand", ClientBrandRetriever.getClientModName());

        setGlobal("mcinstance", mc);
        setGlobal("mcinstance", log);
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
