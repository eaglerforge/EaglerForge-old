
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 4  @  2 : 4

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;

> CHANGE  1 : 5  @  1 : 5

~ public class Vec3 extends ModData {
~ 	public double xCoord;
~ 	public double yCoord;
~ 	public double zCoord;

> INSERT  142 : 215  @  142

+ 
+ 	public void loadModData(BaseData data) {
+ 		xCoord = data.getDouble("x");
+ 		yCoord = data.getDouble("y");
+ 		zCoord = data.getDouble("z");
+ 	}
+ 
+ 	public static Vec3 fromModData(BaseData data) {
+ 		return new Vec3(data.getDouble("x"), data.getDouble("y"), data.getDouble("z"));
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("x", xCoord);
+ 		data.set("y", yCoord);
+ 		data.set("z", zCoord);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackVoidWithDataArg("subtractReverse", (BaseData vec3) -> {
+ 			subtractReverse(Vec3.fromModData(vec3));
+ 		});
+ 		data.setCallbackObject("normalize", () -> {
+ 			return normalize().makeModData();
+ 		});
+ 		data.setCallbackDoubleWithDataArg("dotProduct", (BaseData vec3) -> {
+ 			return dotProduct(Vec3.fromModData(vec3));
+ 		});
+ 		data.setCallbackObjectWithDataArg("crossProduct", (BaseData vec3) -> {
+ 			return crossProduct(Vec3.fromModData(vec3)).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("subtract", (BaseData params) -> {
+ 			return subtract(params.getDouble("x"), params.getDouble("y"), params.getDouble("z")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("addVector", (BaseData params) -> {
+ 			return addVector(params.getDouble("x"), params.getDouble("y"), params.getDouble("z")).makeModData();
+ 		});
+ 		data.setCallbackDoubleWithDataArg("distanceTo", (BaseData vec3) -> {
+ 			return distanceTo(Vec3.fromModData(vec3));
+ 		});
+ 		data.setCallbackDoubleWithDataArg("squareDistanceTo", (BaseData vec3) -> {
+ 			return squareDistanceTo(Vec3.fromModData(vec3));
+ 		});
+ 		data.setCallbackDouble("lengthVector", () -> {
+ 			return lengthVector();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getIntermediateWithXValue", (BaseData params) -> {
+ 			return getIntermediateWithXValue(Vec3.fromModData(params.getBaseData("vec")), params.getDouble("x"))
+ 					.makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getIntermediateWithYValue", (BaseData params) -> {
+ 			return getIntermediateWithYValue(Vec3.fromModData(params.getBaseData("vec")), params.getDouble("y"))
+ 					.makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getIntermediateWithZValue", (BaseData params) -> {
+ 			return getIntermediateWithZValue(Vec3.fromModData(params.getBaseData("vec")), params.getDouble("z"))
+ 					.makeModData();
+ 		});
+ 		data.setCallbackString("toString", () -> {
+ 			return toString();
+ 		});
+ 		data.setCallbackObjectWithDataArg("rotatePitch", (BaseData params) -> {
+ 			return rotatePitch(params.getFloat("pitch")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("rotateYaw", (BaseData params) -> {
+ 			return rotateYaw(params.getFloat("yaw")).makeModData();
+ 		});
+ 
+ 		return data;
+ 	}

> EOF
