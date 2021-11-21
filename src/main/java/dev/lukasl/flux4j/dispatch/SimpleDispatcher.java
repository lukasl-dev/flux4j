package dev.lukasl.flux4j.dispatch;

import dev.lukasl.flux4j.store.Store;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * A simple/default implementation of {@link Dispatcher}.
 *
 * @param <S> the type of the state
 */
public class SimpleDispatcher<S> implements Dispatcher<S> {
    /**
     * The {@link Store<S>} to dispatch to.
     */
    @Getter
    private final Store<S> store;

    /**
     * Initializes a {@link SimpleDispatcher<S>} which dispatches actions
     * to the {@param store}
     *
     * @param store the {@link Store<S>} to dispatch actions to
     */
    public SimpleDispatcher(Store<S> store) {
        this.store = store;
    }

    /**
     * Dispatches an {@param action} to the {@link Store<S>} directly to mutate
     * its state.
     *
     * @param action the {@link Action} to be performed
     */
    @Override
    public void dispatch(@NotNull Action action) {
        this.store.mutate(action);
    }

    /**
     * Dispatches many {@param action}s to the {@link Store<S>} directly to
     * mutate its state.
     *
     * @param actions the {@link Action}s to be performed
     */
    @Override
    public void dispatchAll(@NotNull Collection<@NotNull Action> actions) {
        actions.forEach(this::dispatch);
    }
}
