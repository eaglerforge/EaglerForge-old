
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  3 : 4  @  3

+ 

> CHANGE  8 : 11  @  8 : 11

~ 	public int entityId;
~ 	public C02PacketUseEntity.Action action;
~ 	public Vec3 hitVec;

> INSERT  14 : 24  @  14

+ 	public C02PacketUseEntity(int entityIdentifier, C02PacketUseEntity.Action action) {
+ 		this.entityId = entityIdentifier;
+ 		this.action = action;
+ 	}
+ 
+ 	public C02PacketUseEntity(int entityIdentifier, Vec3 hitVec) {
+ 		this(entityIdentifier, C02PacketUseEntity.Action.INTERACT_AT);
+ 		this.hitVec = hitVec;
+ 	}
+ 

> EOF
