
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  3 : 5  @  3 : 4

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;

> CHANGE  1 : 2  @  1 : 2

~ public class Vec3i extends ModData implements Comparable<Vec3i> {

> CHANGE  1 : 4  @  1 : 4

~ 	public int x;
~ 	public int y;
~ 	public int z;

> INSERT  23 : 74  @  23

+ 	public void loadModData(BaseData data) {
+ 		x = data.getInt("x");
+ 		y = data.getInt("y");
+ 		z = data.getInt("z");
+ 	}
+ 
+ 	public static Vec3i fromModData(BaseData data) {
+ 		return new Vec3i(data.getInt("x"), data.getInt("y"), data.getInt("z"));
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.set("x", x);
+ 		data.set("y", y);
+ 		data.set("z", z);
+ 		data.setCallbackInt("hashCode", () -> {
+ 			return hashCode();
+ 		});
+ 		data.setCallbackIntWithDataArg("compareTo", (BaseData vec) -> {
+ 			return compareTo(Vec3i.fromModData(vec));
+ 		});
+ 		data.setCallbackInt("getX", () -> {
+ 			return hashCode();
+ 		});
+ 		data.setCallbackInt("getY", () -> {
+ 			return getY();
+ 		});
+ 		data.setCallbackInt("getZ", () -> {
+ 			return getZ();
+ 		});
+ 		data.setCallbackObjectWithDataArg("crossProduct", (BaseData vec) -> {
+ 			return crossProduct(Vec3i.fromModData(vec)).makeModData();
+ 		});
+ 		data.setCallbackDoubleWithDataArg("distanceSq", (BaseData vec) -> {
+ 			return distanceSq(Vec3i.fromModData(vec));
+ 		});
+ 		data.setCallbackDoubleWithDataArg("distanceSqToCenter", (BaseData params) -> {
+ 			return distanceSqToCenter(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackString("toString", () -> {
+ 			return toString();
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
