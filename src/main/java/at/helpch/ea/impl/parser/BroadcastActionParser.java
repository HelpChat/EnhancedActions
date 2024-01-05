package at.helpch.ea.impl.parser;

import at.helpch.ea.action.ActionHolder;
import at.helpch.ea.impl.action.BroadcastAction;
import at.helpch.ea.parser.EnhancedActionParser;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public class BroadcastActionParser implements EnhancedActionParser {

    private static BroadcastActionParser instance;

    private BroadcastActionParser() {
    }

    public static BroadcastActionParser getInstance() {
        if (instance == null) {
            instance = new BroadcastActionParser();
        }
        return instance;
    }

    @Override
    public @NotNull Optional<ActionHolder> parse(final @NotNull Map<String, Object> actionData) {
        final Object unparsedChance = actionData.get("chance");
        final Object unparsedDelay = actionData.get("delay");
        final Object unparsedMessage = actionData.get("message");

        if (!(unparsedMessage instanceof String)) {
            return Optional.empty();
        }

        final Long chance = unparsedChance instanceof Long ? (Long) unparsedChance : null;
        final Long delay = unparsedDelay instanceof Long ? (Long) unparsedDelay : null;

        return Optional.of(new ActionHolder(
                new BroadcastAction((String) unparsedMessage),
                chance,
                delay
        ));

    }
}
