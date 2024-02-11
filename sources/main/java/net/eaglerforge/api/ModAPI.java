package net.eaglerforge.api;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.Minecraft;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import static net.lax1dude.eaglercraft.v1_8.EaglercraftVersion.projectForkVersion;

public class ModAPI {
    private static Minecraft mc;
    public static final Logger log = LogManager.getLogger();
    public static String version = projectForkVersion;
    @JSBody(params = { "version" }, script ="var ModAPI = {};\r\n" + //
    "ModAPI.version = version;\r\n" + //
    "ModAPI.log = log;\r\n" + //
    "ModAPI.mcinstance = mc;\r\n" + //
    "window.ModAPI = ModAPI;")
    public static native void initAPI(String version);
    public ModAPI(Minecraft mcIn) {
        this.mc = mcIn;
        initAPI(version);
    }
}
