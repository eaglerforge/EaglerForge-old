
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  4 : 8  @  4 : 5

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
~ 

> CHANGE  2 : 3  @  2 : 3

~ import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;

> INSERT  5 : 6  @  5

+ import net.minecraft.command.ICommandSender;

> DELETE  19  @  19 : 23

> CHANGE  45 : 46  @  45 : 46

~ public abstract class EntityPlayer extends EntityLivingBase implements ICommandSender {

> INSERT  65 : 481  @  65

+ 	@Override
+ 	public void loadModData(BaseData data) {
+ 		super.loadModData(data);
+ 		cameraYaw = data.getFloat("cameraYaw");
+ 		chasingPosX = data.getDouble("chasingPosX");
+ 		chasingPosY = data.getDouble("chasingPosY");
+ 		chasingPosZ = data.getDouble("chasingPosZ");
+ 		experience = data.getFloat("experience");
+ 		experienceLevel = data.getInt("experienceLevel");
+ 		experienceTotal = data.getInt("experienceTotal");
+ 		if (fishEntity != null) {
+ 			fishEntity.loadModData(data.getBaseData("fishEntity"));
+ 		}
+ 		if (foodStats != null) {
+ 			foodStats.loadModData(data.getBaseData("foodStats"));
+ 		}
+ 		if (inventory != null) {
+ 			inventory.loadModData(data.getBaseData("inventory"));
+ 		}
+ 		flyToggleTimer = data.getInt("flyToggleTimer");
+ 		hasReducedDebug = data.getBoolean("hasReducedDebug");
+ 		lastXPSound = data.getInt("lastXPSound");
+ 		sleepTimer = data.getInt("sleepTimer");
+ 		sleeping = data.getBoolean("sleeping");
+ 		spawnForced = data.getBoolean("spawnForced");
+ 		speedInAir = data.getFloat("speedInAir");
+ 		speedOnGround = data.getFloat("speedOnGround");
+ 		xpCooldown = data.getInt("xpCooldown");
+ 		xpSeed = data.getInt("xpSeed");
+ 		if (itemInUse != null) {
+ 			itemInUse.loadModData(data.getBaseData("itemInUse"));
+ 		}
+ 
+ 		if (inventoryContainer != null) {
+ 			inventoryContainer.loadModData(data.getBaseData("inventoryContainer"));
+ 		}
+ 
+ 		if (openContainer != null) {
+ 			openContainer.loadModData(data.getBaseData("openContainer"));
+ 		}
+ 		if (capabilities != null) {
+ 			capabilities.loadModData(data.getBaseData("capabilities"));
+ 		}
+ 	}
+ 
+ 	@Override
+ 	public ModData makeModData() {
+ 		ModData data = super.makeModData();
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.set("cameraYaw", cameraYaw);
+ 		data.set("chasingPosX", chasingPosX);
+ 		data.set("chasingPosY", chasingPosY);
+ 		data.set("chasingPosZ", chasingPosZ);
+ 		data.set("experience", experience);
+ 		data.set("experienceLevel", experienceLevel);
+ 		data.set("experienceTotal", experienceTotal);
+ 		if (fishEntity != null) {
+ 			data.set("fishEntity", fishEntity.makeModData());
+ 		}
+ 		if (itemInUse != null) {
+ 			data.set("itemInUse", itemInUse.makeModData());
+ 		}
+ 		if (foodStats != null) {
+ 			data.set("foodStats", foodStats.makeModData());
+ 		}
+ 		data.set("flyToggleTimer", flyToggleTimer);
+ 		data.set("hasReducedDebug", hasReducedDebug);
+ 		data.set("itemInUseCount", itemInUseCount);
+ 		data.set("lastXPSound", lastXPSound);
+ 		data.set("sleepTimer", sleepTimer);
+ 		data.set("sleeping", sleeping);
+ 		data.set("spawnForced", spawnForced);
+ 		data.set("speedInAir", speedInAir);
+ 		data.set("speedOnGround", speedOnGround);
+ 		data.set("xpCooldown", xpCooldown);
+ 		data.set("xpSeed", xpSeed);
+ 
+ 		if (inventoryContainer != null) {
+ 			data.set("inventoryContainer", inventoryContainer.makeModData());
+ 		}
+ 
+ 		if (openContainer != null) {
+ 			data.set("openContainer", openContainer.makeModData());
+ 		}
+ 		if (inventory != null) {
+ 			data.set("inventory", inventory.makeModData());
+ 		}
+ 		if (capabilities != null) {
+ 			data.set("capabilities", capabilities.makeModData());
+ 		}
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		// Todone: adding functions
+ 
+ 		data.setCallbackObject("getItemInUse", () -> {
+ 			if (getItemInUse() != null) {
+ 				return getItemInUse().makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackInt("getItemInUseCount", () -> {
+ 			return getItemInUseCount();
+ 		});
+ 		data.setCallbackBoolean("isUsingItem", () -> {
+ 			return isUsingItem();
+ 		});
+ 		data.setCallbackInt("getItemInUseDuration", () -> {
+ 			return getItemInUseDuration();
+ 		});
+ 		data.setCallbackVoid("stopUsingItem", () -> {
+ 			stopUsingItem();
+ 		});
+ 		data.setCallbackVoid("clearItemInUse", () -> {
+ 			clearItemInUse();
+ 		});
+ 		data.setCallbackBoolean("isBlocking", () -> {
+ 			return isBlocking();
+ 		});
+ 		data.setCallbackInt("getMaxInPortalTime", () -> {
+ 			return getMaxInPortalTime();
+ 		});
+ 		data.setCallbackString("getSwimSound", () -> {
+ 			return getSwimSound();
+ 		});
+ 		data.setCallbackString("getSplashSound", () -> {
+ 			return getSplashSound();
+ 		});
+ 		data.setCallbackInt("getPortalCooldown", () -> {
+ 			return getPortalCooldown();
+ 		});
+ 		data.setCallbackVoidWithDataArg("playSound", (BaseData params) -> {
+ 			playSound(params.getString("s"), params.getFloat("f"), params.getFloat("f1"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("updateItemUse", (BaseData params) -> {
+ 			updateItemUse((ItemStack) params.getRef("itemStackIn"), params.getInt("parInt1"));
+ 		});
+ 		data.setCallbackVoid("onItemUseFinish", () -> {
+ 			onItemUseFinish();
+ 		});
+ 		data.setCallbackVoidWithDataArg("handleStatusUpdate", (BaseData params) -> {
+ 			handleStatusUpdate(params.getByte("b0"));
+ 		});
+ 		data.setCallbackBoolean("isMovementBlocked", () -> {
+ 			return isMovementBlocked();
+ 		});
+ 		data.setCallbackVoid("closeScreen", () -> {
+ 			closeScreen();
+ 		});
+ 		data.setCallbackVoid("updateRidden", () -> {
+ 			updateRidden();
+ 		});
+ 		data.setCallbackVoid("preparePlayerToSpawn", () -> {
+ 			preparePlayerToSpawn();
+ 		});
+ 		data.setCallbackVoid("updateEntityActionState", () -> {
+ 			updateEntityActionState();
+ 		});
+ 		data.setCallbackVoid("onLivingUpdate", () -> {
+ 			onLivingUpdate();
+ 		});
+ 		data.setCallbackVoidWithDataArg("collideWithPlayer", (BaseData params) -> {
+ 			collideWithPlayer((Entity) params.getRef("parEntity"));
+ 		});
+ 		data.setCallbackInt("getScore", () -> {
+ 			return getScore();
+ 		});
+ 		data.setCallbackVoidWithDataArg("addScore", (BaseData params) -> {
+ 			addScore(params.getInt("parInt1"));
+ 		});
+ 		data.setCallbackString("getHurtSound", () -> {
+ 			return getHurtSound();
+ 		});
+ 		data.setCallbackString("getDeathSound", () -> {
+ 			return getDeathSound();
+ 		});
+ 		data.setCallbackVoidWithDataArg("addToPlayerScore", (BaseData params) -> {
+ 			addToPlayerScore((Entity) params.getRef("entity"), params.getInt("i"));
+ 		});
+ 		data.setCallbackObjectWithDataArg("dropOneItem", (BaseData params) -> {
+ 			return dropOneItem(params.getBoolean("flag")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("dropPlayerItemWithRandomChoice", (BaseData params) -> {
+ 			// The second argument (boolean) is not used.
+ 			return dropPlayerItemWithRandomChoice((ItemStack) params.getRef("itemStackIn"), false).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("dropItem", (BaseData params) -> {
+ 			return dropItem((ItemStack) params.getRef("droppedItem"), params.getBoolean("dropAround"),
+ 					params.getBoolean("traceItem")).makeModData();
+ 		});
+ 		data.setCallbackVoidWithDataArg("joinEntityItemWithWorld", (BaseData params) -> {
+ 			joinEntityItemWithWorld((EntityItem) params.getRef("entityitem"));
+ 		});
+ 		data.setCallbackFloatWithDataArg("getToolDigEfficiency", (BaseData params) -> {
+ 			return getToolDigEfficiency((Block) params.getRef("parBlock"));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("canHarvestBlock", (BaseData params) -> {
+ 			return canHarvestBlock((Block) params.getRef("blockToHarvest"));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("canAttackPlayer", (BaseData params) -> {
+ 			return canAttackPlayer((EntityPlayer) params.getRef("entityplayer"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("damageArmor", (BaseData params) -> {
+ 			damageArmor(params.getFloat("f"));
+ 		});
+ 		data.setCallbackInt("getTotalArmorValue", () -> {
+ 			return getTotalArmorValue();
+ 		});
+ 		data.setCallbackFloat("getArmorVisibility", () -> {
+ 			return getArmorVisibility();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("interactWith", (BaseData params) -> {
+ 			return interactWith((Entity) params.getRef("parEntity"));
+ 		});
+ 		data.setCallbackObject("getCurrentEquippedItem", () -> {
+ 			if (getCurrentEquippedItem() != null) {
+ 				return getCurrentEquippedItem().makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackVoid("destroyCurrentEquippedItem", () -> {
+ 			destroyCurrentEquippedItem();
+ 		});
+ 		data.setCallbackDouble("getYOffset", () -> {
+ 			return getYOffset();
+ 		});
+ 		data.setCallbackVoidWithDataArg("attackTargetEntityWithCurrentItem", (BaseData params) -> {
+ 			attackTargetEntityWithCurrentItem((Entity) params.getRef("entity"));
+ 		});
+ 		data.setCallbackVoid("respawnPlayer", () -> {
+ 			respawnPlayer();
+ 		});
+ 		data.setCallbackBoolean("isEntityInsideOpaqueBlock", () -> {
+ 			return isEntityInsideOpaqueBlock();
+ 		});
+ 		data.setCallbackBoolean("isUser", () -> {
+ 			return isUser();
+ 		});
+ 		data.setCallbackStringWithDataArg("trySleep", (BaseData params) -> {
+ 			return trySleep((BlockPos) params.getRef("blockpos")).name();
+ 		});
+ 		data.setCallbackVoidWithDataArg("wakeUpPlayer", (BaseData params) -> {
+ 			wakeUpPlayer(params.getBoolean("flag"), params.getBoolean("flag1"), params.getBoolean("flag2"));
+ 		});
+ 		data.setCallbackBoolean("isInBed", () -> {
+ 			return isInBed();
+ 		});
+ 		data.setCallbackFloat("getBedOrientationInDegrees", () -> {
+ 			return getBedOrientationInDegrees();
+ 		});
+ 		data.setCallbackBoolean("isPlayerSleeping", () -> {
+ 			return isPlayerSleeping();
+ 		});
+ 		data.setCallbackBoolean("isPlayerFullyAsleep", () -> {
+ 			return isPlayerFullyAsleep();
+ 		});
+ 		data.setCallbackInt("getSleepTimer", () -> {
+ 			return getSleepTimer();
+ 		});
+ 		data.setCallbackObject("getBedLocation", () -> {
+ 			if (getBedLocation() != null) {
+ 				return getBedLocation().makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackBoolean("isSpawnForced", () -> {
+ 			return isSpawnForced();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setSpawnPoint", (BaseData params) -> {
+ 			setSpawnPoint((BlockPos) params.getRef("pos"), params.getBoolean("forced"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("moveEntityWithHeading", (BaseData params) -> {
+ 			moveEntityWithHeading(params.getFloat("f"), params.getFloat("f1"));
+ 		});
+ 		data.setCallbackFloat("getAIMoveSpeed", () -> {
+ 			return getAIMoveSpeed();
+ 		});
+ 		data.setCallbackVoidWithDataArg("addMovementStat", (BaseData params) -> {
+ 			addMovementStat(params.getDouble("parDouble1"), params.getDouble("parDouble2"),
+ 					params.getDouble("parDouble3"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("addMountedMovementStat", (BaseData params) -> {
+ 			addMountedMovementStat(params.getDouble("parDouble1"), params.getDouble("parDouble2"),
+ 					params.getDouble("parDouble3"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("fall", (BaseData params) -> {
+ 			fall(params.getFloat("f"), params.getFloat("f1"));
+ 		});
+ 		data.setCallbackVoid("resetHeight", () -> {
+ 			resetHeight();
+ 		});
+ 		data.setCallbackStringWithDataArg("getFallSoundString", (BaseData params) -> {
+ 			return getFallSoundString(params.getInt("i"));
+ 		});
+ 		data.setCallbackVoid("setInWeb", () -> {
+ 			setInWeb();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getCurrentArmor", (BaseData params) -> {
+ 			if (getCurrentArmor(params.getInt("i")) != null) {
+ 				return getCurrentArmor(params.getInt("i")).makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("addExperience", (BaseData params) -> {
+ 			addExperience(params.getInt("amount"));
+ 		});
+ 		data.setCallbackInt("getXPSeed", () -> {
+ 			return getXPSeed();
+ 		});
+ 		data.setCallbackVoidWithDataArg("removeExperienceLevel", (BaseData params) -> {
+ 			removeExperienceLevel(params.getInt("i"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("addExperienceLevel", (BaseData params) -> {
+ 			addExperienceLevel(params.getInt("i"));
+ 		});
+ 		data.setCallbackInt("xpBarCap", () -> {
+ 			return xpBarCap();
+ 		});
+ 		data.setCallbackVoidWithDataArg("addExhaustion", (BaseData params) -> {
+ 			addExhaustion(params.getFloat("parFloat1"));
+ 		});
+ 		data.setCallbackObject("getFoodStats", () -> {
+ 			return getFoodStats().makeModData();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("canEat", (BaseData params) -> {
+ 			return canEat(params.getBoolean("ignoreHunger"));
+ 		});
+ 		data.setCallbackBoolean("shouldHeal", () -> {
+ 			return shouldHeal();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setItemInUse", (BaseData params) -> {
+ 			setItemInUse((ItemStack) params.getRef("itemstack"), params.getInt("i"));
+ 		});
+ 		data.setCallbackBoolean("isAllowEdit", () -> {
+ 			return isAllowEdit();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("canPlayerEdit", (BaseData params) -> {
+ 			return canPlayerEdit((BlockPos) params.getRef("parBlockPos"),
+ 					EnumFacing.valueOf(params.getString("parEnumFacing")), (ItemStack) params.getRef("parItemStack"));
+ 		});
+ 		data.setCallbackBoolean("isPlayer", () -> {
+ 			return isPlayer();
+ 		});
+ 		data.setCallbackBoolean("getAlwaysRenderNameTagForRender", () -> {
+ 			return getAlwaysRenderNameTagForRender();
+ 		});
+ 		data.setCallbackVoidWithDataArg("clonePlayer", (BaseData params) -> {
+ 			clonePlayer((EntityPlayer) params.getRef("entityplayer"), params.getBoolean("flag"));
+ 		});
+ 		data.setCallbackBoolean("canTriggerWalking", () -> {
+ 			return canTriggerWalking();
+ 		});
+ 		data.setCallbackVoid("sendPlayerAbilities", () -> {
+ 			sendPlayerAbilities();
+ 		});
+ 		data.setCallbackString("getName", () -> {
+ 			return getName();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getEquipmentInSlot", (BaseData params) -> {
+ 			if (getEquipmentInSlot(params.getInt("i")) != null) {
+ 				return getEquipmentInSlot(params.getInt("i")).makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackObject("getHeldItem", () -> {
+ 			if (getHeldItem() != null) {
+ 				return getHeldItem();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("setCurrentItemOrArmor", (BaseData params) -> {
+ 			setCurrentItemOrArmor(params.getInt("i"), (ItemStack) params.getRef("itemstack"));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("isInvisibleToPlayer", (BaseData params) -> {
+ 			return isInvisibleToPlayer((EntityPlayer) params.getRef("entityplayer"));
+ 		});
+ 		data.setCallbackObjectArr("getInventory", () -> {
+ 			ItemStack[] inventory = getInventory();
+ 			ModData[] parDatas = new ModData[inventory.length];
+ 			for (int i = 0; i < inventory.length; i++) {
+ 				if (inventory[i] != null) {
+ 					parDatas[i] = inventory[i].makeModData();
+ 				}
+ 			}
+ 			return parDatas;
+ 		});
+ 		data.setCallbackBoolean("isPushedByWater", () -> {
+ 			return isPushedByWater();
+ 		});
+ 		data.setCallbackFloat("getEyeHeight", () -> {
+ 			return getEyeHeight();
+ 		});
+ 		data.setCallbackStringWithDataArg("getOfflineUUID", (BaseData params) -> {
+ 			return getOfflineUUID(params.getString("username")).toString();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("replaceItemInInventory", (BaseData params) -> {
+ 			return replaceItemInInventory(params.getInt("i"), (ItemStack) params.getRef("itemstack"));
+ 		});
+ 		data.setCallbackBoolean("hasReducedDebug", () -> {
+ 			return hasReducedDebug();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setReducedDebug", (BaseData params) -> {
+ 			setReducedDebug(params.getBoolean("reducedDebug"));
+ 		});
+ 		return data;
+ 	}
+ 

> CHANGE  393 : 394  @  393 : 394

~ 		Collection<ScoreObjective> collection = this.getWorldScoreboard()

> CHANGE  271 : 274  @  271 : 273

~ 		ItemStack[] stack = this.inventory.armorInventory;
~ 		for (int j = 0; j < stack.length; ++j) {
~ 			if (stack[j] != null) {

> INSERT  75 : 86  @  75

+ 				} else if (!this.worldObj.isRemote && MinecraftServer.getServer().worldServers[0].getWorldInfo()
+ 						.getGameRulesInstance().getBoolean("clickToRide") && itemstack == null
+ 						&& parEntity instanceof EntityPlayer) {
+ 					EntityPlayer otherPlayer = (EntityPlayer) parEntity;
+ 					while (otherPlayer.riddenByEntity instanceof EntityPlayer) {
+ 						otherPlayer = (EntityPlayer) otherPlayer.riddenByEntity;
+ 					}
+ 					if (otherPlayer.riddenByEntity == null && otherPlayer != this) {
+ 						mountEntity(otherPlayer);
+ 						return true;
+ 					}

> CHANGE  757 : 759  @  757 : 759

~ 	public static EaglercraftUUID getUUID(GameProfile profile) {
~ 		EaglercraftUUID uuid = profile.getId();

> CHANGE  7 : 9  @  7 : 9

~ 	public static EaglercraftUUID getOfflineUUID(String username) {
~ 		return EaglercraftUUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8));

> CHANGE  66 : 67  @  66 : 67

~ 		private static final EntityPlayer.EnumChatVisibility[] ID_LOOKUP = new EntityPlayer.EnumChatVisibility[3];

> CHANGE  21 : 24  @  21 : 23

~ 			EntityPlayer.EnumChatVisibility[] lst = values();
~ 			for (int i = 0; i < lst.length; ++i) {
~ 				ID_LOOKUP[lst[i].chatVisibility] = lst[i];

> EOF
