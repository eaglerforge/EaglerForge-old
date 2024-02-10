package net.eaglerforge;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class EaglerForge {
    public static final Logger log = LogManager.getLogger();
    public static void init() {
        log.info("Starting EaglerForge!");
        log.info("Loading Mods...");
        // TODO: Make a javascript moding api using teavm and make the mods load here
    }
}
