package dev.lukasl.flux4j.store;

import dev.lukasl.flux4j.dispatch.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link ActionListener<S>} is used as a {@link FunctionalInterface} in the {@link Store<S>}.
 *
 * @param <S> the type of the state
 */
@FunctionalInterface
public interface ActionListener<S> {
    /**
     * Is called whenever a {@link Action} caused a mutation in a {@link Store<S>}.
     *
     * @param state  the new mutated state
     * @param action the {@link Action} that caused the mutation
     */
    void onAction(@Nullable S state, @NotNull Action action);
}
