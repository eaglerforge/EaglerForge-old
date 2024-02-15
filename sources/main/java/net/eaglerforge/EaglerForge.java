package net.eaglerforge;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import org.teavm.jso.JSBody;

import net.eaglerforge.gui.ModLoader;

public class EaglerForge {
    public static final Logger log = LogManager.getLogger();
    @JSBody(params = { "message" }, script = "alert(message)")
    public static native void jsalert(String message);

    @JSBody(params = { "message" }, script = "console.log(message)")
    public static native void jsconsolelog(String message);

    @JSBody(params = { "message" }, script = "prompt(message)")
    public static native void jsprompt(String message);
    @JSBody(params = { "message", "default_text" }, script = "prompt(message, default_text)")
    public static native void jspromptdt(String message, String default_text);



    public static void init() {
        log.info("Starting EaglerForge!");
        log.info("Loading Mods...");
        ModLoader.loadLoader();
    }
}
