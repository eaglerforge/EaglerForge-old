
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  3 : 6  @  3

+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> DELETE  3  @  3 : 4

> CHANGE  12 : 13  @  12 : 13

~ public class InventoryPlayer extends ModData implements IInventory {

> INSERT  11 : 62  @  11

+ 	public void loadModData(BaseData data) {
+ 		BaseData[] parItemStacks = data.getBaseDataArr("mainInventory");
+ 		for (int i = 0; i < parItemStacks.length && i < mainInventory.length; i++) {
+ 			if (mainInventory[i] != null) {
+ 				mainInventory[i].loadModData(parItemStacks[i]);
+ 			} else if (parItemStacks[i] != null && parItemStacks[i].getRef() instanceof ItemStack) {
+ 				mainInventory[i] = (ItemStack) parItemStacks[i].getRef();
+ 			}
+ 		}
+ 
+ 		BaseData[] parArmorStacks = data.getBaseDataArr("armorInventory");
+ 		for (int i = 0; i < parArmorStacks.length && i < armorInventory.length; i++) {
+ 			if (armorInventory[i] != null) {
+ 				armorInventory[i].loadModData(parArmorStacks[i]);
+ 			} else if (parItemStacks[i] != null && parItemStacks[i].getRef() instanceof ItemStack) {
+ 				armorInventory[i] = (ItemStack) parItemStacks[i].getRef();
+ 			}
+ 		}
+ 		currentItem = data.getInt("currentItem");
+ 		inventoryChanged = data.getBoolean("inventoryChanged");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		ModData[] parBaseDatas = new ModData[mainInventory.length];
+ 		for (int i = 0; i < mainInventory.length; i++) {
+ 			if (mainInventory[i] != null) {
+ 				parBaseDatas[i] = mainInventory[i].makeModData();
+ 			}
+ 		}
+ 		data.set("mainInventory", parBaseDatas);
+ 
+ 		ModData[] parBaseDatasArmor = new ModData[armorInventory.length];
+ 		for (int i = 0; i < armorInventory.length; i++) {
+ 			if (armorInventory[i] != null) {
+ 				parBaseDatasArmor[i] = armorInventory[i].makeModData();
+ 			}
+ 		}
+ 		data.set("armorInventory", parBaseDatasArmor);
+ 
+ 		data.set("currentItem", currentItem);
+ 		data.set("inventoryChanged", inventoryChanged);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
