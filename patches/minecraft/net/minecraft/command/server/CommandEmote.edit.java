
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  10 : 11  @  10

+ import net.minecraft.util.ChatComponentText;

> INSERT  2 : 3  @  2

+ import net.minecraft.util.StringUtils;

> INSERT  2 : 3  @  2

+ 

> CHANGE  1 : 2  @  1 : 2

~ 		return "net/me";

> INSERT  16 : 21  @  16

+ 			if (MinecraftServer.getServer().worldServers[0].getWorldInfo().getGameRulesInstance()
+ 					.getBoolean("colorCodes")) {
+ 				ichatcomponent = new ChatComponentText(
+ 						StringUtils.translateControlCodesAlternate(ichatcomponent.getFormattedText()));
+ 			}

> EOF
