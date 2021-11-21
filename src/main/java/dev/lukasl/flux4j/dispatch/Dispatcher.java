package dev.lukasl.flux4j.dispatch;

import dev.lukasl.flux4j.store.Store;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * The {@link Dispatcher<S>} is used to dispatch actions to its {@link Store<S>}
 * to mutate its state.
 *
 * @param <S> the type of the {@link Store<S>}'s state
 */
public interface Dispatcher<S> {
    /**
     * Gets the {@link Store<S>} to be mutated.
     *
     * @return the {@link Store<S>} to be mutated
     */
    @NotNull Store<S> getStore();

    /**
     * Dispatches an {@param action} to the {@link Store<S>} to mutate its state.
     *
     * @param action the {@link Action} to be performed
     */
    void dispatch(@NotNull Action action);

    /**
     * Dispatches many {@param actions} to the {@link Store<S>} to mutate its state.
     *
     * @param actions the {@link Action}s to be performed
     */
    void dispatchAll(@NotNull Collection<@NotNull Action> actions);
}
