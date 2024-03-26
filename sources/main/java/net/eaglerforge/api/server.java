package net.eaglerforge.api;


import net.minecraft.client.Minecraft;

public class server extends ModData{
    private static Minecraft mc;

    public static ModData makeModData() {
        ModData serverglobal = new ModData();
        serverglobal.setCallbackString("getCurrentQuery", () -> {
            return Minecraft.currentServerData.currentQuery.toString();
        });
        serverglobal.setCallbackString("getServerIP", () -> {
            return Minecraft.currentServerData.serverIP;
        });
        serverglobal.setCallbackString("getServerMOTD", () -> {
            return Minecraft.currentServerData.serverMOTD;
        });
        serverglobal.setCallbackString("getServerName", () -> {
            return Minecraft.currentServerData.serverName;
        });
        serverglobal.setCallbackString("getGameVersion", () -> {
            return Minecraft.currentServerData.gameVersion;
        });
        serverglobal.setCallbackString("getPlayerListToString", () -> {
            return Minecraft.currentServerData.playerList;
        });
        serverglobal.setCallbackDouble("getPingSentTime", () -> {
            return Minecraft.currentServerData.pingSentTime;
        });
        serverglobal.setCallbackDouble("getPingToServer", () -> {
            return Minecraft.currentServerData.pingToServer;
        });

        return serverglobal;
    }
}
