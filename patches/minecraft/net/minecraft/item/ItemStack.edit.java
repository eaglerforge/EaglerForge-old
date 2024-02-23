
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 5

> DELETE  3  @  3 : 4

> INSERT  1 : 12  @  1

+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;
+ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
+ import net.lax1dude.eaglercraft.v1_8.HString;
+ import java.util.Set;
+ 
+ import com.google.common.collect.HashMultimap;
+ import com.google.common.collect.Lists;
+ import com.google.common.collect.Multimap;
+ 

> CHANGE  13 : 14  @  13 : 20

~ import net.minecraft.nbt.*;

> CHANGE  10 : 11  @  10 : 11

~ public final class ItemStack extends ModData {

> INSERT  16 : 168  @  16

+ 	public void loadModData(BaseData data) {
+ 		stackSize = data.getInt("amount");
+ 		animationsToGo = data.getInt("animationsToGo");
+ 		itemDamage = data.getInt("itemDamage");
+ 		if (itemFrame != null) {
+ 			itemFrame.loadModData(data.getBaseData("itemFrame"));
+ 		}
+ 		if (canDestroyCacheBlock != null) {
+ 			canDestroyCacheBlock.loadModData(data.getBaseData("canDestroyCacheBlock"));
+ 		}
+ 		if (canPlaceOnCacheBlock != null) {
+ 			canPlaceOnCacheBlock.loadModData(data.getBaseData("canPlaceOnCacheBlock"));
+ 		}
+ 		canDestroyCacheResult = data.getBoolean("canDestroyCacheResult");
+ 		canPlaceOnCacheResult = data.getBoolean("canPlaceOnCacheResult");
+ 	}
+ 
+ 	public static ItemStack fromModData(BaseData data) {
+ 		return new ItemStack(Item.getItemById(data.getInt("itemId")), data.getInt("amount"), data.getInt("itemDamage"));
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("amount", stackSize);
+ 		data.set("animationsToGo", animationsToGo);
+ 		data.set("itemId", item.getIdFromItem(item));
+ 		data.set("itemDamage", itemDamage);
+ 		if (itemFrame != null) {
+ 			data.set("itemFrame", itemFrame.makeModData());
+ 		}
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		if (canDestroyCacheBlock != null) {
+ 			data.set("canDestroyCacheBlock", canDestroyCacheBlock.makeModData());
+ 		}
+ 		data.set("canDestroyCacheResult", canDestroyCacheResult);
+ 		if (canPlaceOnCacheBlock != null) {
+ 			data.set("canPlaceOnCacheBlock", canPlaceOnCacheBlock.makeModData());
+ 		}
+ 		data.set("canPlaceOnCacheResult", canPlaceOnCacheResult);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getItem", () -> {
+ 			return getItem().makeModData();
+ 		});
+ 		data.setCallbackInt("getMaxStackSize", () -> {
+ 			return getMaxStackSize();
+ 		});
+ 		data.setCallbackBoolean("isStackable", () -> {
+ 			return isStackable();
+ 		});
+ 		data.setCallbackBoolean("isItemStackDamageable", () -> {
+ 			return isItemStackDamageable();
+ 		});
+ 		data.setCallbackBoolean("getHasSubtypes", () -> {
+ 			return getHasSubtypes();
+ 		});
+ 		data.setCallbackBoolean("isItemDamaged", () -> {
+ 			return isItemDamaged();
+ 		});
+ 		data.setCallbackInt("getItemDamage", () -> {
+ 			return getItemDamage();
+ 		});
+ 		data.setCallbackInt("getMetadata", () -> {
+ 			return getMetadata();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setItemDamage", (BaseData params) -> {
+ 			setItemDamage(params.getInt("meta"));
+ 		});
+ 		data.setCallbackInt("getMaxDamage", () -> {
+ 			return getMaxDamage();
+ 		});
+ 		data.setCallbackObject("copy", () -> {
+ 			return copy().makeModData();
+ 		});
+ 		data.setCallbackString("getUnlocalizedName", () -> {
+ 			return getUnlocalizedName();
+ 		});
+ 		data.setCallbackString("toString", () -> {
+ 			return toString();
+ 		});
+ 		data.setCallbackInt("getMaxItemUseDuration", () -> {
+ 			return getMaxItemUseDuration();
+ 		});
+ 		data.setCallbackString("getDisplayName", () -> {
+ 			return getDisplayName();
+ 		});
+ 		data.setCallbackObjectWithDataArg("setDisplayName", (BaseData params) -> {
+ 			return setStackDisplayName(params.getString("displayName")).makeModData();
+ 		});
+ 		data.setCallbackVoid("clearCustomName", () -> {
+ 			clearCustomName();
+ 		});
+ 		data.setCallbackBoolean("hasDisplayName", () -> {
+ 			return hasDisplayName();
+ 		});
+ 		data.setCallbackBoolean("hasEffect", () -> {
+ 			return hasEffect();
+ 		});
+ 		data.setCallbackBoolean("isItemEnchantable", () -> {
+ 			return isItemEnchantable();
+ 		});
+ 		data.setCallbackVoidWithDataArg("addEnchantment", (BaseData params) -> {
+ 			if (params.getBaseData("ench") instanceof Enchantment) {
+ 				addEnchantment((Enchantment) params.getBaseData("ench"), params.getInt("level"));
+ 			}
+ 		});
+ 		data.setCallbackBoolean("isItemEnchanted", () -> {
+ 			return isItemEnchanted();
+ 		});
+ 		data.setCallbackBoolean("canEditBlocks", () -> {
+ 			return canEditBlocks();
+ 		});
+ 		data.setCallbackBoolean("isOnItemFrame", () -> {
+ 			return isOnItemFrame();
+ 		});
+ 		data.setCallbackInt("getRepairCost", () -> {
+ 			return getRepairCost();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setRepairCost", (BaseData params) -> {
+ 			setRepairCost(params.getInt("cost"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setItem", (BaseData params) -> {
+ 			if (params.getBaseData("newItem") instanceof Item) {
+ 				setItem((Item) params.getBaseData("newItem"));
+ 			}
+ 		});
+ 		data.setCallbackBooleanWithDataArg("canDestroy", (BaseData params) -> {
+ 			return canDestroy(Block.getBlockById(params.getInt("blockId")));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("canPlaceOn", (BaseData params) -> {
+ 			return canPlaceOn(Block.getBlockById(params.getInt("blockId")));
+ 		});
+ 		data.setCallbackString("toNBT", () -> {
+ 			return toNBT();
+ 		});
+ 		data.setCallbackVoidWithDataArg("fromNBT", (BaseData params) -> {
+ 			fromNBT(params.getString("nbt"));
+ 		});
+ 		data.setCallbackStringArr("getLore", () -> {
+ 			return getLore();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setLore", (BaseData params) -> {
+ 			setLore(params.getStringArr("lore"));
+ 		});
+ 
+ 		return data;
+ 	}
+ 

> INSERT  91 : 106  @  91

+ 	public String toNBT() {
+ 		NBTTagCompound nbt = new NBTTagCompound();
+ 		nbt = writeToNBT(nbt);
+ 		return nbt.toString();
+ 	}
+ 
+ 	public void fromNBT(String nbt) {
+ 		try {
+ 			NBTTagCompound nbtParsed = JsonToNBT.getTagFromJson(nbt);
+ 			this.readFromNBT(nbtParsed);
+ 		} catch (Exception e) {
+ 			// Swallowing the error!
+ 		}
+ 	}
+ 

> CHANGE  64 : 65  @  64 : 65

~ 	public boolean attemptDamageItem(int amount, EaglercraftRandom rand) {

> INSERT  210 : 244  @  210

+ 	public void setLore(String[] loreIn) {
+ 		if (this.stackTagCompound == null) {
+ 			this.stackTagCompound = new NBTTagCompound();
+ 		}
+ 		if (!this.stackTagCompound.hasKey("display", 10)) {
+ 			this.stackTagCompound.setTag("display", new NBTTagCompound());
+ 		}
+ 		NBTTagCompound display = this.stackTagCompound.getCompoundTag("display");
+ 		NBTTagList lore = new NBTTagList();
+ 		for (String strIn : loreIn) {
+ 			lore.appendTag(new NBTTagString(strIn));
+ 		}
+ 		display.setTag("Lore", lore);
+ 	}
+ 
+ 	public String[] getLore() {
+ 		if (this.stackTagCompound == null) {
+ 			return new String[0];
+ 		}
+ 		if (!this.stackTagCompound.hasKey("display", 10)) {
+ 			return new String[0];
+ 		}
+ 		NBTTagCompound display = this.stackTagCompound.getCompoundTag("display");
+ 		if (!display.hasKey("Lore", 9)) {
+ 			return new String[0];
+ 		}
+ 		NBTTagList lore = (NBTTagList) display.getTag("Lore");
+ 		String[] outStrArr = new String[lore.tagCount()];
+ 		for (int i = 0; i < outStrArr.length; i++) {
+ 			outStrArr[i] = lore.getStringTagAt(i);
+ 		}
+ 		return outStrArr;
+ 	}
+ 

> CHANGE  39 : 40  @  39 : 40

~ 				s = s + HString.format("#%04d/%d%s",

> CHANGE  2 : 3  @  2 : 3

~ 				s = s + HString.format("#%04d%s", new Object[] { Integer.valueOf(i), s1 });

> CHANGE  56 : 57  @  56 : 57

~ 			for (Entry entry : (Set<Entry>) multimap.entries()) {

> EOF
