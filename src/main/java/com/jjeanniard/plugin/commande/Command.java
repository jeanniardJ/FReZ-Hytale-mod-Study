package com.jjeanniard.plugin.commande;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;

import java.util.concurrent.CompletableFuture;

public class Command extends AbstractCommand {

    public Command() {
        super("/salut", "Bonjour, joueur");
    }

    /**
     * Pas besoin de permission
     *
     * @return false
     */
    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> execute(CommandContext commandContext) {
        commandContext.sendMessage(Message.raw("Study plugin command"));
        return null;
    }
}
