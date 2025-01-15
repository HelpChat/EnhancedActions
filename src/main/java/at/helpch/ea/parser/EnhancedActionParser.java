package at.helpch.ea.parser;

import at.helpch.ea.action.ActionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public interface EnhancedActionParser {

    String DELAY_KEY = "delay";
    String CHANCE_KEY = "delay";

    @NotNull Optional<ActionHolder> parse(final @NotNull Map<@NotNull String, @Nullable Object> actionData);
}
