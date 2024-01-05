package at.helpch.ea.action;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ActionHolder {
    private static final Random random = new Random();

    private final Action action;
    private final Long delay;
    private final Long chance;

    public ActionHolder(final @NotNull Action action, final @Nullable Long chance, final @Nullable Long delay) {
        this.action = action;
        this.chance = chance;
        this.delay = delay;
    }

    public void execute(final @NotNull Plugin plugin, final @Nullable OfflinePlayer offlinePlayer) {
        if (chance != null && random.nextInt(100) + 1 >= chance) {
            return;
        }

        if (delay != null) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> action.run(plugin, offlinePlayer), delay * 20L);
            return;
        }

        action.run(plugin, offlinePlayer);
    }

    public @NotNull Action action() {
        return action;
    }

    public @Nullable Long getDelay() {
        return delay;
    }

    public @Nullable Long getChance() {
        return chance;
    }
}
