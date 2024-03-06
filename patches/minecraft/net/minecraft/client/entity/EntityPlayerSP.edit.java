
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 7  @  2

+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModAPI;
+ import net.eaglerforge.api.ModData;
+ import net.lax1dude.eaglercraft.v1_8.sp.lan.LANClientNetworkManager;
+ import net.lax1dude.eaglercraft.v1_8.sp.socket.ClientIntegratedServerNetworkManager;

> DELETE  3  @  3 : 4

> DELETE  51  @  51 : 52

> INSERT  22 : 23  @  22

+ 	private StatFileWriter statWriter;

> CHANGE  1 : 2  @  1 : 2

~ 	public EntityPlayerSP(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatFileWriter statWriter) {

> DELETE  2  @  2 : 3

> INSERT  2 : 3  @  2

+ 		this.statWriter = statWriter;

> INSERT  2 : 141  @  2

+ 	@Override
+ 	public ModData makeModData() {
+ 		ModData data = super.makeModData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.set("lastReportedPosX", lastReportedPosX);
+ 		data.set("lastReportedPosY", lastReportedPosY);
+ 		data.set("lastReportedPosZ", lastReportedPosZ);
+ 		data.set("lastReportedYaw", lastReportedYaw);
+ 		data.set("lastReportedPitch", lastReportedPitch);
+ 		data.set("serverSneakState", serverSneakState);
+ 		data.set("serverSprintState", serverSprintState);
+ 		data.set("positionUpdateTicks", positionUpdateTicks);
+ 		data.set("hasValidHealth", hasValidHealth);
+ 		data.set("clientBrand", clientBrand);
+ 		data.set("sprintToggleTimer", sprintToggleTimer);
+ 		data.set("sprintingTicksLeft", sprintingTicksLeft);
+ 
+ 		data.set("renderArmYaw", renderArmYaw);
+ 		data.set("renderArmPitch", renderArmPitch);
+ 		data.set("prevRenderArmYaw", prevRenderArmYaw);
+ 		data.set("prevRenderArmPitch", prevRenderArmPitch);
+ 		data.set("horseJumpPower", horseJumpPower);
+ 		data.set("horseJumpPowerCounter", horseJumpPowerCounter);
+ 
+ 		data.setCallbackVoidWithDataArg("mountEntity", (BaseData params) -> {
+ 			if (params.getBaseData("entityIn") instanceof Entity) {
+ 				mountEntity((Entity) params.getBaseData("entityIn"));
+ 			}
+ 		});
+ 		data.setCallbackObjectWithDataArg("dropOneItem", (BaseData params) -> {
+ 			EntityItem itemEntity = dropOneItem(params.getBoolean("dropAll"));
+ 			if (itemEntity != null) {
+ 				return itemEntity.makeModData();
+ 			} else {
+ 				return null;
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendChatMessage", (BaseData params) -> {
+ 			sendChatMessage(params.getString("message"));
+ 		});
+ 		data.setCallbackVoid("respawnPlayer", () -> {
+ 			respawnPlayer();
+ 		});
+ 		data.setCallbackVoid("closeScreen", () -> {
+ 			closeScreen();
+ 		});
+ 		data.setCallbackVoid("closeScreenAndDropStack", () -> {
+ 			closeScreenAndDropStack();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPlayerSPHealth", (BaseData params) -> {
+ 			setPlayerSPHealth(params.getFloat("health"));
+ 		});
+ 		data.setCallbackVoid("sendPlayerAbilities", () -> {
+ 			sendPlayerAbilities();
+ 		});
+ 		data.setCallbackBoolean("isUser", () -> {
+ 			/**
+ 			 * + returns true if this is an EntityPlayerSP, or the logged in player.
+ 			 */
+ 			return isUser();
+ 		});
+ 		data.setCallbackVoid("sendHorseInventory", () -> {
+ 			sendHorseInventory();
+ 		});
+ 		data.setCallbackVoid("sendHorseJump", () -> {
+ 			sendHorseJump();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setClientBrand", (BaseData params) -> {
+ 			setClientBrand(params.getString("brand"));
+ 		});
+ 		data.setCallbackString("getClientBrand", () -> {
+ 			return getClientBrand();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("pushOutOfBlocks", (BaseData params) -> {
+ 			return pushOutOfBlocks(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("isOpenBlockSpace", (BaseData bp) -> {
+ 			/**
+ 			 * + Returns true if the block at the given BlockPos and the block above it are
+ 			 * NOT full cubes.
+ 			 */
+ 			return isOpenBlockSpace(BlockPos.fromModData(bp));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setXPStats", (BaseData params) -> {
+ 			setXPStats(params.getFloat("currentXP"), params.getInt("maxXP"), params.getInt("level"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("playSound", (BaseData params) -> {
+ 			playSound(params.getString("name"), params.getFloat("volume"), params.getFloat("pitch"));
+ 		});
+ 		data.setCallbackBoolean("isServerWorld", () -> {
+ 			/**
+ 			 * + Returns whether the entity is in a server world
+ 			 */
+ 			return isServerWorld();
+ 		});
+ 		data.setCallbackBoolean("isRidingHorse", () -> {
+ 			return isRidingHorse();
+ 		});
+ 		data.setCallbackFloat("getHorseJumpPower", () -> {
+ 			return getHorseJumpPower();
+ 		});
+ 		data.setCallbackBoolean("isCurrentViewEntity", () -> {
+ 			return isCurrentViewEntity();
+ 		});
+ 		data.setCallbackBoolean("isSpectator", () -> {
+ 			return isSpectator();
+ 		});
+ 		return data;
+ 	}
+ 
+ 	@Override
+ 	public void loadModData(BaseData data) {
+ 		super.loadModData(data);
+ 		lastReportedPosX = data.getDouble("lastReportedPosX");
+ 		lastReportedPosY = data.getDouble("lastReportedPosY");
+ 		lastReportedPosZ = data.getDouble("lastReportedPosZ");
+ 		lastReportedYaw = data.getFloat("lastReportedYaw");
+ 		lastReportedPitch = data.getFloat("lastReportedPitch");
+ 		serverSneakState = data.getBoolean("serverSneakState");
+ 		serverSprintState = data.getBoolean("serverSprintState");
+ 		positionUpdateTicks = data.getInt("positionUpdateTicks");
+ 		hasValidHealth = data.getBoolean("hasValidHealth");
+ 		clientBrand = data.getString("clientBrand");
+ 		sprintToggleTimer = data.getInt("sprintToggleTimer");
+ 		sprintingTicksLeft = data.getInt("sprintingTicksLeft");
+ 
+ 		renderArmYaw = data.getFloat("renderArmYaw");
+ 		renderArmPitch = data.getFloat("renderArmPitch");
+ 		prevRenderArmYaw = data.getFloat("prevRenderArmYaw");
+ 		prevRenderArmPitch = data.getFloat("prevRenderArmPitch");
+ 		horseJumpPower = data.getFloat("horseJumpPower");
+ 		horseJumpPowerCounter = data.getInt("horseJumpPowerCounter");
+ 	}
+ 

> INSERT  17 : 18  @  17

+ 			mc.modapi.onUpdate();

> INSERT  8 : 9  @  8

+ 				ModAPI.callEvent("postmotionupdate", new ModData());

> INSERT  6 : 11  @  6

+ 		ModData event = new ModData();
+ 		event.set("yaw", this.rotationYaw);
+ 		event.set("pitch", this.rotationPitch);
+ 		event.set("onground", this.onGround);
+ 		ModAPI.callEvent("premotionupdate", event);

> CHANGE  34 : 40  @  34 : 35

~ 			ModData eventData = new ModData();
~ 			eventData.set("preventDefault", false);
~ 			BaseData newEvent = ModAPI.callEvent("motionupdate", eventData);
~ 			if (newEvent.has("preventDefault") && newEvent.getBoolean("preventDefault") == true) {
~ 				// *sneeze*
~ 			} else if (this.ridingEntity == null) {

> CHANGE  48 : 63  @  48 : 49

~ 		if (((sendQueue.getNetworkManager() instanceof ClientIntegratedServerNetworkManager)
~ 				|| (sendQueue.getNetworkManager() instanceof LANClientNetworkManager))
~ 				&& message.startsWith("/eagskull")) {
~ 			this.mc.eagskullCommand.openFileChooser();
~ 		} else {
~ 			ModData event = new ModData();
~ 			event.set("message", message);
~ 			event.set("preventDefault", false);
~ 			BaseData newEvent = mc.modapi.callEvent("sendchatmessage", event);
~ 			if (newEvent.has("preventDefault") && newEvent.getBoolean("preventDefault")) {
~ 				return;
~ 			}
~ 			message = newEvent.has("message") ? newEvent.getString("message") : message;
~ 			this.sendQueue.addToSendQueue(new C01PacketChatMessage(message));
~ 		}

> EOF
