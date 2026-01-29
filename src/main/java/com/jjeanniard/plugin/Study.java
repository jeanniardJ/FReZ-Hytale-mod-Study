package com.jjeanniard.plugin;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.util.Config;
import com.jjeanniard.plugin.commande.Command;
import com.jjeanniard.plugin.config.MyConfig;

import javax.annotation.Nonnull;
import java.nio.file.Path;

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

    private final Config<MyConfig> config;

    private MyConfig cfg;

    /**
     * Constructor - Called when plugin is loaded.
     */
    public Study(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        // Load the configuration, appelé dela propriété CODEC qui est Static dans MyConfig
        config = this.withConfig("config", MyConfig.CODEC);
    }

    /**
     * Gets the plugin instance.
     *
     * @return The plugin instance.
     */
    public static Study getInstance() {
        return instance;
    }

    /**
     * Crée un fichier de configuration par défaut s'il n'existe pas.
     *
     * @param config   de l'instance Config à sauvegarder.
     * @param fileName du fichier de configuration.
     */
    private void ensureConfigFileExists(Config<MyConfig> config, String fileName) {
        Path configPath = this.getDataDirectory().resolve(fileName);
        if (!configPath.toFile().exists()) {
            config.save().thenRun(() -> setLog(INFO, "Fichier de configuration créé par défaut.")).exceptionally(ex -> {
                setLog(INFO, "Erreur lors de la création du fichier de configuration : " + ex.getMessage());
                return null;
            });
        } else {
            setLog(INFO, "Fichier de configuration chargé depuis : " + configPath);
        }
    }

    /**
     * Called when plugin is set up.
     */
    @Override
    protected void setup() {
        setLog(INFO, "Initialisation du plugin");
        // Load configuration
        MyConfig cfg = config.get();
        // Register commands
        getCommandRegistry().registerCommand(new Command());
        setLog(INFO, instance.getName() + " version " + instance.getManifest().getVersion().toString());
    }

    /**
     * Called when plugin is enabled.
     */
    @Override
    protected void start() {
        setLog(INFO, "Plugin en  cours de démarrage");

        // Create default config if not exists
        ensureConfigFileExists(config, "config.json");

        getEventRegistry().register(PlayerConnectEvent.class, event -> {
            PlayerRef playerRef = event.getPlayerRef();

            Universe.get().sendMessage(Message.raw(cfg.getWelcomeMessage() + " " + playerRef.getUsername()));
        });
    }

    /**
     * Called when plugin is disabled.
     */
    @Override
    protected void shutdown() {
        if (config != null) {
            setLog(INFO, "Sauvegarde de la configuration...");
            config.save().join(); // OK de bloquer au shutdown
        }
        setLog(INFO, "Plugin en cours d'arrêt");
    }

}
