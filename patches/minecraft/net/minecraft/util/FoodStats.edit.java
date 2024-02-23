
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 4  @  2

+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> DELETE  4  @  4 : 5

> CHANGE  2 : 3  @  2 : 3

~ public class FoodStats extends ModData {

> INSERT  6 : 54  @  6

+ 	public void loadModData(BaseData data) {
+ 		foodLevel = data.getInt("foodLevel");
+ 		foodSaturationLevel = data.getFloat("foodSaturationLevel");
+ 		foodExhaustionLevel = data.getFloat("foodExhaustionLevel");
+ 		foodTimer = data.getInt("foodTimer");
+ 		prevFoodLevel = data.getInt("prevFoodLevel");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.set("foodLevel", foodLevel);
+ 		data.set("foodSaturationLevel", foodSaturationLevel);
+ 		data.set("foodExhaustionLevel", foodExhaustionLevel);
+ 		data.set("foodTimer", foodTimer);
+ 		data.set("prevFoodLevel", prevFoodLevel);
+ 		data.setCallbackVoidWithDataArg("addStats", (BaseData params) -> {
+ 			addStats(params.getInt("foodLevelIn"), params.getFloat("foodSaturationModifier"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("addExhaustion", (BaseData params) -> {
+ 			addExhaustion(params.getFloat("parFloat1"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setFoodLevel", (BaseData params) -> {
+ 			setFoodLevel(params.getInt("foodLevelIn"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setFoodSaturationLevel", (BaseData params) -> {
+ 			setFoodSaturationLevel(params.getFloat("foodSaturationLevelIn"));
+ 		});
+ 		data.setCallbackInt("getFoodLevel", () -> {
+ 			return getFoodLevel();
+ 		});
+ 		data.setCallbackInt("getPrevFoodLevel", () -> {
+ 			return getPrevFoodLevel();
+ 		});
+ 		data.setCallbackFloat("getSaturationLevel", () -> {
+ 			return getSaturationLevel();
+ 		});
+ 		data.setCallbackBoolean("needFood", () -> {
+ 			return needFood();
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
