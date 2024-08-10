
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 10  @  2

+ import java.util.List;
+ import java.util.Map;
+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;
+ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
+ import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
+ 

> CHANGE  4 : 5  @  4 : 8

~ 

> DELETE  23  @  23 : 91

> CHANGE  13 : 14  @  13 : 14

~ public class Item extends ModData {

> CHANGE  2 : 4  @  2 : 3

~ 	protected static final EaglercraftUUID itemModifierUUID = EaglercraftUUID
~ 			.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

> CHANGE  1 : 2  @  1 : 2

~ 	protected static EaglercraftRandom itemRand = new EaglercraftRandom();

> INSERT  20 : 64  @  20

+ 	public void loadModData(BaseData data) {
+ 		maxStackSize = data.getInt("maxStackSize");
+ 		maxDamage = data.getInt("maxDamage");
+ 		bFull3D = data.getBoolean("bFull3D");
+ 		hasSubtypes = data.getBoolean("hasSubtypes");
+ 		potionEffect = data.getString("potionEffect");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("potionEffect", potionEffect);
+ 		data.set("unlocalizedName", unlocalizedName);
+ 		data.set("hasSubtypes", hasSubtypes);
+ 		data.set("bFull3D", bFull3D);
+ 		data.set("maxDamage", maxDamage);
+ 		data.set("maxStackSize", maxStackSize);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackInt("getID", () -> {
+ 			return getIdFromItem(this);
+ 		});
+ 		data.setCallbackObjectWithDataArg("setMaxStackSize", (BaseData params) -> {
+ 			return setMaxStackSize(params.getInt("maxStackSize")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("setHasSubtypes", (BaseData params) -> {
+ 			return setHasSubtypes(params.getBoolean("hasSubtypes")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("setMaxDamage", (BaseData params) -> {
+ 			return setMaxDamage(params.getInt("maxDamageIn")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("setUnlocalizedName", (BaseData params) -> {
+ 			return setUnlocalizedName(params.getString("s")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("setPotionEffect", (BaseData params) -> {
+ 			return setPotionEffect(params.getString("potionEffect")).makeModData();
+ 		});
+ 		return data;
+ 	}
+ 

> INSERT  864 : 868  @  864

+ 
+ 	public float getHeldItemBrightnessEagler() {
+ 		return 0.0f;
+ 	}

> EOF
