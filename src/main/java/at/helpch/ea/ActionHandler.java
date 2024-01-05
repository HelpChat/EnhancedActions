package at.helpch.ea;

import at.helpch.ea.action.ActionHolder;
import at.helpch.ea.parser.ActionParser;
import at.helpch.ea.parser.EnhancedActionParser;
import at.helpch.ea.impl.parser.MessageActionParser;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ActionHandler {
    static final Set<EnhancedActionParser> DEFAULT_ENHANCED_ACTION_PARSERS = Set.of(
            MessageActionParser.getInstance()
    );
    static final Set<ActionParser> DEFAULT_ACTION_PARSERS = Set.of(
    );

    final Set<ActionParser> actionParsers = new HashSet<>();
    final Set<EnhancedActionParser> enhancedActionParsers = new HashSet<>();
    final Plugin plugin;

    public ActionHandler(final @NotNull Plugin plugin, final boolean loadDefaults) {
        this.plugin = plugin;

        if (loadDefaults) {
            this.loadDefaults();
        }
    }

    /**
     * Parses the given data into an action and executes it.
     * @param offlinePlayer The player to execute the action for.
     * @param actionData The data to parse into an action.
     * @return Whether an action was found from the given data.
     */
    public boolean execute(final @Nullable OfflinePlayer offlinePlayer, final @NotNull Object actionData) {
        // If action data is not a map or contains non-string keys, use normal action parsers.
        if (!(actionData instanceof Map) || ((Map<?, ?>) actionData).keySet().stream().anyMatch(key -> !(key instanceof String))) {
            final ActionHolder actionHolder = actionParsers.stream()
                    .map(parser -> parser.parse(actionData))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst()
                    .orElse(null);

            if (actionHolder == null) {
                return false;
            }

            actionHolder.execute(this.plugin, offlinePlayer);
            return true;
        }

        final ActionHolder actionHolder = enhancedActionParsers.stream()
                .map(parser -> parser.parse((Map<String, Object>) actionData))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(null);

        if (actionHolder == null) {
            return false;
        }

        actionHolder.execute(this.plugin, offlinePlayer);
        return true;
    }

    public void registerActionParser(final @NotNull ActionParser action) {
        this.actionParsers.add(action);
    }

    public void registerEnhancedActionParser(final @NotNull EnhancedActionParser action) {
        this.enhancedActionParsers.add(action);
    }

    private void loadDefaults() {
        DEFAULT_ENHANCED_ACTION_PARSERS.forEach(this::registerEnhancedActionParser);
        DEFAULT_ACTION_PARSERS.forEach(this::registerActionParser);
    }
}
