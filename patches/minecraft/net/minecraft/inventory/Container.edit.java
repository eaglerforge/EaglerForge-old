
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 4

> INSERT  3 : 9  @  3

+ 
+ import com.google.common.collect.Lists;
+ import com.google.common.collect.Sets;
+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> DELETE  2  @  2 : 5

> CHANGE  5 : 6  @  5 : 6

~ public abstract class Container extends ModData {

> INSERT  1 : 42  @  1

+ 
+ 	public void loadModData(BaseData data) {
+ 		BaseData[] parItemStacks = data.getBaseDataArr("inventoryItemStacks");
+ 		for (int i = 0; i < parItemStacks.length && i < inventoryItemStacks.size(); i++) {
+ 			if (inventoryItemStacks.get(i) != null) {
+ 				inventoryItemStacks.get(i).loadModData(parItemStacks[i]);
+ 			} else if (parItemStacks[i] != null && parItemStacks[i].getRef() instanceof ItemStack) {
+ 				inventoryItemStacks.set(i, (ItemStack) parItemStacks[i].getRef());
+ 			}
+ 		}
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		ModData[] parBaseDatas = new ModData[inventoryItemStacks.size()];
+ 		for (int i = 0; i < inventoryItemStacks.size(); i++) {
+ 			if (inventoryItemStacks.get(i) != null) {
+ 				parBaseDatas[i] = inventoryItemStacks.get(i).makeModData();
+ 			}
+ 		}
+ 		data.set("inventoryItemStacks", parBaseDatas);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackObjectArr("getPlayerList", () -> {
+ 			ModData[] parPlayerList = new ModData[playerList.size()];
+ 			int i = 0;
+ 			for (EntityPlayer player : playerList) {
+ 				if (player != null) {
+ 					parPlayerList[i] = player.makeModData();
+ 				}
+ 				i++;
+ 			}
+ 			return parPlayerList;
+ 		});
+ 		return data;
+ 	}
+ 

> CHANGE  12 : 13  @  12 : 13

~ 		this.inventoryItemStacks.add((ItemStack) null);

> INSERT  521 : 522  @  521

+ 

> EOF
