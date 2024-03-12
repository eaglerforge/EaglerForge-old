
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  9 : 14  @  9 : 14

~ 	public String lang;
~ 	public int view;
~ 	public EntityPlayer.EnumChatVisibility chatVisibility;
~ 	public boolean enableColors;
~ 	public int modelPartFlags;

> INSERT  48 : 52  @  48

+ 
+ 	public int getViewDistance() {
+ 		return this.view;
+ 	}

> EOF
