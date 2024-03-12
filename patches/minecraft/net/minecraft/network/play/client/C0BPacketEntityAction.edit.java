
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  3 : 4  @  3

+ 

> CHANGE  6 : 9  @  6 : 9

~ 	public int entityID;
~ 	public C0BPacketEntityAction.Action action;
~ 	public int auxData;

> INSERT  14 : 20  @  14

+ 	public C0BPacketEntityAction(int entityId, C0BPacketEntityAction.Action action, int auxData) {
+ 		this.entityID = entityId;
+ 		this.action = action;
+ 		this.auxData = auxData;
+ 	}
+ 

> EOF
