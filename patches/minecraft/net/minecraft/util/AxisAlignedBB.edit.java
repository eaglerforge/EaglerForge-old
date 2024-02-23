
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 4  @  2 : 6

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;

> CHANGE  1 : 8  @  1 : 8

~ public class AxisAlignedBB extends ModData {
~ 	public double minX;
~ 	public double minY;
~ 	public double minZ;
~ 	public double maxX;
~ 	public double maxY;
~ 	public double maxZ;

> INSERT  278 : 348  @  278

+ 
+ 	public void loadModData(BaseData data) {
+ 		minX = data.getDouble("minX");
+ 		minY = data.getDouble("minY");
+ 		minZ = data.getDouble("minZ");
+ 		maxX = data.getDouble("maxX");
+ 		maxY = data.getDouble("maxY");
+ 		maxZ = data.getDouble("maxZ");
+ 	}
+ 
+ 	public static AxisAlignedBB fromModData(BaseData data) {
+ 		return new AxisAlignedBB(data.getDouble("x1"), data.getDouble("y1"), data.getDouble("z1"), data.getDouble("x1"),
+ 				data.getDouble("y1"), data.getDouble("z1"));
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 
+ 		data.set("minX", minX);
+ 		data.set("minY", minY);
+ 		data.set("minZ", minZ);
+ 		data.set("maxX", maxX);
+ 		data.set("maxY", maxY);
+ 		data.set("maxZ", maxZ);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 
+ 		data.setCallbackObjectWithDataArg("addCoord", (BaseData params) -> {
+ 			return addCoord(params.getDouble("x"), params.getDouble("y"), params.getDouble("z")).makeModData();
+ 		});
+ 
+ 		data.setCallbackObjectWithDataArg("contract", (BaseData params) -> {
+ 			return contract(params.getDouble("x"), params.getDouble("y"), params.getDouble("z")).makeModData();
+ 		});
+ 
+ 		data.setCallbackObjectWithDataArg("expand", (BaseData params) -> {
+ 			return expand(params.getDouble("x"), params.getDouble("y"), params.getDouble("z")).makeModData();
+ 		});
+ 
+ 		data.setCallbackObjectWithDataArg("offset", (BaseData params) -> {
+ 			return offset(params.getDouble("x"), params.getDouble("y"), params.getDouble("z")).makeModData();
+ 		});
+ 
+ 		data.setCallbackObjectWithDataArg("union", (BaseData axisAlignedBB) -> {
+ 			return union(AxisAlignedBB.fromModData(axisAlignedBB)).makeModData();
+ 		});
+ 
+ 		data.setCallbackBooleanWithDataArg("intersectsWith", (BaseData axisAlignedBB) -> {
+ 			return intersectsWith(AxisAlignedBB.fromModData(axisAlignedBB));
+ 		});
+ 
+ 		data.setCallbackBooleanWithDataArg("isVecInside", (BaseData vec) -> {
+ 			return isVecInside(Vec3.fromModData(vec));
+ 		});
+ 		data.setCallbackDouble("getAverageEdgeLength", () -> {
+ 			return getAverageEdgeLength();
+ 		});
+ 
+ 		data.setCallbackString("toString", () -> {
+ 			return toString();
+ 		});
+ 
+ 		return data;
+ 	}

> EOF
