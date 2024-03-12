
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  4 : 8  @  4 : 6

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
~ import net.lax1dude.eaglercraft.v1_8.mojang.authlib.Property;

> CHANGE  19 : 21  @  19 : 20

~ 		for (int i = 0; i < players.length; ++i) {
~ 			EntityPlayerMP entityplayermp = players[i];

> CHANGE  79 : 81  @  79 : 80

~ 		for (int i = 0, l = this.players.size(); i < l; ++i) {
~ 			S38PacketPlayerListItem.AddPlayerData s38packetplayerlistitem$addplayerdata = this.players.get(i);

> CHANGE  71 : 72  @  71 : 72

~ 	public class AddPlayerData extends ModData {

> INSERT  17 : 43  @  17

+ 		public void loadModData(BaseData data) {
+ 			// Yep, all the fields are 'final', so i can be lazy here.
+ 		}
+ 
+ 		public ModData makeModData() {
+ 			ModData data = new ModData();
+ 			data.setCallbackVoid("reload", () -> {
+ 				loadModData(data);
+ 			});
+ 			data.set("ping", ping);
+ 			if (gamemode != null) {
+ 				data.set("gamemode", gamemode.name());
+ 			}
+ 			if (displayName != null) {
+ 				data.set("displayNameFormatted", displayName.getFormattedText());
+ 				data.set("displayName", displayName.getUnformattedText());
+ 			}
+ 			if (profile != null && profile.name != null) {
+ 				data.set("profileName", profile.name);
+ 			}
+ 			data.setCallbackObject("getRef", () -> {
+ 				return this;
+ 			});
+ 			return data;
+ 		}
+ 

> EOF
