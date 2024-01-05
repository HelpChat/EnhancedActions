package at.helpch.ea.action;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Action {
    void run(final @NotNull Plugin plugin,
             final @Nullable OfflinePlayer offlinePlayer);
}
