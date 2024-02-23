
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 5  @  2 : 3

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;

> DELETE  1  @  1 : 2

> INSERT  1 : 3  @  1

+ import net.minecraft.nbt.JsonToNBT;
+ import net.minecraft.nbt.NBTTagCompound;

> INSERT  23 : 64  @  23

+ 	@Override
+ 	public ModData makeModData() {
+ 		ModData data = super.makeModData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.set("otherPlayerMPPosRotationIncrements", otherPlayerMPPosRotationIncrements);
+ 		data.set("otherPlayerMPX", otherPlayerMPX);
+ 		data.set("otherPlayerMPY", otherPlayerMPY);
+ 		data.set("otherPlayerMPZ", otherPlayerMPZ);
+ 		data.set("otherPlayerMPYaw", otherPlayerMPYaw);
+ 		data.set("otherPlayerMPPitch", otherPlayerMPPitch);
+ 		data.setCallbackVoidWithDataArg("setCurrentItemOrArmor", (BaseData params) -> {
+ 			try {
+ 				NBTTagCompound nbtParsed = JsonToNBT.getTagFromJson(params.getString("itemNbt"));
+ 				ItemStack stack = ItemStack.loadItemStackFromNBT(nbtParsed);
+ 				setCurrentItemOrArmor(params.getInt("slotIn"), stack);
+ 			} catch (Exception e) {
+ 			}
+ 		});
+ 		data.setCallbackBoolean("isSpectator", () -> {
+ 			return isSpectator();
+ 		});
+ 
+ 		return data;
+ 	}
+ 
+ 	@Override
+ 	public void loadModData(BaseData data) {
+ 		super.loadModData(data);
+ 		otherPlayerMPPosRotationIncrements = data.getInt("otherPlayerMPPosRotationIncrements");
+ 		otherPlayerMPX = data.getDouble("otherPlayerMPX");
+ 		otherPlayerMPY = data.getDouble("otherPlayerMPX");
+ 		otherPlayerMPZ = data.getDouble("otherPlayerMPX");
+ 		otherPlayerMPYaw = data.getDouble("otherPlayerMPX");
+ 		otherPlayerMPPitch = data.getDouble("otherPlayerMPX");
+ 	}
+ 

> EOF
