
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  3 : 11  @  3 : 5

~ 
~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
~ import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
~ import net.lax1dude.eaglercraft.v1_8.HString;
~ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DynamicLightManager;
~ 

> INSERT  1 : 2  @  1

+ 

> INSERT  8 : 9  @  8

+ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

> DELETE  6  @  6 : 9

> CHANGE  8 : 9  @  8 : 12

~ import net.minecraft.nbt.*;

> CHANGE  17 : 18  @  17 : 18

~ public abstract class Entity extends ModData implements ICommandSender {

> CHANGE  44 : 45  @  44 : 45

~ 	protected EaglercraftRandom rand;

> CHANGE  27 : 28  @  27 : 28

~ 	protected EaglercraftUUID entityUniqueID;

> CHANGE  21 : 22  @  21 : 22

~ 		this.rand = new EaglercraftRandom();

> CHANGE  297 : 298  @  297 : 298

~ 			for (AxisAlignedBB axisalignedbb1 : (List<AxisAlignedBB>) list1) {

> CHANGE  6 : 7  @  6 : 7

~ 			for (AxisAlignedBB axisalignedbb2 : (List<AxisAlignedBB>) list1) {

> CHANGE  5 : 6  @  5 : 6

~ 			for (AxisAlignedBB axisalignedbb13 : (List<AxisAlignedBB>) list1) {

> CHANGE  17 : 18  @  17 : 18

~ 				for (AxisAlignedBB axisalignedbb6 : (List<AxisAlignedBB>) list) {

> CHANGE  6 : 7  @  6 : 7

~ 				for (AxisAlignedBB axisalignedbb7 : (List<AxisAlignedBB>) list) {

> CHANGE  6 : 7  @  6 : 7

~ 				for (AxisAlignedBB axisalignedbb8 : (List<AxisAlignedBB>) list) {

> CHANGE  7 : 8  @  7 : 8

~ 				for (AxisAlignedBB axisalignedbb9 : (List<AxisAlignedBB>) list) {

> CHANGE  6 : 7  @  6 : 7

~ 				for (AxisAlignedBB axisalignedbb10 : (List<AxisAlignedBB>) list) {

> CHANGE  6 : 7  @  6 : 7

~ 				for (AxisAlignedBB axisalignedbb11 : (List<AxisAlignedBB>) list) {

> CHANGE  18 : 19  @  18 : 19

~ 				for (AxisAlignedBB axisalignedbb12 : (List<AxisAlignedBB>) list) {

> INSERT  233 : 603  @  233

+ 	public void loadModData(BaseData data) {
+ 		posX = data.getDouble("x");
+ 		posY = data.getDouble("y");
+ 		posZ = data.getDouble("z");
+ 		motionX = data.getDouble("motionX");
+ 		motionY = data.getDouble("motionY");
+ 		motionZ = data.getDouble("motionZ");
+ 		rotationYaw = data.getFloat("yaw");
+ 		rotationPitch = data.getFloat("pitch");
+ 		isInWeb = data.getBoolean("isInWeb");
+ 		onGround = data.getBoolean("onGround");
+ 		noClip = data.getBoolean("noClip");
+ 		stepHeight = data.getFloat("stepHeight");
+ 		isCollided = data.getBoolean("isCollided");
+ 		isCollidedHorizontally = data.getBoolean("isCollidedHorizontally");
+ 		isCollidedVertically = data.getBoolean("isCollidedVertically");
+ 		inPortal = data.getBoolean("inPortal");
+ 		inWater = data.getBoolean("inWater");
+ 		isAirBorne = data.getBoolean("isAirBorne");
+ 		invulnerable = data.getBoolean("invulnerable");
+ 		isImmuneToFire = data.getBoolean("isImmuneToFire");
+ 		isOutsideBorder = data.getBoolean("isOutsideBorder");
+ 		entityCollisionReduction = data.getFloat("entityCollisionReduction");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("x", posX);
+ 		data.set("y", posY);
+ 		data.set("z", posZ);
+ 		data.set("chunkCoordX", chunkCoordX);
+ 		data.set("chunkCoordY", chunkCoordY);
+ 		data.set("chunkCoordZ", chunkCoordZ);
+ 		data.set("motionX", motionX);
+ 		data.set("motionY", motionY);
+ 		data.set("motionZ", motionZ);
+ 		data.set("yaw", rotationYaw);
+ 		data.set("pitch", rotationPitch);
+ 		data.set("isInWeb", isInWeb);
+ 		data.set("isCollided", isCollided);
+ 		data.set("isCollidedVertically", isCollidedVertically);
+ 		data.set("isCollidedHorizontally", isCollidedHorizontally);
+ 		data.set("onGround", onGround);
+ 		data.set("dimension", dimension);
+ 		data.set("id", entityId);
+ 		data.set("fallDistance", fallDistance);
+ 		data.set("noClip", noClip);
+ 		data.set("stepHeight", stepHeight);
+ 		data.set("isDead", isDead);
+ 		data.set("inPortal", inPortal);
+ 		data.set("inWater", inWater);
+ 		data.set("isAirBorne", isAirBorne);
+ 		data.set("ticksExisted", ticksExisted);
+ 		data.set("invulnerable", invulnerable);
+ 		data.set("isImmuneToFire", isImmuneToFire);
+ 		data.set("isOutsideBorder", isOutsideBorder);
+ 		data.set("entityCollisionReduction", entityCollisionReduction);
+ 		data.set("ticksExisted", ticksExisted);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 
+ 		data.setCallbackBoolean("isBurning", () -> {
+ 			/**
+ 			 * + Returns true if the entity is on fire. Used by render to add the fire
+ 			 * effect on rendering.
+ 			 */
+ 			return isBurning();
+ 		});
+ 		data.setCallbackBoolean("isPushedByWater", () -> {
+ 			return isPushedByWater();
+ 		});
+ 		data.setCallbackBoolean("isEating", () -> {
+ 			return isEating();
+ 		});
+ 		data.setCallbackBoolean("isEntityAlive", () -> {
+ 			/**
+ 			 * + Checks whether target entity is alive.
+ 			 */
+ 			return isEntityAlive();
+ 		});
+ 		data.setCallbackBoolean("isEntityInsideOpaqueBlock", () -> {
+ 			/**
+ 			 * + Checks if this entity is inside of an opaque block
+ 			 */
+ 			return isEntityInsideOpaqueBlock();
+ 		});
+ 		data.setCallbackBoolean("isImmuneToExplosions", () -> {
+ 			return isImmuneToExplosions();
+ 		});
+ 		data.setCallbackBoolean("isImmuneToFire", () -> {
+ 			return isImmuneToFire();
+ 		});
+ 		data.setCallbackBoolean("isInLava", () -> {
+ 			return isInLava();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("isInRangeToRender3d", (BaseData params) -> {
+ 			return isInRangeToRender3d(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("isInRangeToRenderDist", (BaseData params) -> {
+ 			/**
+ 			 * + Checks if the entity is in range to render by using the past in distance
+ 			 * and comparing it to its average edge length * 64 * renderDistanceWeight Args:
+ 			 * distance
+ 			 */
+ 			return isInRangeToRenderDist(params.getDouble("distance"));
+ 		});
+ 		data.setCallbackBoolean("isInWater", () -> {
+ 			/**
+ 			 * + Checks if this entity is inside water (if inWater field is true as a result
+ 			 * of handleWaterMovement() returning true)
+ 			 */
+ 			return isInWater();
+ 		});
+ 		data.setCallbackBoolean("isInvisible", () -> {
+ 			return isInvisible();
+ 		});
+ 		data.setCallbackBoolean("isPushedByWater", () -> {
+ 			return isPushedByWater();
+ 		});
+ 		data.setCallbackBoolean("isRiding", () -> {
+ 			/**
+ 			 * + Returns true if the entity is riding another entity, used by render to
+ 			 * rotate the legs to be in 'sit' position for players.
+ 			 */
+ 			return isRiding();
+ 		});
+ 		data.setCallbackBoolean("isSilent", () -> {
+ 			/**
+ 			 * +
+ 			 *
+ 			 * @return True if this entity will not play sounds
+ 			 */
+ 			return isSilent();
+ 		});
+ 		data.setCallbackBoolean("isSneaking", () -> {
+ 			/**
+ 			 * + Returns if this entity is sneaking.
+ 			 */
+ 			return isSneaking();
+ 		});
+ 		data.setCallbackBoolean("isSprinting", () -> {
+ 			/**
+ 			 * + Get if the Entity is sprinting.
+ 			 */
+ 			return isSprinting();
+ 		});
+ 		data.setCallbackBoolean("isWet", () -> {
+ 			/**
+ 			 * + Checks if this entity is either in water or on an open air block in rain
+ 			 * (used in wolves).
+ 			 */
+ 			return isWet();
+ 		});
+ 
+ 		data.setCallbackVoidWithDataArg("setAir", (BaseData params) -> {
+ 			setAir(params.getInt("air"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setAlwaysRenderNameTag", (BaseData params) -> {
+ 			setAlwaysRenderNameTag(params.getBoolean("alwaysRenderNameTag"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setAngles", (BaseData params) -> {
+ 			setAngles(params.getFloat("yaw"), params.getFloat("pitch"));
+ 		});
+ 		data.setCallbackVoid("setBeenAttacked", () -> {
+ 			setBeenAttacked();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setCustomNameTag", (BaseData params) -> {
+ 			setCustomNameTag(params.getString("name"));
+ 		});
+ 		data.setCallbackVoid("setDead", () -> {
+ 			setDead();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setEating", (BaseData params) -> {
+ 			setEating(params.getBoolean("eating"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setEntityId", (BaseData params) -> {
+ 			setEntityId(params.getInt("id"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setFire", (BaseData params) -> {
+ 			setFire(params.getInt("seconds"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setFlag", (BaseData params) -> {
+ 			setFlag(params.getInt("flag"), params.getBoolean("set"));
+ 		});
+ 		data.setCallbackVoid("setInWeb", () -> {
+ 			setInWeb();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setInvisible", (BaseData params) -> {
+ 			setInvisible(params.getBoolean("invisible"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setLocationAndAngles", (BaseData params) -> {
+ 			setLocationAndAngles(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"),
+ 					params.getFloat("yaw"), params.getFloat("pitch"));
+ 		});
+ 		data.setCallbackVoid("setOnFireFromLava", () -> {
+ 			setOnFireFromLava();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setOutsideBorder", (BaseData params) -> {
+ 			setOutsideBorder(params.getBoolean("outsideBorder"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPosition", (BaseData params) -> {
+ 			setPosition(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPositionAndRotation", (BaseData params) -> {
+ 			setPositionAndRotation(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"),
+ 					params.getFloat("yaw"), params.getFloat("pitch"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPositionAndRotation2", (BaseData params) -> {
+ 			setPositionAndRotation2(params.getDouble("d0"), params.getDouble("d1"), params.getDouble("d2"),
+ 					params.getFloat("f"), params.getFloat("f1"), params.getInt("var9"), params.getBoolean("var10"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPositionAndUpdate", (BaseData params) -> {
+ 			setPositionAndUpdate(params.getDouble("d0"), params.getDouble("d1"), params.getDouble("d2"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setRotation", (BaseData params) -> {
+ 			setRotation(params.getFloat("yaw"), params.getFloat("pitch"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setRotationYawHead", (BaseData params) -> {
+ 			setRotationYawHead(params.getFloat("rotation"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setSilent", (BaseData params) -> {
+ 			setSilent(params.getBoolean("isSilent"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setSize", (BaseData params) -> {
+ 			setSize(params.getFloat("f"), params.getFloat("f1"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setSneaking", (BaseData params) -> {
+ 			setSneaking(params.getBoolean("sneaking"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setSprinting", (BaseData params) -> {
+ 			setSprinting(params.getBoolean("flag"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setVelocity", (BaseData params) -> {
+ 			setVelocity(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 
+ 		// Todo: add other getters and other functions. When I get told to! hahhahahah
+ 		data.setCallbackString("getUUID", () -> {
+ 			return entityUniqueID.toString();
+ 		});
+ 
+ 		data.setCallbackInt("getAir", () -> {
+ 			return getAir();
+ 		});
+ 		data.setCallbackBoolean("getAlwaysRenderNameTag", () -> {
+ 			return getAlwaysRenderNameTag();
+ 		});
+ 		data.setCallbackBoolean("getAlwaysRenderNameTagForRender", () -> {
+ 			return getAlwaysRenderNameTagForRender();
+ 		});
+ 		data.setCallbackFloatWithDataArg("getBrightness", (BaseData params) -> {
+ 			return getBrightness(params.getFloat("var1"));
+ 		});
+ 		data.setCallbackIntWithDataArg("getBrightnessForRender", (BaseData params) -> {
+ 			return getBrightnessForRender(params.getFloat("var1"));
+ 		});
+ 		data.setCallbackFloat("getCollisionBorderSize", () -> {
+ 			return getCollisionBorderSize();
+ 		});
+ 		data.setCallbackObject("getCollisionBoundingBox", () -> {
+ 			return getCollisionBoundingBox().makeModData();
+ 		});
+ 		data.setCallbackObject("getCommandSenderEntity", () -> {
+ 			return getCommandSenderEntity().makeModData();
+ 		});
+ 		data.setCallbackString("getCustomNameTag", () -> {
+ 			return getCustomNameTag();
+ 		});
+ 		data.setCallbackString("getDisplayName", () -> {
+ 			return getDisplayName().getUnformattedText();
+ 		});
+ 		data.setCallbackString("getDisplayNameFormatted", () -> {
+ 			return getDisplayName().getFormattedText();
+ 		});
+ 		data.setCallbackDoubleWithDataArg("getDistance", (BaseData params) -> {
+ 			return getDistance(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackDoubleWithDataArg("getDistanceSq", (BaseData params) -> {
+ 			return getDistanceSq(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackDouble("getMountedYOffset", () -> {
+ 			return getMountedYOffset();
+ 		});
+ 		data.setCallbackInt("getEntityId", () -> {
+ 			return getEntityId();
+ 		});
+ 		data.setCallbackString("getEntityString", () -> {
+ 			return getEntityString();
+ 		});
+ 		data.setCallbackFloat("getEyeHeight", () -> {
+ 			return getEyeHeight();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("getFlag", (BaseData params) -> {
+ 			return getFlag(params.getInt("flag"));
+ 		});
+ 		data.setCallbackInt("getMaxFallHeight", () -> {
+ 			return getMaxFallHeight();
+ 		});
+ 		data.setCallbackInt("getMaxInPortalTime", () -> {
+ 			return getMaxInPortalTime();
+ 		});
+ 		data.setCallbackString("getName", () -> {
+ 			return getName();
+ 		});
+ 		data.setCallbackObjectArr("getParts", () -> {
+ 			Entity[] entityArr = getParts();
+ 			ModData[] arr = new ModData[entityArr.length];
+ 			for (int i = 0; i < entityArr.length; i++) {
+ 				if (entityArr[i] != null) {
+ 					arr[i] = entityArr[i].makeModData();
+ 				} else {
+ 					arr[i] = new ModData();
+ 				}
+ 			}
+ 			return arr;
+ 		});
+ 		data.setCallbackInt("getPortalCooldown", () -> {
+ 			return getPortalCooldown();
+ 		});
+ 		data.setCallbackFloat("getRotationYawHead", () -> {
+ 			return getRotationYawHead();
+ 		});
+ 		data.setCallbackString("getSplashSound", () -> {
+ 			return getSplashSound();
+ 		});
+ 		data.setCallbackString("getSwimSound", () -> {
+ 			return getSwimSound();
+ 		});
+ 		data.setCallbackDouble("getYOffset", () -> {
+ 			return getYOffset();
+ 		});
+ 		data.setCallbackString("getClassName", () -> {
+ 			return getClass().getSimpleName();
+ 		});
+ 		data.setCallbackObject("getPositionVector", () -> {
+ 			return getPositionVector().makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getPositionEyes", (BaseData params) -> {
+ 			return getPositionEyes(params.getFloat("partialTicks")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getLook", (BaseData params) -> {
+ 			return getLook(params.getFloat("partialTicks")).makeModData();
+ 		});
+ 		data.setCallbackObject("getLookVec", () -> {
+ 			return getLookVec().makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("getVectorForRotation", (BaseData params) -> {
+ 			return getVectorForRotation(params.getFloat("yaw"), params.getFloat("pitch")).makeModData();
+ 		});
+ 		data.setCallbackString("getSplashSound", () -> {
+ 			return getSplashSound();
+ 		});
+ 		data.setCallbackString("getSwimSound", () -> {
+ 			return getSwimSound();
+ 		});
+ 		data.setCallbackString("toNBT", () -> {
+ 			return toNBT();
+ 		});
+ 		data.setCallbackVoidWithDataArg("fromNBT", (BaseData params) -> {
+ 			fromNBT(params.getString("nbt"));
+ 		});
+ 		return data;
+ 	}
+ 

> CHANGE  418 : 420  @  418 : 419

~ 				this.entityUniqueID = new EaglercraftUUID(tagCompund.getLong("UUIDMost"),
~ 						tagCompund.getLong("UUIDLeast"));

> CHANGE  1 : 2  @  1 : 2

~ 				this.entityUniqueID = EaglercraftUUID.fromString(tagCompund.getString("UUID"));

> CHANGE  226 : 227  @  226 : 227

~ 			for (AxisAlignedBB axisalignedbb : (List<AxisAlignedBB>) list) {

> CHANGE  256 : 257  @  256 : 257

~ 		return HString.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]",

> INSERT  24 : 39  @  24

+ 	public String toNBT() {
+ 		NBTTagCompound nbt = new NBTTagCompound();
+ 		writeToNBT(nbt);
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

> CHANGE  97 : 98  @  97 : 98

~ 	public EaglercraftUUID getUniqueID() {

> INSERT  151 : 177  @  151

+ 
+ 	public void renderDynamicLightsEagler(float partialTicks, boolean isInFrustum) {
+ 		double entityX = prevPosX + (posX - prevPosX) * (double) partialTicks;
+ 		double entityY = prevPosY + (posY - prevPosY) * (double) partialTicks;
+ 		double entityZ = prevPosZ + (posZ - prevPosZ) * (double) partialTicks;
+ 		double entityX2 = entityX - TileEntityRendererDispatcher.staticPlayerX;
+ 		double entityY2 = entityY - TileEntityRendererDispatcher.staticPlayerY;
+ 		double entityZ2 = entityZ - TileEntityRendererDispatcher.staticPlayerZ;
+ 		if (Math.sqrt(entityX2 * entityX2 + entityY2 * entityY2 + entityZ2 * entityZ2) < 48.0 * 48.0) {
+ 			renderDynamicLightsEaglerAt(entityX, entityY, entityZ, entityX2, entityY2, entityZ2, partialTicks,
+ 					isInFrustum);
+ 		}
+ 	}
+ 
+ 	protected void renderDynamicLightsEaglerAt(double entityX, double entityY, double entityZ, double renderX,
+ 			double renderY, double renderZ, float partialTicks, boolean isInFrustum) {
+ 		if (this.isBurning()) {
+ 			float size = Math.max(width, height);
+ 			if (size < 1.0f && !isInFrustum) {
+ 				return;
+ 			}
+ 			float mag = 5.0f * size;
+ 			DynamicLightManager.renderDynamicLight("entity_" + entityId + "_fire", entityX, entityY + height * 0.75,
+ 					entityZ, mag, 0.487f * mag, 0.1411f * mag, false);
+ 		}
+ 	}

> EOF
