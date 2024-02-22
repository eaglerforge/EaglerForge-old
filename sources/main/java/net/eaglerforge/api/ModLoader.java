package net.eaglerforge.api;

import org.teavm.jso.JSBody;

public class ModLoader {
    public static String[] Mods = {};

    @JSBody(params = { "Mods" }, script = "window.ModLoader(Mods);")
    public static native void loadMods(String[] Mods);

    @JSBody(params = {}, script = "loadLoader();")
    public static native void loadLoader();

    @JSBody(params = { "Mods" }, script = "localStorage.setItem('ml::Mods', JSON.stringify(Mods))")
    private static native void saveMods(String[] Mods);

    @JSBody(params = {}, script = "try { return JSON.parse(localStorage.getItem('ml::Mods')||'[]') } catch(err) {return []}")
    private static native String[] retrieveMods();

    @JSBody(params = {}, script = "return returntotalloadedmods()")
    public static native int returntotalloadedmods();


    public static void saveModsToLocalStorage() {
        saveMods(Mods);
    };

    public static void loadModsFromLocalStorage() {
        Mods = retrieveMods();
    };
}