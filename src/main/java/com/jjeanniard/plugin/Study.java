package com.jjeanniard.plugin;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.jjeanniard.plugin.commande.Command;

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
        setLog(INFO, "Initialisation du plugin");
        setLog(INFO, instance.getName() + " version " + instance.getManifest().getVersion().toString());
        getCommandRegistry().registerCommand(new Command());
    }

    /**
     * Called when plugin is enabled.
     */
    @Override
    protected void start() {
        setLog(INFO, "Plugin en  cours de démarrage");
        getEventRegistry().register(PlayerConnectEvent.class, event -> {
            PlayerRef playerRef = event.getPlayerRef();

            Universe.get().sendMessage(Message.raw("Bienvenue joueur :" + playerRef.getUsername()));
        });
    }

    /**
     * Called when plugin is disabled.
     */
    @Override
    public void shutdown() {
        setLog(INFO, "Plugin de  cours d'arret");
    }

}
