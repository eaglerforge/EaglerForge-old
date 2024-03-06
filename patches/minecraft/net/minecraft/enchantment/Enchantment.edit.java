
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 4

> CHANGE  3 : 9  @  3 : 21

~ 
~ import com.google.common.collect.Lists;
~ import com.google.common.collect.Maps;
~ 
~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;

> CHANGE  8 : 9  @  8 : 9

~ public abstract class Enchantment extends ModData {

> CHANGE  41 : 42  @  41 : 42

~ 	private int weight;

> INSERT  19 : 49  @  19

+ 	public void loadModData(BaseData data) {
+ 		weight = data.getInt("weight");
+ 		name = data.getString("name");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("enchID", effectId);
+ 		data.set("weight", weight);
+ 		data.set("name", name);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		return data;
+ 	}
+ 
+ 	public static ModData makeModDataStatic() {
+ 		ModData data = new ModData();
+ 		Map<ResourceLocation, Enchantment> enchMap = locationEnchantments;
+ 		for (Map.Entry<ResourceLocation, Enchantment> entry : enchMap.entrySet()) {
+ 			if (entry.getKey().resourceName != null && entry.getValue() != null) {
+ 				data.set(entry.getKey().resourceName, entry.getValue().makeModData());
+ 			}
+ 		}
+ 		return data;
+ 	}
+ 

> CHANGE  67 : 69  @  67 : 68

~ 		for (int i = 0; i < enchantmentsList.length; ++i) {
~ 			Enchantment enchantment = enchantmentsList[i];

> EOF
