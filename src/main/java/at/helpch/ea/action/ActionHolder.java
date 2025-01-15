package at.helpch.ea.action;

import at.helpch.ea.parser.EnhancedActionParser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ActionHolder {

    private final Action action;
    private final Long delay;
    private final Long chance;

    public ActionHolder(final @NotNull Action action, final @Nullable Long chance, final @Nullable Long delay) {
        this.action = action;
        this.chance = chance;
        this.delay = delay;
    }

    /**
     * Alternative constructor for {@link EnhancedActionParser} with default keys for {@link #delay} and {@link #chance}.
     *
     * @param action     action to hold
     * @param actionData action data to get delay and chance from
     */
    public ActionHolder(final @NotNull Action action, final @NotNull Map<@NotNull String, @Nullable Object> actionData) {
        var unparsedDelay = actionData.get(EnhancedActionParser.DELAY_KEY);
        var unparsedChance = actionData.get(EnhancedActionParser.CHANCE_KEY);

        this.action = action;
        this.delay = unparsedDelay instanceof Long ? (Long) unparsedDelay : null;
        this.chance = unparsedChance instanceof Long ? (Long) unparsedChance : null;
    }

    public void execute(final @NotNull Plugin plugin, final @Nullable OfflinePlayer offlinePlayer) {
        if (chance != null && chance < 100 && ThreadLocalRandom.current().nextInt(100) + 1 >= chance) {
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
