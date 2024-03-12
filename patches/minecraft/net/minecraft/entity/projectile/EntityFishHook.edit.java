
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  4 : 7  @  4

+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> INSERT  126 : 181  @  126

+ 	@Override
+ 	public void loadModData(BaseData data) {
+ 		super.loadModData(data);
+ 		inGround = data.getBoolean("inGround");
+ 		xTile = data.getInt("xTile");
+ 		yTile = data.getInt("yTile");
+ 		zTile = data.getInt("zTile");
+ 		shake = data.getInt("shake");
+ 		ticksCatchable = data.getInt("ticksCatchable");
+ 		ticksCatchableDelay = data.getInt("ticksCatchableDelay");
+ 		ticksCaughtDelay = data.getInt("ticksCaughtDelay");
+ 		ticksInAir = data.getInt("ticksInAir");
+ 		ticksInGround = data.getInt("ticksInGround");
+ 		if (caughtEntity != null) {
+ 			caughtEntity.loadModData(data.getBaseData("caughtEntity"));
+ 		}
+ 		fishApproachAngle = data.getFloat("fishApproachAngle");
+ 		fishPitch = data.getDouble("fishPitch");
+ 		fishYaw = data.getDouble("fishYaw");
+ 		fishPosRotationIncrements = data.getInt("fishPosRotationIncrements");
+ 		fishX = data.getDouble("fishX");
+ 		fishY = data.getDouble("fishY");
+ 		fishZ = data.getDouble("fishZ");
+ 	}
+ 
+ 	@Override
+ 	public ModData makeModData() {
+ 		ModData data = super.makeModData();
+ 		data.set("inGround", inGround);
+ 		data.set("xTile", xTile);
+ 		data.set("yTile", yTile);
+ 		data.set("zTile", zTile);
+ 		data.set("shake", shake);
+ 		data.set("ticksCatchable", ticksCatchable);
+ 		data.set("ticksCatchableDelay", ticksCatchableDelay);
+ 		data.set("ticksCaughtDelay", ticksCaughtDelay);
+ 		data.set("ticksInAir", ticksInAir);
+ 		data.set("ticksInGround", ticksInGround);
+ 		if (caughtEntity != null) {
+ 			data.set("caughtEntity", caughtEntity.makeModData());
+ 		}
+ 		data.set("fishApproachAngle", fishApproachAngle);
+ 		data.set("fishPitch", fishPitch);
+ 		data.set("fishYaw", fishYaw);
+ 		data.set("fishPosRotationIncrements", fishPosRotationIncrements);
+ 		data.set("fishX", fishX);
+ 		data.set("fishY", fishY);
+ 		data.set("fishZ", fishZ);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
