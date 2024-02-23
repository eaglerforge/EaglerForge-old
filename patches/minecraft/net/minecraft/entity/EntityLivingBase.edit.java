
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  5 : 6  @  5

+ 

> CHANGE  4 : 9  @  4 : 6

~ 
~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
~ import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;

> INSERT  3 : 4  @  3

+ import net.minecraft.client.Minecraft;

> DELETE  1  @  1 : 5

> CHANGE  39 : 41  @  39 : 40

~ 	private static final EaglercraftUUID sprintingSpeedBoostModifierUUID = EaglercraftUUID
~ 			.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");

> INSERT  75 : 380  @  75

+ 	@Override
+ 	public void loadModData(BaseData data) {
+ 		super.loadModData(data);
+ 		isSwingInProgress = data.getBoolean("isSwingInProgress");
+ 		arrowHitTimer = data.getInt("arrowHitTimer");
+ 		hurtTime = data.getInt("hurtTime");
+ 		maxHurtTime = data.getInt("maxHurtTime");
+ 		swingProgressInt = data.getInt("swingProgressInt");
+ 		attackedAtYaw = data.getFloat("attackedAtYaw");
+ 		deathTime = data.getInt("deathTime");
+ 
+ 		prevSwingProgress = data.getFloat("prevSwingProgress");
+ 		swingProgress = data.getFloat("swingProgress");
+ 		prevLimbSwingAmount = data.getFloat("prevLimbSwingAmount");
+ 		limbSwingAmount = data.getFloat("limbSwingAmount");
+ 		limbSwing = data.getFloat("limbSwing");
+ 		maxHurtResistantTime = data.getInt("maxHurtResistantTime");
+ 		prevCameraPitch = data.getFloat("prevCameraPitch");
+ 		cameraPitch = data.getFloat("cameraPitch");
+ 		renderYawOffset = data.getFloat("renderYawOffset");
+ 		prevRenderYawOffset = data.getFloat("prevRenderYawOffset");
+ 		rotationYawHead = data.getFloat("rotationYawHead");
+ 		prevRotationYawHead = data.getFloat("prevRotationYawHead");
+ 		jumpMovementFactor = data.getFloat("jumpMovementFactor");
+ 
+ 		recentlyHit = data.getInt("recentlyHit");
+ 		dead = data.getBoolean("dead");
+ 		entityAge = data.getInt("entityAge");
+ 		onGroundSpeedFactor = data.getFloat("onGroundSpeedFactor");
+ 		prevOnGroundSpeedFactor = data.getFloat("prevOnGroundSpeedFactor");
+ 		movedDistance = data.getFloat("movedDistance");
+ 		prevMovedDistance = data.getFloat("prevMovedDistance");
+ 		scoreValue = data.getInt("scoreValue");
+ 		lastDamage = data.getFloat("lastDamage");
+ 		isJumping = data.getBoolean("isJumping");
+ 
+ 		moveForward = data.getFloat("moveForward");
+ 		moveStrafing = data.getFloat("moveStrafing");
+ 		randomYawVelocity = data.getFloat("randomYawVelocity");
+ 		newPosRotationIncrements = data.getInt("newPosRotationIncrements");
+ 		newPosX = data.getDouble("newPosX");
+ 		newPosY = data.getDouble("newPosY");
+ 		newPosZ = data.getDouble("newPosZ");
+ 		newRotationPitch = data.getDouble("newRotationPitch");
+ 		newRotationYaw = data.getDouble("newRotationYaw");
+ 		revengeTimer = data.getInt("revengeTimer");
+ 		lastAttackerTime = data.getInt("lastAttackerTime");
+ 		landMovementFactor = data.getFloat("landMovementFactor");
+ 		jumpTicks = data.getInt("jumpTicks");
+ 		absorptionAmount = data.getFloat("absorptionAmount");
+ 	}
+ 
+ 	@Override
+ 	public ModData makeModData() {
+ 		ModData data = super.makeModData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackObjectArr("getPreviousEquipment", () -> {
+ 			ModData[] itemstackBaseDatas = new ModData[previousEquipment.length];
+ 			for (int i = 0; i < previousEquipment.length; i++) {
+ 				if (previousEquipment[i] != null) {
+ 					itemstackBaseDatas[i] = previousEquipment[i].makeModData();
+ 				}
+ 			}
+ 			return itemstackBaseDatas;
+ 		});
+ 		data.setCallbackObject("getAttackingPlayer", () -> {
+ 			if (attackingPlayer != null) {
+ 				return attackingPlayer.makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackObject("getLastAttacker", () -> {
+ 			if (lastAttacker != null) {
+ 				return lastAttacker.makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackObject("getEntityLivingToAttack", () -> {
+ 			if (entityLivingToAttack != null) {
+ 				return entityLivingToAttack.makeModData();
+ 			} else {
+ 				return new ModData();
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("setEntityLivingToAttack", (BaseData params) -> {
+ 			if (params.getBaseData("entity") instanceof EntityLivingBase) {
+ 				entityLivingToAttack = (EntityLivingBase) params.getBaseData("entity");
+ 			}
+ 		});
+ 		data.set("isSwingInProgress", isSwingInProgress);
+ 		data.set("arrowHitTimer", arrowHitTimer);
+ 		data.set("hurtTime", hurtTime);
+ 		data.set("maxHurtTime", maxHurtTime);
+ 		data.set("swingProgressInt", swingProgressInt);
+ 		data.set("attackedAtYaw", attackedAtYaw);
+ 		data.set("deathTime", deathTime);
+ 
+ 		data.set("prevSwingProgress", prevSwingProgress);
+ 		data.set("swingProgress", swingProgress);
+ 		data.set("prevLimbSwingAmount", prevLimbSwingAmount);
+ 		data.set("limbSwingAmount", limbSwingAmount);
+ 		data.set("limbSwing", limbSwing);
+ 		data.set("maxHurtResistantTime", maxHurtResistantTime);
+ 
+ 		data.set("prevCameraPitch", prevCameraPitch);
+ 		data.set("cameraPitch", cameraPitch);
+ 		data.set("renderYawOffset", renderYawOffset);
+ 		data.set("prevRenderYawOffset", prevRenderYawOffset);
+ 		data.set("rotationYawHead", rotationYawHead);
+ 		data.set("prevRotationYawHead", prevRotationYawHead);
+ 		data.set("jumpMovementFactor", jumpMovementFactor);
+ 
+ 		data.set("recentlyHit", recentlyHit);
+ 		data.set("dead", dead);
+ 		data.set("entityAge", entityAge);
+ 		data.set("onGroundSpeedFactor", onGroundSpeedFactor);
+ 		data.set("movedDistance", movedDistance);
+ 		data.set("prevOnGroundSpeedFactor", prevOnGroundSpeedFactor);
+ 		data.set("prevMovedDistance", prevMovedDistance);
+ 		data.set("scoreValue", scoreValue);
+ 		data.set("lastDamage", lastDamage);
+ 		data.set("isJumping", isJumping);
+ 
+ 		data.set("moveForward", moveForward);
+ 		data.set("moveStrafing", moveStrafing);
+ 		data.set("randomYawVelocity", randomYawVelocity);
+ 		data.set("newPosRotationIncrements", newPosRotationIncrements);
+ 		data.set("newPosX", newPosX);
+ 		data.set("newPosY", newPosY);
+ 		data.set("newPosZ", newPosZ);
+ 		data.set("newRotationPitch", newRotationPitch);
+ 		data.set("newRotationYaw", newRotationYaw);
+ 		data.set("revengeTimer", revengeTimer);
+ 		data.set("lastAttackerTime", lastAttackerTime);
+ 		data.set("landMovementFactor", landMovementFactor);
+ 		data.set("jumpTicks", jumpTicks);
+ 		data.set("absorptionAmount", absorptionAmount);
+ 
+ 		data.setCallbackBoolean("canBreatheUnderwater", () -> {
+ 			return canBreatheUnderwater();
+ 		});
+ 		data.setCallbackBoolean("isChild", () -> {
+ 			return isChild();
+ 		});
+ 		data.setCallbackBoolean("canDropLoot", () -> {
+ 			return canDropLoot();
+ 		});
+ 		data.setCallbackIntWithDataArg("decreaseAirSupply", (BaseData params) -> {
+ 			return decreaseAirSupply(params.getInt("parInt1"));
+ 		});
+ 		data.setCallbackBoolean("isPlayer", () -> {
+ 			return isPlayer();
+ 		});
+ 		data.setCallbackObject("getAITarget", () -> {
+ 			return getAITarget().makeModData();
+ 		});
+ 		data.setCallbackInt("getRevengeTimer", () -> {
+ 			return getRevengeTimer();
+ 		});
+ 		data.setCallbackInt("getLastAttackerTime", () -> {
+ 			return getLastAttackerTime();
+ 		});
+ 		data.setCallbackInt("getAge", () -> {
+ 			return getAge();
+ 		});
+ 		data.setCallbackVoid("clearActivePotions", () -> {
+ 			clearActivePotions();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("isPotionActive", (BaseData params) -> {
+ 			return isPotionActive(params.getInt("potionId"));
+ 		});
+ 		data.setCallbackBoolean("isEntityUndead", () -> {
+ 			return isEntityUndead();
+ 		});
+ 		data.setCallbackVoidWithDataArg("removePotionEffectClient", (BaseData params) -> {
+ 			removePotionEffectClient(params.getInt("potionId"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("removePotionEffect", (BaseData params) -> {
+ 			removePotionEffect(params.getInt("potionId"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("heal", (BaseData params) -> {
+ 			heal(params.getFloat("f"));
+ 		});
+ 		data.setCallbackFloat("getHealth", () -> {
+ 			return getHealth();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setHealth", (BaseData params) -> {
+ 			setHealth(params.getFloat("health"));
+ 		});
+ 		data.setCallbackString("getHurtSound", () -> {
+ 			return getHurtSound();
+ 		});
+ 		data.setCallbackString("getDeathSound", () -> {
+ 			return getDeathSound();
+ 		});
+ 		data.setCallbackVoid("addRandomDrop", () -> {
+ 			addRandomDrop();
+ 		});
+ 		data.setCallbackBoolean("isOnLadder", () -> {
+ 			return isOnLadder();
+ 		});
+ 		data.setCallbackBoolean("isEntityAlive", () -> {
+ 			return isEntityAlive();
+ 		});
+ 		data.setCallbackVoidWithDataArg("fall", (BaseData params) -> {
+ 			fall(params.getFloat("f"), params.getFloat("f1"));
+ 		});
+ 		data.setCallbackStringWithDataArg("getFallSoundString", (BaseData params) -> {
+ 			return getFallSoundString(params.getInt("damageValue"));
+ 		});
+ 		data.setCallbackVoid("performHurtAnimation", () -> {
+ 			performHurtAnimation();
+ 		});
+ 		data.setCallbackInt("getTotalArmorValue", () -> {
+ 			return getTotalArmorValue();
+ 		});
+ 		data.setCallbackVoidWithDataArg("damageArmor", (BaseData params) -> {
+ 			damageArmor(params.getFloat("parFloat1"));
+ 		});
+ 		data.setCallbackFloat("getMaxHealth", () -> {
+ 			return getMaxHealth();
+ 		});
+ 		data.setCallbackInt("getArrowCountInEntity", () -> {
+ 			return getArrowCountInEntity();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setArrowCountInEntity", (BaseData params) -> {
+ 			setArrowCountInEntity(params.getInt("count"));
+ 		});
+ 		data.setCallbackVoid("swingItem", () -> {
+ 			swingItem();
+ 		});
+ 		data.setCallbackVoid("kill", () -> {
+ 			kill();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setSprinting", (BaseData params) -> {
+ 			setSprinting(params.getBoolean("flag"));
+ 		});
+ 		data.setCallbackFloat("getSoundVolume", () -> {
+ 			return getSoundVolume();
+ 		});
+ 		data.setCallbackFloat("getSoundPitch", () -> {
+ 			return getSoundPitch();
+ 		});
+ 		data.setCallbackBoolean("isMovementBlocked", () -> {
+ 			return isMovementBlocked();
+ 		});
+ 		data.setCallbackFloat("getJumpUpwardsMotion", () -> {
+ 			return getJumpUpwardsMotion();
+ 		});
+ 		data.setCallbackVoid("jump", () -> {
+ 			jump();
+ 		});
+ 		data.setCallbackVoid("updateAITick", () -> {
+ 			updateAITick();
+ 		});
+ 		data.setCallbackVoid("handleJumpLava", () -> {
+ 			handleJumpLava();
+ 		});
+ 		data.setCallbackFloat("getAIMoveSpeed", () -> {
+ 			return getAIMoveSpeed();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setAIMoveSpeed", (BaseData params) -> {
+ 			setAIMoveSpeed(params.getFloat("speedIn"));
+ 		});
+ 		data.setCallbackVoid("collideWithNearbyEntities", () -> {
+ 			collideWithNearbyEntities();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setJumping", (BaseData params) -> {
+ 			setJumping(params.getBoolean("parFlag"));
+ 		});
+ 		data.setCallbackBoolean("canBeCollidedWith", () -> {
+ 			return canBeCollidedWith();
+ 		});
+ 		data.setCallbackBoolean("canBePushed", () -> {
+ 			return canBePushed();
+ 		});
+ 		data.setCallbackVoid("setBeenAttacked", () -> {
+ 			setBeenAttacked();
+ 		});
+ 		data.setCallbackFloat("getRotationYawHead", () -> {
+ 			return getRotationYawHead();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setRotationYawHead", (BaseData params) -> {
+ 			setRotationYawHead(params.getFloat("f"));
+ 		});
+ 		data.setCallbackFloat("getAbsorptionAmount", () -> {
+ 			return getAbsorptionAmount();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setAbsorptionAmount", (BaseData params) -> {
+ 			setAbsorptionAmount(params.getFloat("amount"));
+ 		});
+ 		data.setCallbackVoid("markPotionsDirty", () -> {
+ 			markPotionsDirty();
+ 		});
+ 
+ 		return data;
+ 	}
+ 

> CHANGE  189 : 190  @  189 : 190

~ 	public EaglercraftRandom getRNG() {

> INSERT  1309 : 1320  @  1309

+ 
+ 	protected void renderDynamicLightsEaglerAt(double entityX, double entityY, double entityZ, double renderX,
+ 			double renderY, double renderZ, float partialTicks, boolean isInFrustum) {
+ 		super.renderDynamicLightsEaglerAt(entityX, entityY, entityZ, renderX, renderY, renderZ, partialTicks,
+ 				isInFrustum);
+ 		Minecraft mc = Minecraft.getMinecraft();
+ 		if (mc.gameSettings.thirdPersonView != 0 || !(mc.getRenderViewEntity() == this)) {
+ 			Minecraft.getMinecraft().entityRenderer.renderHeldItemLight(this, 1.0f);
+ 		}
+ 	}
+ 

> EOF
