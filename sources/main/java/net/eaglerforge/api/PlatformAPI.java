package net.eaglerforge.api;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import org.teavm.jso.JSBody;

// this is me.otterdev.PlatformAPI but modified to have more features and be more optimized
public class PlatformAPI {

    @JSBody(params = { }, script = "return navigator.platform;")
    public static native void getplatformOS();

    public static ModData makeModData() {
        ModData platformGlobal = new ModData();
        platformGlobal.setCallbackBoolean("isOfflineDownload", () -> {
            return EagRuntime.isOfflineDownloadURL();
        });
        platformGlobal.setCallbackFloat("freeMemory", () -> {
            return EagRuntime.freeMemory();
        });
        platformGlobal.setCallbackFloat("maxMemory", () -> {
            return EagRuntime.maxMemory();
        });
        platformGlobal.setCallbackFloat("totalMemory", () -> {
            return EagRuntime.totalMemory();
        });
        platformGlobal.setCallbackVoidWithDataArg("openLink", (BaseData params) -> {
            EagRuntime.openLink(params.getString("url"));
        });
        platformGlobal.setCallbackString("getClipboard", () -> {
            return EagRuntime.getClipboard();
        });
        platformGlobal.setCallbackBoolean("recSupported", () -> {
            return EagRuntime.recSupported();
        });
        platformGlobal.setCallbackVoid("toggleRec", () -> {
            EagRuntime.toggleRec();
        });
        platformGlobal.setCallbackVoid("getPlatformOS", () -> {
            //return EagRuntime.getPlatformOS();
            getplatformOS();
        });
        platformGlobal.setCallbackVoid("getUserAgentString", () -> {
            PlatformRuntime.getUserAgentString();
        });
        platformGlobal.setCallbackVoid("getGLRenderer", () -> {
            PlatformRuntime.getGLRenderer();
        });
        platformGlobal.setCallbackVoid("getGLVersion", () -> {
            PlatformRuntime.getGLVersion();
        });
        return platformGlobal;
    }
}
