package dev.lukasl.flux4j.store;

import dev.lukasl.flux4j.dispatch.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * The {@link Store} is a central system which holds the current state and mutates
 * it using {@link Action}s.
 *
 * @param <S> the type of the state
 */
public interface Store<S> {
    /**
     * Gets the current state.
     *
     * @return the current state
     */
    @Nullable S getState();

    /**
     * Mutates the {@link Store<S>}'s state using the {@param action}.
     *
     * @param action the {@link Action} to apply
     */
    void mutate(@NotNull Action action);

    /**
     * Adds a {@link ActionListener<S>} to the event bus.
     *
     * @param listener the {@link ActionListener<S>} to add
     */
    void subscribe(@NotNull ActionListener<S> listener);

    /**
     * Adds many {@link ActionListener<S>}'s to the event bus.
     *
     * @param listeners the {@link ActionListener<S>}s to add
     */
    void subscribeAll(@NotNull Collection<@NotNull ActionListener<S>> listeners);
}
