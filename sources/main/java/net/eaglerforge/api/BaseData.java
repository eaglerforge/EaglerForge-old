package net.eaglerforge.api;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSFunctor;

public abstract class BaseData implements JSObject {
    @JSFunctor
    public interface VoidCallback extends JSObject {
        void onCallback();
    }

    @JSFunctor
    public interface BooleanCallback extends JSObject {
        boolean onCallback();
    }

    @JSFunctor
    public interface ObjectCallback extends JSObject {
        JSObject onCallback();
    }

    @JSFunctor
    public interface FloatCallback extends JSObject {
        float onCallback();
    }

    @JSFunctor
    public interface DoubleCallback extends JSObject {
        double onCallback();
    }

    @JSFunctor
    public interface StringCallback extends JSObject {
        String onCallback();
    }

    @JSFunctor
    public interface IntCallback extends JSObject {
        int onCallback();
    }

    @JSFunctor
    public interface BooleanCallbackArr extends JSObject {
        boolean[] onCallback();
    }

    @JSFunctor
    public interface ObjectCallbackArr extends JSObject {
        JSObject[] onCallback();
    }

    @JSFunctor
    public interface FloatCallbackArr extends JSObject {
        float[] onCallback();
    }

    @JSFunctor
    public interface DoubleCallbackArr extends JSObject {
        double[] onCallback();
    }

    @JSFunctor
    public interface StringCallbackArr extends JSObject {
        String[] onCallback();
    }

    @JSFunctor
    public interface IntCallbackArr extends JSObject {
        int[] onCallback();
    }

    @JSFunctor
    public interface DataVoidCallback extends JSObject {
        void onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataBooleanCallback extends JSObject {
        boolean onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataObjectCallback extends JSObject {
        JSObject onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataFloatCallback extends JSObject {
        float onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataDoubleCallback extends JSObject {
        double onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataStringCallback extends JSObject {
        String onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataIntCallback extends JSObject {
        int onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataBooleanCallbackArr extends JSObject {
        boolean[] onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataObjectCallbackArr extends JSObject {
        JSObject[] onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataFloatCallbackArr extends JSObject {
        float[] onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataDoubleCallbackArr extends JSObject {
        double[] onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataStringCallbackArr extends JSObject {
        String[] onCallback(BaseData data);
    }

    @JSFunctor
    public interface DataIntCallbackArr extends JSObject {
        int[] onCallback(BaseData data);
    }

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, String value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, int value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, boolean value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, float value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, byte value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, char value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, double value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, JSObject value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, BaseData value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, String[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, int[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, boolean[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, float[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, byte[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, char[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, double[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, JSObject[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, BaseData[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, short[] value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void set(String key, short value);

    @JSBody(params = { "key" }, script = "this[key]=null;")
    public native void setNull(String key);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackVoid(String key, VoidCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackBoolean(String key, BooleanCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackInt(String key, IntCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackString(String key, StringCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackDouble(String key, DoubleCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackFloat(String key, FloatCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackObject(String key, ObjectCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackBooleanWithDataArg(String key, DataBooleanCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackDoubleWithDataArg(String key, DataDoubleCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackFloatWithDataArg(String key, DataFloatCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackIntWithDataArg(String key, DataIntCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackStringWithDataArg(String key, DataStringCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackObjectWithDataArg(String key, DataObjectCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackVoidWithDataArg(String key, DataVoidCallback value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackBooleanArrWithDataArg(String key, DataBooleanCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackDoubleArrWithDataArg(String key, DataDoubleCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackFloatArrWithDataArg(String key, DataFloatCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackIntArrWithDataArg(String key, DataIntCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackStringArrWithDataArg(String key, DataStringCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackObjectArrWithDataArg(String key, DataObjectCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackBooleanArr(String key, BooleanCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackIntArr(String key, IntCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackStringArr(String key, StringCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackDoubleArr(String key, DoubleCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackFloatArr(String key, FloatCallbackArr value);

    @JSBody(params = { "key", "value" }, script = "this[key]=value;")
    public native void setCallbackObjectArr(String key, ObjectCallbackArr value);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native String getString(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native int getInt(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native boolean getBoolean(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native float getFloat(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native byte getByte(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native char getChar(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native double getDouble(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native JSObject getObject(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native BaseData getBaseData(String key);


    @JSBody(params = { "key" }, script = "return this[key];")
    public native String[] getStringArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native int[] getIntArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native short getShort(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native short[] getShortArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native boolean[] getBooleanArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native float[] getFloatArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native byte[] getByteArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native char[] getCharArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native double[] getDoubleArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native JSObject[] getObjectArr(String key);

    @JSBody(params = { "key" }, script = "return this[key];")
    public native BaseData[] getBaseDataArr(String key);

    @JSBody(params = { "key" }, script = "return key in this;")
    public native boolean has(String key);

    @JSBody(params = { "key" }, script = "return typeof this[key];")
    public native String getType(String key);

    @JSBody(params = { "key" }, script = "return this[key]();")
    public native JSObject execFuncObject(String key);

    @JSBody(params = { "key" }, script = "return this[key]();")
    public native BaseData execFuncBaseData(String key);

    @JSBody(params = { "key" }, script = "return this[key]();")
    public native String execFuncString(String key);

    @JSBody(params = { "key" }, script = "if(this[key] && this[key].getRef){ return this[key].getRef(); } else { return null; }")
    public native BaseData getRef(String key);

    @JSBody(params = { }, script = "if(this.getRef){ return this.getRef(); } else { return null; }")
    public native BaseData getRef();
}