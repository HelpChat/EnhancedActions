package at.helpch.ea.impl.action;

import at.helpch.ea.action.Action;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageAction implements Action {

    private final @NotNull String message;

    public MessageAction(final @NotNull String message) {
        this.message = message;
    }

    @Override
    public void run(final @NotNull Plugin plugin, final @Nullable OfflinePlayer offlinePlayer) {
        if (offlinePlayer == null) {
            return;
        }

        if (!offlinePlayer.isOnline()) {
            return;
        }

        final Player player = offlinePlayer.getPlayer();

        if (player == null) {
            return;
        }

        player.sendMessage(message);
    }
}
