package com.jjeanniard.plugin;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

import static com.jjeanniard.plugin.Log.setLog;
import static java.util.logging.Level.INFO;

/**
 * Study plugin.
 *
 * @author Jjeanniard
 * @version 0.0.2
 * @see JavaPlugin
 * @see JavaPluginInit
 * @see Log
 * @see #getInstance()
 * @since 0.0.1
 */
@SuppressWarnings("unused")
public final class Study extends JavaPlugin {

    private static Study instance;


    /**
     * Constructor - Called when plugin is loaded.
     */
    public Study(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
    }

    /**
     * @return The plugin instance.
     */
    public static Study getInstance() {
        return instance;
    }

    /**
     * Called when plugin is set up.
     */
    @Override
    protected void setup() {
        setLog(INFO, "Plugin en  cours de demarrage");
        setLog(INFO, instance.getName() + " version " + instance.getManifest().getVersion().toString());
    }

    /**
     * Called when plugin is enabled.
     */
    @Override
    protected void start() {

    }

    /**
     * Called when plugin is disabled.
     */
    @Override
    public void shutdown() {

    }

}
