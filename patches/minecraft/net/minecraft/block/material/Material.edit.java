
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 4  @  2 : 7

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;

> CHANGE  1 : 2  @  1 : 2

~ public class Material extends ModData {

> INSERT  54 : 173  @  54

+ 	public static ModData makeModDataStatic() {
+ 		ModData data = new ModData();
+ 		data.set("air", air.makeModData());
+ 		data.set("grass", grass.makeModData());
+ 		data.set("ground", ground.makeModData());
+ 		data.set("wood", wood.makeModData());
+ 		data.set("rock", rock.makeModData());
+ 		data.set("iron", iron.makeModData());
+ 		data.set("anvil", anvil.makeModData());
+ 		data.set("water", water.makeModData());
+ 		data.set("lava", lava.makeModData());
+ 		data.set("leaves", leaves.makeModData());
+ 		data.set("plants", plants.makeModData());
+ 		data.set("vine", vine.makeModData());
+ 		data.set("sponge", sponge.makeModData());
+ 		data.set("cloth", cloth.makeModData());
+ 		data.set("fire", fire.makeModData());
+ 		data.set("sand", sand.makeModData());
+ 		data.set("circuits", circuits.makeModData());
+ 		data.set("carpet", carpet.makeModData());
+ 		data.set("glass", glass.makeModData());
+ 		data.set("redstoneLight", redstoneLight.makeModData());
+ 		data.set("tnt", tnt.makeModData());
+ 		data.set("coral", coral.makeModData());
+ 		data.set("ice", ice.makeModData());
+ 		data.set("packedIce", packedIce.makeModData());
+ 		data.set("snow", snow.makeModData());
+ 		data.set("craftedSnow", craftedSnow.makeModData());
+ 		data.set("cactus", cactus.makeModData());
+ 		data.set("clay", clay.makeModData());
+ 		data.set("gourd", gourd.makeModData());
+ 		data.set("dragonEgg", dragonEgg.makeModData());
+ 		data.set("portal", portal.makeModData());
+ 		data.set("cake", cake.makeModData());
+ 		data.set("web", web.makeModData());
+ 		data.set("piston", piston.makeModData());
+ 		data.set("barrier", barrier.makeModData());
+ 		return data;
+ 	}
+ 
+ 	public void loadModData(BaseData data) {
+ 		canBurn = data.getBoolean("canBurn");
+ 		replaceable = data.getBoolean("replaceable");
+ 		requiresNoTool = data.getBoolean("requiresNoTool");
+ 		isTranslucent = data.getBoolean("isTranslucent");
+ 		isAdventureModeExempt = data.getBoolean("isAdventureModeExempt");
+ 		materialMapColor.loadModData(data.getBaseData("materialMapColor"));
+ 
+ 		mobilityFlag = data.getInt("mobilityFlag");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.set("canBurn", canBurn);
+ 		data.set("replaceable", replaceable);
+ 		data.set("isTranslucent", isTranslucent);
+ 		data.set("requiresNoTool", requiresNoTool);
+ 		data.set("mobilityFlag", mobilityFlag);
+ 		data.set("isAdventureModeExempt", isAdventureModeExempt);
+ 		data.set("materialMapColor", materialMapColor.makeModData());
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		data.setCallbackBoolean("isLiquid", () -> {
+ 			return isLiquid();
+ 		});
+ 		data.setCallbackBoolean("isSolid", () -> {
+ 			return isSolid();
+ 		});
+ 		data.setCallbackBoolean("isReplaceable", () -> {
+ 			return isReplaceable();
+ 		});
+ 		data.setCallbackBoolean("isToolNotRequired", () -> {
+ 			return isToolNotRequired();
+ 		});
+ 		data.setCallbackBoolean("isOpaque", () -> {
+ 			return isOpaque();
+ 		});
+ 		data.setCallbackBoolean("getCanBurn", () -> {
+ 			return getCanBurn();
+ 		});
+ 		data.setCallbackBoolean("blocksLight", () -> {
+ 			return blocksLight();
+ 		});
+ 		data.setCallbackBoolean("blocksMovement", () -> {
+ 			return blocksMovement();
+ 		});
+ 		data.setCallbackObject("setTranslucent", () -> {
+ 			return setTranslucent().makeModData();
+ 		});
+ 		data.setCallbackObject("setRequiresTool", () -> {
+ 			return setRequiresTool().makeModData();
+ 		});
+ 		data.setCallbackObject("setBurning", () -> {
+ 			return setBurning().makeModData();
+ 		});
+ 		data.setCallbackObject("setReplaceable", () -> {
+ 			return setReplaceable().makeModData();
+ 		});
+ 		data.setCallbackObject("setNoPushMobility", () -> {
+ 			return setNoPushMobility().makeModData();
+ 		});
+ 		data.setCallbackInt("getMaterialMobility", () -> {
+ 			return getMaterialMobility();
+ 		});
+ 		data.setCallbackObject("setImmovableMobility", () -> {
+ 			return setImmovableMobility().makeModData();
+ 		});
+ 		data.setCallbackObject("setAdventureModeExempt", () -> {
+ 			return setAdventureModeExempt().makeModData();
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
