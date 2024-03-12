
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 3

> INSERT  1 : 6  @  1

+ 
+ import com.google.common.collect.Lists;
+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> DELETE  1  @  1 : 3

> CHANGE  5 : 6  @  5 : 6

~ public class InventoryBasic extends ModData implements IInventory {

> INSERT  6 : 42  @  6

+ 	public void loadModData(BaseData data) {
+ 		BaseData[] parItemStacks = data.getBaseDataArr("inventoryContents");
+ 		for (int i = 0; i < parItemStacks.length && i < inventoryContents.length; i++) {
+ 			if (inventoryContents[i] != null) {
+ 				inventoryContents[i].loadModData(parItemStacks[i]);
+ 			} else if (parItemStacks[i] != null && parItemStacks[i].getRef() instanceof ItemStack) {
+ 				inventoryContents[i] = (ItemStack) parItemStacks[i].getRef();
+ 			}
+ 		}
+ 
+ 		inventoryTitle = data.getString("inventoryTitle");
+ 		slotsCount = data.getInt("slotsCount");
+ 		hasCustomName = data.getBoolean("hasCustomName");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		ModData[] parBaseDatas = new ModData[inventoryContents.length];
+ 		for (int i = 0; i < inventoryContents.length; i++) {
+ 			if (inventoryContents[i] != null) {
+ 				parBaseDatas[i] = inventoryContents[i].makeModData();
+ 			}
+ 		}
+ 		data.set("inventoryContents", parBaseDatas);
+ 		data.set("inventoryTitle", inventoryTitle);
+ 		data.set("slotsCount", slotsCount);
+ 		data.set("hasCustomName", hasCustomName);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
