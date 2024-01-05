package at.helpch.ea.impl.action;

import at.helpch.ea.action.Action;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BroadcastAction implements Action {

    private final String message;

    public BroadcastAction(final @NotNull String message) {
        this.message = message;
    }

    @Override
    public void run(final @NotNull Plugin plugin, final @Nullable OfflinePlayer offlinePlayer) {
        Bukkit.broadcastMessage(message);
    }
}
