package at.helpch.ea.impl.parser;

import at.helpch.ea.action.ActionHolder;
import at.helpch.ea.impl.action.MessageAction;
import at.helpch.ea.parser.EnhancedActionParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class MessageActionParser implements EnhancedActionParser {

    private static MessageActionParser instance;

    private MessageActionParser() {
    }

    public static MessageActionParser getInstance() {
        if (instance == null) {
            instance = new MessageActionParser();
        }
        return instance;
    }

    @Override
    public @NotNull Optional<ActionHolder> parse(final @NotNull Map<@NotNull String, @Nullable Object> actionData) {
        final Object unparsedMessage = actionData.get("message");

        if (!(unparsedMessage instanceof String)) {
            return Optional.empty();
        }

        return Optional.of(new ActionHolder(
            new MessageAction((String) unparsedMessage),
            actionData
        ));

    }
}
