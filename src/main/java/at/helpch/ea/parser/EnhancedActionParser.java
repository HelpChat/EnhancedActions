package at.helpch.ea.parser;

import at.helpch.ea.action.ActionHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public interface EnhancedActionParser {
    @NotNull Optional<ActionHolder> parse(final @NotNull Map<String, Object> actionData);
}
