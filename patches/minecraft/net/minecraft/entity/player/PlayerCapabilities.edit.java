
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 5  @  2

+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModAPI;
+ import net.eaglerforge.api.ModData;

> CHANGE  2 : 3  @  2 : 3

~ public class PlayerCapabilities extends ModData {

> INSERT  8 : 49  @  8

+ 	public void loadModData(BaseData data) {
+ 		disableDamage = data.getBoolean("disableDamage");
+ 		isFlying = data.getBoolean("isFlying");
+ 		allowFlying = data.getBoolean("allowFlying");
+ 		isCreativeMode = data.getBoolean("isCreativeMode");
+ 		allowEdit = data.getBoolean("allowEdit");
+ 
+ 		flySpeed = data.getFloat("flySpeed");
+ 		walkSpeed = data.getFloat("walkSpeed");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("disableDamage", disableDamage);
+ 		data.set("isFlying", isFlying);
+ 		data.set("allowFlying", allowFlying);
+ 		data.set("isCreativeMode", isCreativeMode);
+ 		data.set("allowEdit", allowEdit);
+ 		data.set("flySpeed", flySpeed);
+ 		data.set("walkSpeed", walkSpeed);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackFloat("getFlySpeed", () -> {
+ 			return getFlySpeed();
+ 		});
+ 		data.setCallbackFloat("getWalkSpeed", () -> {
+ 			return getWalkSpeed();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setFlySpeed", (BaseData params) -> {
+ 			setFlySpeed(params.getFloat("speed"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPlayerWalkSpeed", (BaseData params) -> {
+ 			setPlayerWalkSpeed(params.getFloat("speed"));
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
