package at.helpch.ea.parser;

import at.helpch.ea.action.ActionHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ActionParser {
    @NotNull Optional<ActionHolder> parse(final @NotNull Object actionData);
}
