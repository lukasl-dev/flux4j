package dev.lukasl.flux4j.store;

import dev.lukasl.flux4j.dispatch.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple/default implementation of {@link Store}.
 *
 * @param <S> the type of the state
 */
public class SimpleStore<S> implements Store<S> {
    /**
     * The {@link Reducer<S>} which applies the mutations to the state.
     */
    private final Reducer<S> reducer;

    /**
     * The registered {@link ActionListener<S>}s.
     */
    private final Collection<ActionListener<S>> listeners = new ArrayList<>();

    /**
     * The current state.
     */
    private @Nullable S state;

    /**
     * Initializes a new {@link SimpleStore<S>} and sets its initial state to
     * {@param state}. The {@param reducer} is used to mutate the state.
     *
     * @param state   the initial state
     * @param reducer the reducer which applies the mutations to the state
     */
    public SimpleStore(@Nullable S state, @NotNull Reducer<S> reducer) {
        this.state = state;
        this.reducer = reducer;
    }

    /**
     * Initializes a new {@link SimpleStore<S>} and sets the initial state to
     * null. The {@param reducer} is used to mutate the state.
     *
     * @param reducer the reducer which applies the mutations to the state
     */
    public SimpleStore(Reducer<S> reducer) {
        this(null, reducer);
    }

    /**
     * Gets the current state.
     *
     * @return the current state
     */
    @Override
    public @Nullable S getState() {
        return this.state;
    }

    /**
     * Mutates the {@link SimpleStore<S>}'s state using the {@param action}. The
     * state should only be mutated by a {@link dev.lukasl.flux4j.dispatch.Dispatcher<S>}.
     *
     * @param action the {@link Action} to apply
     */
    @Override
    public void mutate(@NotNull Action action) {
        this.state = this.reducer.reduce(this.state, action);
        this.listeners.forEach((listener) -> listener.onAction(this.state, action));
    }

    /**
     * Adds a {@link ActionListener<S>} to the event bus.
     *
     * @param listener the {@link ActionListener<S>} to add
     */
    @Override
    public void subscribe(@NotNull ActionListener<S> listener) {
        this.listeners.add(listener);
    }

    /**
     * Adds many {@link ActionListener<S>}'s to the event bus.
     *
     * @param listeners the {@link ActionListener<S>}s to add
     */
    @Override
    public void subscribeAll(@NotNull Collection<@NotNull ActionListener<S>> listeners) {
        listeners.forEach(this::subscribe);
    }
}
