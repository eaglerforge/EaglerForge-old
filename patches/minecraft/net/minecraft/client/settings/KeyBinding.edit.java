
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 4

> INSERT  2 : 8  @  2

+ 
+ import com.google.common.collect.Lists;
+ import com.google.common.collect.Sets;
+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> CHANGE  3 : 4  @  3 : 4

~ public class KeyBinding extends ModData implements Comparable<KeyBinding> {

> INSERT  60 : 115  @  60

+ 	public void loadModData(BaseData data) {
+ 		keyCode = data.getInt("keyCode");
+ 		pressed = data.getBoolean("pressed");
+ 		pressTime = data.getInt("pressTime");
+ 
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 
+ 		data.set("keyCode", keyCode);
+ 		data.set("pressed", pressed);
+ 		data.set("pressTime", pressTime);
+ 		data.set("keyDescription", keyDescription);
+ 		data.set("keyCategory", keyCategory);
+ 
+ 		data.setCallbackBoolean("isKeyDown", () -> {
+ 			return isKeyDown();
+ 		});
+ 
+ 		data.setCallbackString("getKeyCategory", () -> {
+ 			return getKeyCategory();
+ 		});
+ 
+ 		data.setCallbackBoolean("isPressed", () -> {
+ 			return isPressed();
+ 		});
+ 
+ 		data.setCallbackVoid("unpressKey", () -> {
+ 			unpressKey();
+ 		});
+ 
+ 		data.setCallbackString("getKeyDescription", () -> {
+ 			return getKeyDescription();
+ 		});
+ 
+ 		data.setCallbackInt("getKeyCodeDefault", () -> {
+ 			return getKeyCodeDefault();
+ 		});
+ 
+ 		data.setCallbackInt("getKeyCode", () -> {
+ 			return getKeyCode();
+ 		});
+ 
+ 		return data;
+ 	}
+ 

> EOF
