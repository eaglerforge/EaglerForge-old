
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 8

> DELETE  4  @  4 : 6

> INSERT  1 : 25  @  1

+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModAPI;
+ import net.eaglerforge.api.ModData;
+ import net.lax1dude.eaglercraft.v1_8.EagRuntime;
+ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
+ import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
+ 
+ import com.google.common.collect.Maps;
+ 
+ import net.lax1dude.eaglercraft.v1_8.netty.Unpooled;
+ import net.lax1dude.eaglercraft.v1_8.profile.CapePackets;
+ import net.lax1dude.eaglercraft.v1_8.profile.ServerCapeCache;
+ import net.lax1dude.eaglercraft.v1_8.profile.ServerSkinCache;
+ import net.lax1dude.eaglercraft.v1_8.profile.SkinPackets;
+ import net.lax1dude.eaglercraft.v1_8.socket.EaglercraftNetworkManager;
+ import net.lax1dude.eaglercraft.v1_8.sp.lan.LANClientNetworkManager;
+ import net.lax1dude.eaglercraft.v1_8.sp.socket.ClientIntegratedServerNetworkManager;
+ import net.lax1dude.eaglercraft.v1_8.update.UpdateService;
+ import net.lax1dude.eaglercraft.v1_8.voice.VoiceClientController;
+ import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
+ import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
+ import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerFolderResourcePack;
+ import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;

> DELETE  14  @  14 : 16

> DELETE  9  @  9 : 10

> DELETE  5  @  5 : 8

> INSERT  32 : 33  @  32

+ import net.minecraft.entity.player.PlayerCapabilities;

> DELETE  15  @  15 : 16

> DELETE  2  @  2 : 3

> CHANGE  1 : 2  @  1 : 6

~ import net.minecraft.network.play.client.*;

> INSERT  30 : 31  @  30

+ import net.minecraft.network.play.server.S20PacketEntityProperties.Snapshot;

> INSERT  2 : 3  @  2

+ import net.minecraft.network.play.server.S22PacketMultiBlockChange.BlockUpdateData;

> INSERT  22 : 23  @  22

+ import net.minecraft.network.play.server.S38PacketPlayerListItem.AddPlayerData;

> DELETE  18  @  18 : 19

> CHANGE  17 : 18  @  17 : 26

~ import net.minecraft.util.*;

> DELETE  6  @  6 : 8

> CHANGE  1 : 2  @  1 : 2

