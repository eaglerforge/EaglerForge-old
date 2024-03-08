package net.eaglerforge.api;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class LoggerAPI {
    public static Logger log = LogManager.getLogger();
    public static ModData makeModData() {
        ModData loggerGlobal = new ModData();
        loggerGlobal.setCallbackVoidWithDataArg("setlogger", (BaseData params) -> {
            log = LogManager.getLogger(params.getString("name"));
        });
        loggerGlobal.setCallbackVoidWithDataArg("loginfo", (BaseData params) -> {
            log.info(params.getString("string"));
        });

        loggerGlobal.setCallbackVoidWithDataArg("logdebug", (BaseData params) -> {
            log.debug(params.getString("string"));
        });

        loggerGlobal.setCallbackVoidWithDataArg("logerror", (BaseData params) -> {
            log.error(params.getString("string"));
        });

        loggerGlobal.setCallbackVoidWithDataArg("logwarn", (BaseData params) -> {
            log.warn(params.getString("string"));
        });

        loggerGlobal.setCallbackVoidWithDataArg("logfatal", (BaseData params) -> {
            log.fatal(params.getString("string"));
        });

        loggerGlobal.setCallbackVoidWithDataArg("logtrace", (BaseData params) -> {
            log.trace(params.getString("string"));
        });
        return loggerGlobal;
    }
}
