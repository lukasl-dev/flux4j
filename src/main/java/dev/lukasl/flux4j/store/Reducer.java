package dev.lukasl.flux4j.store;

import dev.lukasl.flux4j.dispatch.Action;

/**
 * A {@link Reducer<S>} is used to mutate the state of a {@link Store<S>}.
 *
 * @param <S> the type of state
 */
@FunctionalInterface
public interface Reducer<S> {
    /**
     * Mutates the state of a {@link Store<S>} using the given {@link Action}.
     *
     * @param state  the current state
     * @param action the {@link Action} to be performed
     * @return the new mutated state
     */
    S reduce(S state, Action action);
}
