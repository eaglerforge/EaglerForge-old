package net.eaglerforge.api;

import java.util.Map;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSFunctor;

class GlobalsListener {
    @JSFunctor
    public interface GlobalsHandler extends JSObject {
        void onGlobalsUpdate(String global);
    }

    @JSFunctor
    public interface UpdateHandler extends JSObject {
        void onUpdate();
    }

    @JSBody(params = { "handler" }, script = "window.PluginAPI.globals.onGlobalsUpdate = handler;")
    static native void provideCallback(GlobalsHandler handler);

    @JSBody(params = { "handler" }, script = "window.PluginAPI.globals.onRequire = handler;")
    static native void provideRequireCallback(GlobalsHandler handler);

    @JSBody(params = { "handler" }, script = "window.PluginAPI.update = handler;")
    static native void provideUpdateCallback(UpdateHandler handler);
}