~ public class NetHandlerPlayClient extends ModData implements INetHandlerPlayClient {

> CHANGE  1 : 2  @  1 : 2

~ 	private final EaglercraftNetworkManager netManager;

> CHANGE  5 : 6  @  5 : 6

~ 	private final Map<EaglercraftUUID, NetworkPlayerInfo> playerInfoMap = Maps.newHashMap();

> CHANGE  2 : 7  @  2 : 3

~ 	private boolean isIntegratedServer = false;
~ 	private final EaglercraftRandom avRandomizer = new EaglercraftRandom();
~ 	private final ServerSkinCache skinCache;
~ 	private final ServerCapeCache capeCache;
~ 	public boolean currentFNAWSkinAllowedState = true;

> CHANGE  1 : 2  @  1 : 2

~ 	public NetHandlerPlayClient(Minecraft mcIn, GuiScreen parGuiScreen, EaglercraftNetworkManager parNetworkManager,

> INSERT  5 : 9  @  5

+ 		this.skinCache = new ServerSkinCache(parNetworkManager, mcIn.getTextureManager());
+ 		this.capeCache = new ServerCapeCache(parNetworkManager, mcIn.getTextureManager());
+ 		this.isIntegratedServer = (parNetworkManager instanceof ClientIntegratedServerNetworkManager)
+ 				|| (parNetworkManager instanceof LANClientNetworkManager);

> INSERT  4 : 6  @  4

+ 		this.skinCache.destroy();
+ 		this.capeCache.destroy();

> INSERT  2 : 158  @  2

+ 	public ServerSkinCache getSkinCache() {
+ 		return this.skinCache;
+ 	}
+ 
+ 	public void loadModData(BaseData data) {
+ 		doneLoadingTerrain = data.getBoolean("doneLoadingTerrain");
+ 		currentServerMaxPlayers = data.getInt("doneLoadingTerrain");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("doneLoadingTerrain", doneLoadingTerrain);
+ 		data.set("currentServerMaxPlayers", currentServerMaxPlayers);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 
+ 		data.setCallbackVoid("sendPacketAnimation", () -> {
+ 			addToSendQueue(new C0APacketAnimation());
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketEntityAction", (BaseData args) -> {
+ 			addToSendQueue(new C0BPacketEntityAction(args.getInt("entityId"),
+ 					C0BPacketEntityAction.Action.valueOf(args.getString("action")), args.getInt("auxData")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketInput", (BaseData args) -> {
+ 			addToSendQueue(new C0CPacketInput(args.getFloat("strafeSpeed"), args.getFloat("forwardSpeed"),
+ 					args.getBoolean("jumping"), args.getBoolean("sneaking")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketCloseWindow", (BaseData args) -> {
+ 			addToSendQueue(new C0DPacketCloseWindow(args.getInt("windowId")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketClickWindow", (BaseData args) -> {
+ 			if (args.getBaseData("clickedItemRef") instanceof ItemStack) {
+ 				addToSendQueue(new C0EPacketClickWindow(args.getInt("windowId"), args.getInt("slotId"),
+ 						args.getInt("usedButton"), args.getInt("mode"), (ItemStack) args.getBaseData("clickedItemRef"),
+ 						args.getShort("actionNumber")));
+ 			} else {
+ 				addToSendQueue(new C0EPacketClickWindow(args.getInt("windowId"), args.getInt("slotId"),
+ 						args.getInt("usedButton"), args.getInt("mode"), null, args.getShort("actionNumber")));
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketConfirmTransaction", (BaseData args) -> {
+ 			addToSendQueue(new C0FPacketConfirmTransaction(args.getInt("windowId"), args.getShort("uid"),
+ 					args.getBoolean("accepted")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketKeepAlive", (BaseData args) -> {
+ 			addToSendQueue(new C00PacketKeepAlive(args.getInt("key")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketChatMessage", (BaseData args) -> {
+ 			addToSendQueue(new C01PacketChatMessage(args.getString("messageIn")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketUseEntity", (BaseData args) -> {
+ 			if (args.has("entityId")) {
+ 				if (args.has("hitVec")) {
+ 					addToSendQueue(new C02PacketUseEntity(args.getInt("entityId"),
+ 							Vec3.fromModData(args.getBaseData("hitVec"))));
+ 				} else {
+ 					addToSendQueue(new C02PacketUseEntity(args.getInt("entityId"),
+ 							C02PacketUseEntity.Action.valueOf(args.getString("action"))));
+ 				}
+ 			} else {
+ 				if (args.has("hitVec")) {
+ 					addToSendQueue(new C02PacketUseEntity((Entity) args.getBaseData("entityRef"),
+ 							Vec3.fromModData(args.getBaseData("hitVec"))));
+ 				} else {
+ 					addToSendQueue(new C02PacketUseEntity((Entity) args.getBaseData("entityRef"),
+ 							C02PacketUseEntity.Action.valueOf(args.getString("action"))));
+ 				}
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayer", (BaseData args) -> {
+ 			addToSendQueue(new C03PacketPlayer(args.getBoolean("isOnGround")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayerPosition", (BaseData args) -> {
+ 			addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(args.getDouble("posX"), args.getDouble("posY"),
+ 					args.getDouble("posZ"), args.getBoolean("isOnGround")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayerLook", (BaseData args) -> {
+ 			addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(args.getFloat("playerYaw"),
+ 					args.getFloat("playerPitch"), args.getBoolean("isOnGround")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayerPosLook", (BaseData args) -> {
+ 			addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(args.getDouble("playerX"),
+ 					args.getDouble("playerY"), args.getDouble("playerZ"), args.getFloat("playerYaw"),
+ 					args.getFloat("playerPitch"), args.getBoolean("isOnGround")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayerDigging", (BaseData args) -> {
+ 			addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.valueOf(args.getString("action")),
+ 					BlockPos.fromModData(args.getBaseData("pos")), EnumFacing.valueOf(args.getString("facing"))));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayerBlockPlacement", (BaseData args) -> {
+ 			if (args.has("positionIn")) {
+ 				addToSendQueue(new C08PacketPlayerBlockPlacement((BlockPos) args.getBaseData("posRef"),
+ 						args.getInt("placedBlockDirectionIn"), (ItemStack) args.getBaseData("stackRef"),
+ 						args.getFloat("facingXIn"), args.getFloat("facingYIn"), args.getFloat("facingZIn")));
+ 			} else {
+ 				addToSendQueue(new C08PacketPlayerBlockPlacement((ItemStack) args.getBaseData("stackRef")));
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketHeldItemChange", (BaseData args) -> {
+ 			addToSendQueue(new C09PacketHeldItemChange(args.getInt("slotId")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketCreativeInventoryAction", (BaseData args) -> {
+ 			addToSendQueue(new C10PacketCreativeInventoryAction(args.getInt("slotId"),
+ 					(ItemStack) args.getBaseData("stackRef")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketEnchantItem", (BaseData args) -> {
+ 			addToSendQueue(new C11PacketEnchantItem(args.getInt("windowId"), args.getInt("button")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketUpdateSign", (BaseData args) -> {
+ 			String[] parLineArr = getStringArr("lines");
+ 			ChatComponentText[] lineArr = new ChatComponentText[parLineArr.length];
+ 			for (int i = 0; i < parLineArr.length; i++) {
+ 				lineArr[i] = new ChatComponentText(parLineArr[i]);
+ 			}
+ 			addToSendQueue(new C12PacketUpdateSign(BlockPos.fromModData(args.getBaseData("pos")), lineArr));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketPlayerAbilities", (BaseData args) -> {
+ 			addToSendQueue(new C13PacketPlayerAbilities((PlayerCapabilities) args.getBaseData("capabilitiesRef")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketTabComplete", (BaseData args) -> {
+ 			if (args.has("target")) {
+ 				addToSendQueue(new C14PacketTabComplete(args.getString("msg"),
+ 						BlockPos.fromModData(args.getBaseData("target"))));
+ 			} else {
+ 				addToSendQueue(new C14PacketTabComplete(args.getString("msg")));
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketClientSettings", (BaseData args) -> {
+ 			addToSendQueue(new C15PacketClientSettings(args.getString("lang"), args.getInt("view"),
+ 					EntityPlayer.EnumChatVisibility.valueOf(args.getString("chatVisibility")),
+ 					args.getBoolean("enableColors"), args.getInt("modelPartFlags")));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketClientStatus", (BaseData args) -> {
+ 			addToSendQueue(
+ 					new C16PacketClientStatus(C16PacketClientStatus.EnumState.valueOf(args.getString("status"))));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketSpectate", (BaseData args) -> {
+ 			addToSendQueue(new C18PacketSpectate(new EaglercraftUUID(args.getString("uuid"))));
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendPacketResourcePackStatus", (BaseData args) -> {
+ 			addToSendQueue(new C19PacketResourcePackStatus(args.getString("hash"),
+ 					C19PacketResourcePackStatus.Action.valueOf(args.getString("status"))));
+ 		});
+ 		return data;
+ 	}
+ 
+ 	public ServerCapeCache getCapeCache() {
+ 		return this.capeCache;
+ 	}
+ 

> DELETE  1  @  1 : 2

> INSERT  16 : 20  @  16

+ 		if (VoiceClientController.isClientSupported()) {
+ 			VoiceClientController.initializeVoiceClient((pkt) -> this.netManager
+ 					.sendPacket(new C17PacketCustomPayload(VoiceClientController.SIGNAL_CHANNEL, pkt)));
+ 		}

> DELETE  3  @  3 : 4

> DELETE  105  @  105 : 106

> DELETE  12  @  12 : 13

> DELETE  21  @  21 : 22

> DELETE  6  @  6 : 7

> DELETE  8  @  8 : 9

> DELETE  8  @  8 : 9

> DELETE  31  @  31 : 32

> DELETE  22  @  22 : 23

> DELETE  8  @  8 : 9

> DELETE  17  @  17 : 18

> DELETE  8  @  8 : 10

> DELETE  7  @  7 : 8

> CHANGE  47 : 50  @  47 : 51

~ 		BlockUpdateData[] dat = packetIn.getChangedBlocks();
~ 		for (int i = 0; i < dat.length; ++i) {
~ 			BlockUpdateData s22packetmultiblockchange$blockupdatedata = dat[i];

> DELETE  7  @  7 : 8

> DELETE  22  @  22 : 23

> CHANGE  8 : 14  @  8 : 9

~ 		VoiceClientController.handleServerDisconnect();
~ 		Minecraft.getMinecraft().getRenderManager()
~ 				.setEnableFNAWSkins(this.gameController.gameSettings.enableFNAWSkins);
~ 		if (this.gameController.theWorld != null) {
~ 			this.gameController.loadWorld((WorldClient) null);
~ 		}

> CHANGE  1 : 3  @  1 : 9

~ 			this.gameController.shutdownIntegratedServer(
~ 					new GuiDisconnected(this.guiScreenServer, "disconnect.lost", ichatcomponent));

> CHANGE  1 : 2  @  1 : 2

~ 			this.gameController.shutdownIntegratedServer(

> DELETE  2  @  2 : 3

> CHANGE  3 : 18  @  3 : 4

~ 		if (ModAPI.clientPacketSendEventsEnabled) {
~ 			ModData eventData = new ModData();
~ 			eventData.set("preventDefault", false);
~ 			setPropertiesFromPacket(eventData, parPacket);
~ 			// ModAPI.logger.info("Send packet ev ::
~ 			// "+getEventNameFromPacket(parPacket));
~ 			BaseData newEvent = ModAPI.callEvent(getEventNameFromPacket(parPacket), eventData);
~ 			if (newEvent.has("preventDefault") && newEvent.getBoolean("preventDefault") == true) {
~ 				return;
~ 			}
~ 			Packet newPacket = setPropertiesToPacket(newEvent, parPacket);
~ 			this.netManager.sendPacket(newPacket);
~ 		} else {
~ 			this.netManager.sendPacket(parPacket);
~ 		}

> INSERT  2 : 436  @  2

+ 	public String getEventNameFromPacket(Packet packet) {
+ 		if (packet instanceof C0APacketAnimation) {
+ 			return "sendpacketanimation";
+ 		} else if (packet instanceof C0BPacketEntityAction) {
+ 			return "sendpacketentityaction";
+ 		} else if (packet instanceof C0CPacketInput) {
+ 			return "sendpacketinput";
+ 		} else if (packet instanceof C0DPacketCloseWindow) {
+ 			return "sendpacketclosewindow";
+ 		} else if (packet instanceof C0EPacketClickWindow) {
+ 			return "sendpacketclickwindow";
+ 		} else if (packet instanceof C0FPacketConfirmTransaction) {
+ 			return "sendpacketconfirmtransaction";
+ 		} else if (packet instanceof C00PacketKeepAlive) {
+ 			return "sendpacketkeepalive";
+ 		} else if (packet instanceof C01PacketChatMessage) {
+ 			return "sendpacketchatmessage";
+ 		} else if (packet instanceof C02PacketUseEntity) {
+ 			return "sendpacketuseentity";
+ 		} else if (packet instanceof C03PacketPlayer.C04PacketPlayerPosition) {
+ 			return "sendpacketplayerposition";
+ 		} else if (packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
+ 			return "sendpacketplayerlook";
+ 		} else if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
+ 			return "sendpacketplayerposlook";
+ 		} else if (packet instanceof C03PacketPlayer) {
+ 			return "sendpacketplayer";
+ 		} else if (packet instanceof C07PacketPlayerDigging) {
+ 			return "sendpacketplayerdigging";
+ 		} else if (packet instanceof C08PacketPlayerBlockPlacement) {
+ 			return "sendpacketplayerblockplacement";
+ 		} else if (packet instanceof C09PacketHeldItemChange) {
+ 			return "sendpackethelditemchange";
+ 		} else if (packet instanceof C10PacketCreativeInventoryAction) {
+ 			return "sendpacketcreativeinventoryaction";
+ 		} else if (packet instanceof C11PacketEnchantItem) {
+ 			return "sendpacketenchantitem";
+ 		} else if (packet instanceof C12PacketUpdateSign) {
+ 			return "sendpacketupdatesign";
+ 		} else if (packet instanceof C13PacketPlayerAbilities) {
+ 			return "sendpacketplayerabilities";
+ 		} else if (packet instanceof C14PacketTabComplete) {
+ 			return "sendpackettabcomplete";
+ 		} else if (packet instanceof C15PacketClientSettings) {
+ 			return "sendpacketclientsettings";
+ 		} else if (packet instanceof C16PacketClientStatus) {
+ 			return "sendpacketclientstatus";
+ 		} else if (packet instanceof C17PacketCustomPayload) {
+ 			return "sendpacketcustompayload";
+ 		} else if (packet instanceof C18PacketSpectate) {
+ 			return "sendpacketspectate";
+ 		} else if (packet instanceof C19PacketResourcePackStatus) {
+ 			return "sendpacketresourcepackstatus";
+ 		}
+ 		return "sendpacketunknown";
+ 	}
+ 
+ 	public void setPropertiesFromPacket(BaseData data, Packet packet) {
+ 		if (packet instanceof C0APacketAnimation) {
+ 			C0APacketAnimation newPacket = (C0APacketAnimation) packet;
+ 			// Nothing to do
+ 			return;
+ 		} else if (packet instanceof C0BPacketEntityAction) {
+ 			C0BPacketEntityAction newPacket = (C0BPacketEntityAction) packet;
+ 			data.set("entityID", newPacket.entityID);
+ 			data.set("action", newPacket.action.name());
+ 			data.set("auxData", newPacket.auxData);
+ 			return;
+ 		} else if (packet instanceof C0CPacketInput) {
+ 			C0CPacketInput newPacket = (C0CPacketInput) packet;
+ 			data.set("strafeSpeed", newPacket.strafeSpeed);
+ 			data.set("forwardSpeed", newPacket.forwardSpeed);
+ 			data.set("jumping", newPacket.jumping);
+ 			data.set("sneaking", newPacket.sneaking);
+ 			return;
+ 		} else if (packet instanceof C0DPacketCloseWindow) {
+ 			C0DPacketCloseWindow newPacket = (C0DPacketCloseWindow) packet;
+ 			data.set("windowId", newPacket.windowId);
+ 			return;
+ 		} else if (packet instanceof C0EPacketClickWindow) {
+ 			C0EPacketClickWindow newPacket = (C0EPacketClickWindow) packet;
+ 			data.set("windowId", newPacket.windowId);
+ 			data.set("slotId", newPacket.slotId);
+ 			data.set("usedButton", newPacket.usedButton);
+ 			data.set("actionNumber", newPacket.actionNumber);
+ 			if (newPacket.clickedItem != null) {
+ 				data.set("clickedItem", newPacket.clickedItem.makeModData());
+ 			}
+ 			data.set("mode", newPacket.mode);
+ 			return;
+ 		} else if (packet instanceof C0FPacketConfirmTransaction) {
+ 			C0FPacketConfirmTransaction newPacket = (C0FPacketConfirmTransaction) packet;
+ 			data.set("windowId", newPacket.windowId);
+ 			data.set("uid", newPacket.uid);
+ 			data.set("accepted", newPacket.accepted);
+ 			return;
+ 		} else if (packet instanceof C00PacketKeepAlive) {
+ 			C00PacketKeepAlive newPacket = (C00PacketKeepAlive) packet;
+ 			data.set("key", newPacket.key);
+ 			return;
+ 		} else if (packet instanceof C01PacketChatMessage) {
+ 			C01PacketChatMessage newPacket = (C01PacketChatMessage) packet;
+ 			data.set("message", newPacket.message);
+ 			return;
+ 		} else if (packet instanceof C02PacketUseEntity) {
+ 			C02PacketUseEntity newPacket = (C02PacketUseEntity) packet;
+ 			data.set("entityId", newPacket.entityId);
+ 			data.set("action", newPacket.action.name());
+ 			if (newPacket.hitVec != null) {
+ 				data.set("hitVec", newPacket.hitVec.makeModData());
+ 			}
+ 			return;
+ 		} else if (packet instanceof C03PacketPlayer.C04PacketPlayerPosition) {
+ 			C03PacketPlayer.C04PacketPlayerPosition newPacket = (C03PacketPlayer.C04PacketPlayerPosition) packet;
+ 			data.set("x", newPacket.x);
+ 			data.set("y", newPacket.y);
+ 			data.set("z", newPacket.z);
+ 			data.set("yaw", newPacket.yaw);
+ 			data.set("pitch", newPacket.pitch);
+ 			data.set("onGround", newPacket.onGround);
+ 			data.set("moving", newPacket.moving);
+ 			data.set("rotating", newPacket.rotating);
+ 			return;
+ 		} else if (packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
+ 			C03PacketPlayer.C05PacketPlayerLook newPacket = (C03PacketPlayer.C05PacketPlayerLook) packet;
+ 			data.set("x", newPacket.x);
+ 			data.set("y", newPacket.y);
+ 			data.set("z", newPacket.z);
+ 			data.set("yaw", newPacket.yaw);
+ 			data.set("pitch", newPacket.pitch);
+ 			data.set("onGround", newPacket.onGround);
+ 			data.set("moving", newPacket.moving);
+ 			data.set("rotating", newPacket.rotating);
+ 			return;
+ 		} else if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
+ 			C03PacketPlayer.C06PacketPlayerPosLook newPacket = (C03PacketPlayer.C06PacketPlayerPosLook) packet;
+ 			data.set("x", newPacket.x);
+ 			data.set("y", newPacket.y);
+ 			data.set("z", newPacket.z);
+ 			data.set("yaw", newPacket.yaw);
+ 			data.set("pitch", newPacket.pitch);
+ 			data.set("onGround", newPacket.onGround);
+ 			data.set("moving", newPacket.moving);
+ 			data.set("rotating", newPacket.rotating);
+ 			return;
+ 		} else if (packet instanceof C03PacketPlayer) {
+ 			C03PacketPlayer newPacket = (C03PacketPlayer) packet;
+ 			data.set("x", newPacket.x);
+ 			data.set("y", newPacket.y);
+ 			data.set("z", newPacket.z);
+ 			data.set("yaw", newPacket.yaw);
+ 			data.set("pitch", newPacket.pitch);
+ 			data.set("onGround", newPacket.onGround);
+ 			data.set("moving", newPacket.moving);
+ 			data.set("rotating", newPacket.rotating);
+ 			return;
+ 		} else if (packet instanceof C07PacketPlayerDigging) {
+ 			C07PacketPlayerDigging newPacket = (C07PacketPlayerDigging) packet;
+ 			data.set("position", newPacket.position.makeModData());
+ 			data.set("facing", newPacket.facing.name());
+ 			data.set("status", newPacket.status.name());
+ 			return;
+ 		} else if (packet instanceof C08PacketPlayerBlockPlacement) {
+ 			C08PacketPlayerBlockPlacement newPacket = (C08PacketPlayerBlockPlacement) packet;
+ 			data.set("placedBlockDirection", newPacket.placedBlockDirection);
+ 			data.set("facingX", newPacket.facingX);
+ 			data.set("facingY", newPacket.facingY);
+ 			data.set("facingZ", newPacket.facingZ);
+ 			data.set("position", newPacket.position.makeModData());
+ 			if (newPacket.stack != null) {
+ 				data.set("stack", newPacket.stack.makeModData());
+ 			}
+ 			return;
+ 		} else if (packet instanceof C09PacketHeldItemChange) {
+ 			C09PacketHeldItemChange newPacket = (C09PacketHeldItemChange) packet;
+ 			data.set("slotId", newPacket.slotId);
+ 			return;
+ 		} else if (packet instanceof C10PacketCreativeInventoryAction) {
+ 			C10PacketCreativeInventoryAction newPacket = (C10PacketCreativeInventoryAction) packet;
+ 			data.set("slotId", newPacket.slotId);
+ 			if (newPacket.stack != null) {
+ 				data.set("stack", newPacket.stack.makeModData());
+ 			}
+ 			return;
+ 		} else if (packet instanceof C11PacketEnchantItem) {
+ 			C11PacketEnchantItem newPacket = (C11PacketEnchantItem) packet;
+ 			data.set("windowId", newPacket.windowId);
+ 			data.set("button", newPacket.button);
+ 			return;
+ 		} else if (packet instanceof C12PacketUpdateSign) {
+ 			C12PacketUpdateSign newPacket = (C12PacketUpdateSign) packet;
+ 			if (newPacket.pos != null) {
+ 				data.set("pos", newPacket.pos.makeModData());
+ 			}
+ 			String[] stringArr = new String[newPacket.lines.length];
+ 			for (int i = 0; i < stringArr.length; i++) {
+ 				if (newPacket.lines[i] != null) {
+ 					stringArr[i] = newPacket.lines[i].getFormattedText();
+ 				}
+ 			}
+ 			data.set("lines", stringArr);
+ 			return;
+ 		} else if (packet instanceof C13PacketPlayerAbilities) {
+ 			C13PacketPlayerAbilities newPacket = (C13PacketPlayerAbilities) packet;
+ 			data.set("invulnerable", newPacket.invulnerable);
+ 			data.set("flying", newPacket.flying);
+ 			data.set("allowFlying", newPacket.allowFlying);
+ 			data.set("creativeMode", newPacket.creativeMode);
+ 			data.set("flySpeed", newPacket.flySpeed);
+ 			data.set("walkSpeed", newPacket.walkSpeed);
+ 			return;
+ 		} else if (packet instanceof C14PacketTabComplete) {
+ 			C14PacketTabComplete newPacket = (C14PacketTabComplete) packet;
+ 			data.set("message", newPacket.message);
+ 			if (newPacket.targetBlock != null) {
+ 				data.set("targetBlock", newPacket.targetBlock.makeModData());
+ 			}
+ 			return;
+ 		} else if (packet instanceof C15PacketClientSettings) {
+ 			C15PacketClientSettings newPacket = (C15PacketClientSettings) packet;
+ 			data.set("lang", newPacket.lang);
+ 			data.set("view", newPacket.view);
+ 			data.set("chatVisibility", newPacket.chatVisibility.name());
+ 			data.set("enableColors", newPacket.enableColors);
+ 			data.set("modelPartFlags", newPacket.modelPartFlags);
+ 			return;
+ 		} else if (packet instanceof C16PacketClientStatus) {
+ 			C16PacketClientStatus newPacket = (C16PacketClientStatus) packet;
+ 			data.set("status", newPacket.status.name());
+ 			return;
+ 		} else if (packet instanceof C17PacketCustomPayload) {
+ 			C17PacketCustomPayload newPacket = (C17PacketCustomPayload) packet;
+ 			data.set("channel", newPacket.channel);
+ 			return;
+ 		} else if (packet instanceof C18PacketSpectate) {
+ 			C18PacketSpectate newPacket = (C18PacketSpectate) packet;
+ 			data.set("id", newPacket.id.toString());
+ 			return;
+ 		} else if (packet instanceof C19PacketResourcePackStatus) {
+ 			C19PacketResourcePackStatus newPacket = (C19PacketResourcePackStatus) packet;
+ 			data.set("hash", newPacket.hash);
+ 			data.set("status", newPacket.status.name());
+ 			return;
+ 		}
+ 	}
+ 
+ 	public Packet setPropertiesToPacket(BaseData data, Packet packet) {
+ 		if (packet instanceof C0APacketAnimation) {
+ 			C0APacketAnimation newPacket = (C0APacketAnimation) packet;
+ 			// Nothing to do
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C0BPacketEntityAction) {
+ 			C0BPacketEntityAction newPacket = (C0BPacketEntityAction) packet;
+ 			newPacket.entityID = data.getInt("entityID");
+ 			newPacket.auxData = data.getInt("auxData");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C0CPacketInput) {
+ 			C0CPacketInput newPacket = (C0CPacketInput) packet;
+ 			newPacket.strafeSpeed = data.getFloat("strafeSpeed");
+ 			newPacket.forwardSpeed = data.getFloat("forwardSpeed");
+ 			newPacket.jumping = data.getBoolean("jumping");
+ 			newPacket.sneaking = data.getBoolean("sneaking");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C0DPacketCloseWindow) {
+ 			C0DPacketCloseWindow newPacket = (C0DPacketCloseWindow) packet;
+ 			newPacket.windowId = data.getInt("windowId");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C0EPacketClickWindow) {
+ 			C0EPacketClickWindow newPacket = (C0EPacketClickWindow) packet;
+ 			newPacket.windowId = data.getInt("windowId");
+ 			newPacket.slotId = data.getInt("slotId");
+ 			newPacket.usedButton = data.getInt("usedButton");
+ 			newPacket.actionNumber = data.getShort("actionNumber");
+ 			newPacket.mode = data.getInt("mode");
+ 			if (data.has("clickedItem")) {
+ 				newPacket.clickedItem = (ItemStack) data.getRef("clickedItem");
+ 			}
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C0FPacketConfirmTransaction) {
+ 			C0FPacketConfirmTransaction newPacket = (C0FPacketConfirmTransaction) packet;
+ 			newPacket.windowId = data.getInt("windowId");
+ 			newPacket.uid = data.getShort("uid");
+ 			newPacket.accepted = data.getBoolean("accepted");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C00PacketKeepAlive) {
+ 			C00PacketKeepAlive newPacket = (C00PacketKeepAlive) packet;
+ 			newPacket.key = data.getInt("key");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C01PacketChatMessage) {
+ 			C01PacketChatMessage newPacket = (C01PacketChatMessage) packet;
+ 			newPacket.message = data.getString("message");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C02PacketUseEntity) {
+ 			C02PacketUseEntity newPacket = (C02PacketUseEntity) packet;
+ 			newPacket.entityId = data.getInt("entityId");
+ 			newPacket.action = C02PacketUseEntity.Action.valueOf(data.getString("action"));
+ 			if (newPacket.hitVec != null) {
+ 				newPacket.hitVec.loadModData(data.getBaseData("hitVec"));
+ 			}
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C03PacketPlayer.C04PacketPlayerPosition) {
+ 			C03PacketPlayer.C04PacketPlayerPosition newPacket = (C03PacketPlayer.C04PacketPlayerPosition) packet;
+ 			newPacket.x = data.getDouble("x");
+ 			newPacket.y = data.getDouble("y");
+ 			newPacket.z = data.getDouble("z");
+ 			newPacket.yaw = data.getFloat("yaw");
+ 			newPacket.pitch = data.getFloat("pitch");
+ 			newPacket.onGround = data.getBoolean("onGround");
+ 			newPacket.moving = data.getBoolean("moving");
+ 			newPacket.rotating = data.getBoolean("rotating");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
+ 			C03PacketPlayer.C05PacketPlayerLook newPacket = (C03PacketPlayer.C05PacketPlayerLook) packet;
+ 			newPacket.x = data.getDouble("x");
+ 			newPacket.y = data.getDouble("y");
+ 			newPacket.z = data.getDouble("z");
+ 			newPacket.yaw = data.getFloat("yaw");
+ 			newPacket.pitch = data.getFloat("pitch");
+ 			newPacket.onGround = data.getBoolean("onGround");
+ 			newPacket.moving = data.getBoolean("moving");
+ 			newPacket.rotating = data.getBoolean("rotating");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
+ 			C03PacketPlayer.C06PacketPlayerPosLook newPacket = (C03PacketPlayer.C06PacketPlayerPosLook) packet;
+ 			newPacket.x = data.getDouble("x");
+ 			newPacket.y = data.getDouble("y");
+ 			newPacket.z = data.getDouble("z");
+ 			newPacket.yaw = data.getFloat("yaw");
+ 			newPacket.pitch = data.getFloat("pitch");
+ 			newPacket.onGround = data.getBoolean("onGround");
+ 			newPacket.moving = data.getBoolean("moving");
+ 			newPacket.rotating = data.getBoolean("rotating");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C03PacketPlayer) {
+ 			C03PacketPlayer newPacket = (C03PacketPlayer) packet;
+ 			newPacket.x = data.getDouble("x");
+ 			newPacket.y = data.getDouble("y");
+ 			newPacket.z = data.getDouble("z");
+ 			newPacket.yaw = data.getFloat("yaw");
+ 			newPacket.pitch = data.getFloat("pitch");
+ 			newPacket.onGround = data.getBoolean("onGround");
+ 			newPacket.moving = data.getBoolean("moving");
+ 			newPacket.rotating = data.getBoolean("rotating");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C07PacketPlayerDigging) {
+ 			C07PacketPlayerDigging newPacket = (C07PacketPlayerDigging) packet;
+ 			newPacket.position = BlockPos.fromModData(data.getBaseData("position"));
+ 			newPacket.facing = EnumFacing.valueOf(data.getString("facing"));
+ 			newPacket.status = C07PacketPlayerDigging.Action.valueOf(data.getString("status"));
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C08PacketPlayerBlockPlacement) {
+ 			C08PacketPlayerBlockPlacement newPacket = (C08PacketPlayerBlockPlacement) packet;
+ 			newPacket.placedBlockDirection = data.getInt("placedBlockDirection");
+ 			newPacket.facingX = data.getFloat("facingX");
+ 			newPacket.facingY = data.getFloat("facingY");
+ 			newPacket.facingZ = data.getFloat("facingZ");
+ 			newPacket.position = BlockPos.fromModData(data.getBaseData("position"));
+ 			if (data.has("stack")) {
+ 				newPacket.stack = (ItemStack) data.getRef("stack");
+ 			}
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C09PacketHeldItemChange) {
+ 			C09PacketHeldItemChange newPacket = (C09PacketHeldItemChange) packet;
+ 			newPacket.slotId = data.getInt("slotId");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C10PacketCreativeInventoryAction) {
+ 			C10PacketCreativeInventoryAction newPacket = (C10PacketCreativeInventoryAction) packet;
+ 			if (data.has("stack")) {
+ 				newPacket.stack = (ItemStack) data.getRef("stack");
+ 			}
+ 			newPacket.slotId = data.getInt("slotId");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C11PacketEnchantItem) {
+ 			C11PacketEnchantItem newPacket = (C11PacketEnchantItem) packet;
+ 			newPacket.windowId = data.getInt("windowId");
+ 			newPacket.button = data.getInt("button");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C12PacketUpdateSign) {
+ 			C12PacketUpdateSign newPacket = (C12PacketUpdateSign) packet;
+ 			if (data.has("pos")) {
+ 				newPacket.pos = (BlockPos) data.getRef("pos");
+ 			}
+ 			String[] stringArr = data.getStringArr("lines");
+ 			for (int i = 0; i < stringArr.length; i++) {
+ 				if (stringArr[i] != null && (stringArr[i].length() != newPacket.lines[i].getFormattedText().length())) {
+ 					newPacket.lines[i] = new ChatComponentText(stringArr[i]);
+ 				}
+ 			}
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C13PacketPlayerAbilities) {
+ 			C13PacketPlayerAbilities newPacket = (C13PacketPlayerAbilities) packet;
+ 			newPacket.invulnerable = data.getBoolean("invulnerable");
+ 			newPacket.flying = data.getBoolean("flying");
+ 			newPacket.allowFlying = data.getBoolean("allowFlying");
+ 			newPacket.creativeMode = data.getBoolean("creativeMode");
+ 			newPacket.flySpeed = data.getFloat("flySpeed");
+ 			newPacket.walkSpeed = data.getFloat("walkSpeed");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C14PacketTabComplete) {
+ 			C14PacketTabComplete newPacket = (C14PacketTabComplete) packet;
+ 			if (data.has("targetBlock")) {
+ 				newPacket.targetBlock = (BlockPos) data.getRef("targetBlock");
+ 			}
+ 			newPacket.message = data.getString("message");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C15PacketClientSettings) {
+ 			C15PacketClientSettings newPacket = (C15PacketClientSettings) packet;
+ 			newPacket.lang = data.getString("lang");
+ 			newPacket.view = data.getInt("view");
+ 			newPacket.chatVisibility = EntityPlayer.EnumChatVisibility.valueOf(data.getString("chatVisibility"));
+ 			newPacket.enableColors = data.getBoolean("enableColors");
+ 			newPacket.modelPartFlags = data.getInt("modelPartFlags");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C16PacketClientStatus) {
+ 			C16PacketClientStatus newPacket = (C16PacketClientStatus) packet;
+ 			newPacket.status = C16PacketClientStatus.EnumState.valueOf(data.getString("status"));
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C17PacketCustomPayload) {
+ 			C17PacketCustomPayload newPacket = (C17PacketCustomPayload) packet;
+ 			newPacket.channel = data.getString("channel");
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C18PacketSpectate) {
+ 			C18PacketSpectate newPacket = (C18PacketSpectate) packet;
+ 			newPacket.id = new EaglercraftUUID(data.getString("id"));
+ 			return (Packet) newPacket;
+ 		} else if (packet instanceof C19PacketResourcePackStatus) {
+ 			C19PacketResourcePackStatus newPacket = (C19PacketResourcePackStatus) packet;
+ 			newPacket.hash = data.getString("hash");
+ 			newPacket.status = C19PacketResourcePackStatus.Action.valueOf(data.getString("status"));
+ 			return (Packet) newPacket;
+ 		}
+ 		return packet;
+ 	}
+ 

> DELETE  1  @  1 : 2

> CHANGE  23 : 37  @  23 : 24

~ 		ModData eventData = new ModData();
~ 		eventData.set("preventDefault", false);
~ 		eventData.set("type", packetIn.type);
~ 		eventData.set("chat", packetIn.chatComponent.getFormattedText());
~ 		BaseData newEvent = ModAPI.callEvent("packetchat", eventData);
~ 		if (newEvent.has("preventDefault") && newEvent.getBoolean("preventDefault") == true) {
~ 			return;
~ 		}
~ 		packetIn.type = newEvent.has("type") ? newEvent.getByte("type") : packetIn.type;
~ 		if (newEvent.has("chat")
~ 				&& (newEvent.getString("chat").length() != packetIn.chatComponent.getFormattedText().length())) {
~ 			packetIn.chatComponent = new ChatComponentText(newEvent.getString("chat"));
~ 		}
~ 

> DELETE  9  @  9 : 10

> DELETE  20  @  20 : 21

> DELETE  4  @  4 : 5

> DELETE  35  @  35 : 36

> DELETE  5  @  5 : 6

> DELETE  5  @  5 : 6

> DELETE  39  @  39 : 40

> DELETE  12  @  12 : 13

> DELETE  6  @  6 : 7

> DELETE  5  @  5 : 6

> DELETE  17  @  17 : 18

> DELETE  9  @  9 : 10

> DELETE  27  @  27 : 28

> DELETE  27  @  27 : 28

> DELETE  16  @  16 : 17

> DELETE  10  @  10 : 11

> DELETE  11  @  11 : 12

> DELETE  22  @  22 : 23

> DELETE  16  @  16 : 17

> DELETE  8  @  8 : 9

> DELETE  8  @  8 : 9

> DELETE  4  @  4 : 5

> DELETE  5  @  5 : 6

> DELETE  5  @  5 : 7

> DELETE  18  @  18 : 19

> CHANGE  21 : 24  @  21 : 40

~ 
~ 			// minecraft demo screen
~ 

> DELETE  18  @  18 : 19

> DELETE  6  @  6 : 7

> DELETE  11  @  11 : 12

> DELETE  9  @  9 : 10

> DELETE  25  @  25 : 26

> DELETE  10  @  10 : 26

> INSERT  1 : 3  @  1

+ 		// used by twitch stream
+ 

> DELETE  3  @  3 : 4

> CHANGE  5 : 6  @  5 : 6

~ 

> DELETE  8  @  8 : 9

> DELETE  4  @  4 : 5

> DELETE  36  @  36 : 37

> CHANGE  8 : 11  @  8 : 11

~ 		List<AddPlayerData> lst = packetIn.func_179767_a();
~ 		for (int i = 0, l = lst.size(); i < l; ++i) {
~ 			S38PacketPlayerListItem.AddPlayerData s38packetplayerlistitem$addplayerdata = lst.get(i);

> CHANGE  1 : 5  @  1 : 2

~ 				EaglercraftUUID uuid = s38packetplayerlistitem$addplayerdata.getProfile().getId();
~ 				this.playerInfoMap.remove(uuid);
~ 				this.skinCache.evictSkin(uuid);
~ 				this.capeCache.evictCape(uuid);

> DELETE  34  @  34 : 35

> DELETE  10  @  10 : 11

> CHANGE  9 : 28  @  9 : 10

~ 		ModData eventData = new ModData();
~ 		eventData.set("preventDefault", false);
~ 		eventData.set("soundName", packetIn.soundName);
~ 		eventData.set("posX", packetIn.posX);
~ 		eventData.set("posY", packetIn.posY);
~ 		eventData.set("posZ", packetIn.posZ);
~ 		eventData.set("soundVolume", packetIn.soundVolume);
~ 		eventData.set("soundPitch", packetIn.soundPitch);
~ 		BaseData newEvent = ModAPI.callEvent("packetsoundeffect", eventData);
~ 		if (newEvent.has("preventDefault") && newEvent.getBoolean("preventDefault") == true) {
~ 			return;
~ 		}
~ 		packetIn.soundName = newEvent.has("soundName") ? newEvent.getString("soundName") : packetIn.soundName;
~ 		packetIn.posX = newEvent.has("posX") ? newEvent.getInt("posX") : packetIn.posX;
~ 		packetIn.posY = newEvent.has("posY") ? newEvent.getInt("posY") : packetIn.posY;
~ 		packetIn.posZ = newEvent.has("posZ") ? newEvent.getInt("posZ") : packetIn.posZ;
~ 		packetIn.soundVolume = newEvent.has("soundVolume") ? newEvent.getFloat("soundVolume") : packetIn.soundVolume;
~ 		packetIn.soundPitch = newEvent.has("soundPitch") ? newEvent.getInt("soundPitch") : packetIn.soundPitch;
~ 

> CHANGE  7 : 30  @  7 : 31

~ 		if (!EaglerFolderResourcePack.isSupported() || s.startsWith("level://")) {
~ 			this.netManager
~ 					.sendPacket(new C19PacketResourcePackStatus(s1, C19PacketResourcePackStatus.Action.DECLINED));
~ 			return;
~ 		}
~ 		if (this.gameController.getCurrentServerData() != null && this.gameController.getCurrentServerData()
~ 				.getResourceMode() == ServerData.ServerResourceMode.ENABLED) {
~ 			NetHandlerPlayClient.this.netManager
~ 					.sendPacket(new C19PacketResourcePackStatus(s1, C19PacketResourcePackStatus.Action.ACCEPTED));
~ 			NetHandlerPlayClient.this.gameController.getResourcePackRepository().downloadResourcePack(s, s1,
~ 					success -> {
~ 						if (success) {
~ 							NetHandlerPlayClient.this.netManager.sendPacket(new C19PacketResourcePackStatus(s1,
~ 									C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
~ 						} else {
~ 							NetHandlerPlayClient.this.netManager.sendPacket(new C19PacketResourcePackStatus(s1,
~ 									C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
~ 						}
~ 					});
~ 		} else if (this.gameController.getCurrentServerData() != null && this.gameController.getCurrentServerData()
~ 				.getResourceMode() != ServerData.ServerResourceMode.PROMPT) {
~ 			this.netManager
~ 					.sendPacket(new C19PacketResourcePackStatus(s1, C19PacketResourcePackStatus.Action.DECLINED));

> CHANGE  1 : 9  @  1 : 11

~ 			NetHandlerPlayClient.this.gameController.displayGuiScreen(new GuiYesNo(new GuiYesNoCallback() {
~ 				public void confirmClicked(boolean flag, int var2) {
~ 					NetHandlerPlayClient.this.gameController = Minecraft.getMinecraft();
~ 					if (flag) {
~ 						if (NetHandlerPlayClient.this.gameController.getCurrentServerData() != null) {
~ 							NetHandlerPlayClient.this.gameController.getCurrentServerData()
~ 									.setResourceMode(ServerData.ServerResourceMode.ENABLED);
~ 						}

> CHANGE  1 : 11  @  1 : 20

~ 						NetHandlerPlayClient.this.netManager.sendPacket(
~ 								new C19PacketResourcePackStatus(s1, C19PacketResourcePackStatus.Action.ACCEPTED));
~ 						NetHandlerPlayClient.this.gameController.getResourcePackRepository().downloadResourcePack(s, s1,
~ 								success -> {
~ 									if (success) {
~ 										NetHandlerPlayClient.this.netManager.sendPacket(new C19PacketResourcePackStatus(
~ 												s1, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
~ 									} else {
~ 										NetHandlerPlayClient.this.netManager.sendPacket(new C19PacketResourcePackStatus(
~ 												s1, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));

> INSERT  1 : 7  @  1

+ 								});
+ 					} else {
+ 						if (NetHandlerPlayClient.this.gameController.getCurrentServerData() != null) {
+ 							NetHandlerPlayClient.this.gameController.getCurrentServerData()
+ 									.setResourceMode(ServerData.ServerResourceMode.DISABLED);
+ 						}

> CHANGE  1 : 3  @  1 : 34

~ 						NetHandlerPlayClient.this.netManager.sendPacket(
~ 								new C19PacketResourcePackStatus(s1, C19PacketResourcePackStatus.Action.DECLINED));

> DELETE  1  @  1 : 3

> INSERT  1 : 6  @  1

+ 					ServerList.func_147414_b(NetHandlerPlayClient.this.gameController.getCurrentServerData());
+ 					NetHandlerPlayClient.this.gameController.displayGuiScreen((GuiScreen) null);
+ 				}
+ 			}, I18n.format("multiplayer.texturePrompt.line1", new Object[0]),
+ 					I18n.format("multiplayer.texturePrompt.line2", new Object[0]), 0));

> DELETE  4  @  4 : 5

> DELETE  8  @  8 : 9

> DELETE  2  @  2 : 3

> DELETE  11  @  11 : 13

> INSERT  9 : 43  @  9

+ 		} else if ("EAG|Skins-1.8".equals(packetIn.getChannelName())) {
+ 			try {
+ 				SkinPackets.readPluginMessage(packetIn.getBufferData(), skinCache);
+ 			} catch (IOException e) {
+ 				logger.error("Couldn't read EAG|Skins-1.8 packet!");
+ 				logger.error(e);
+ 			}
+ 		} else if ("EAG|Capes-1.8".equals(packetIn.getChannelName())) {
+ 			try {
+ 				CapePackets.readPluginMessage(packetIn.getBufferData(), capeCache);
+ 			} catch (IOException e) {
+ 				logger.error("Couldn't read EAG|Capes-1.8 packet!");
+ 				logger.error(e);
+ 			}
+ 		} else if ("EAG|UpdateCert-1.8".equals(packetIn.getChannelName())) {
+ 			if (EagRuntime.getConfiguration().allowUpdateSvc()) {
+ 				try {
+ 					PacketBuffer pb = packetIn.getBufferData();
+ 					byte[] c = new byte[pb.readableBytes()];
+ 					pb.readBytes(c);
+ 					UpdateService.addCertificateToSet(c);
+ 				} catch (Throwable e) {
+ 					logger.error("Couldn't process EAG|UpdateCert-1.8 packet!");
+ 					logger.error(e);
+ 				}
+ 			}
+ 		} else if (VoiceClientController.SIGNAL_CHANNEL.equals(packetIn.getChannelName())) {
+ 			if (VoiceClientController.isClientSupported()) {
+ 				VoiceClientController.handleVoiceSignalPacket(packetIn.getBufferData());
+ 			}
+ 		} else if ("EAG|FNAWSEn-1.8".equals(packetIn.getChannelName())) {
+ 			this.currentFNAWSkinAllowedState = packetIn.getBufferData().readBoolean();
+ 			Minecraft.getMinecraft().getRenderManager().setEnableFNAWSkins(
+ 					this.currentFNAWSkinAllowedState && Minecraft.getMinecraft().gameSettings.enableFNAWSkins);

> DELETE  1  @  1 : 2

> DELETE  3  @  3 : 4

> DELETE  19  @  19 : 20

> DELETE  16  @  16 : 17

> DELETE  11  @  11 : 12

> DELETE  39  @  39 : 40

> DELETE  35  @  35 : 36

> CHANGE  8 : 11  @  8 : 9

~ 				List<Snapshot> lst = packetIn.func_149441_d();
~ 				for (int i = 0, l = lst.size(); i < l; ++i) {
~ 					S20PacketEntityProperties.Snapshot s20packetentityproperties$snapshot = lst.get(i);

> CHANGE  20 : 21  @  20 : 21

~ 	public EaglercraftNetworkManager getNetworkManager() {

> CHANGE  7 : 8  @  7 : 8

~ 	public NetworkPlayerInfo getPlayerInfo(EaglercraftUUID parUUID) {

> INSERT  16 : 20  @  16

+ 
+ 	public boolean isClientInEaglerSingleplayerOrLAN() {
+ 		return isIntegratedServer;
+ 	}

> EOF
