package net.eaglerforge.api;


import net.minecraft.client.Minecraft;

public class ServerAPI extends ModData {
    private static Minecraft mc;

    public static ModData makeModData() {
        ModData serverGlobal = new ModData();
        serverGlobal.setCallbackString("getCurrentQuery", () -> {
            return Minecraft.currentServerData.currentQuery.toString();
        });
        serverGlobal.setCallbackString("getServerIP", () -> {
            return Minecraft.currentServerData.serverIP;
        });
        serverGlobal.setCallbackString("getServerMOTD", () -> {
            return Minecraft.currentServerData.serverMOTD;
        });
        serverGlobal.setCallbackString("getServerName", () -> {
            return Minecraft.currentServerData.serverName;
        });
        serverGlobal.setCallbackString("getGameVersion", () -> {
            return Minecraft.currentServerData.gameVersion;
        });
        serverGlobal.setCallbackString("getPlayerListToString", () -> {
            return Minecraft.currentServerData.playerList;
        });
        serverGlobal.setCallbackDouble("getPingSentTime", () -> {
            return Minecraft.currentServerData.pingSentTime;
        });
        serverGlobal.setCallbackDouble("getPingToServer", () -> {
            return Minecraft.currentServerData.pingToServer;
        });

        return serverGlobal;
    }
}
