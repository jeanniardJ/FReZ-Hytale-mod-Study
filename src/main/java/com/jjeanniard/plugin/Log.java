package com.jjeanniard.plugin;

import com.hypixel.hytale.logger.HytaleLogger;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class Log {
    private static final HytaleLogger Logger = HytaleLogger.getLogger();

    public static void setLog(@Nonnull Level type, @Nonnull String message) {
        if (type == Level.INFO) {
            Logger.atInfo().log(message);
        } else if (type == Level.WARNING) {
            Logger.atWarning().log(message);
        } else if (type == Level.SEVERE) {
            Logger.atSevere().log(message);
        } else {
            Logger.at(type).log(message);
        }
    }
}
