package dev.lukasl.flux4j.dispatch;

import dev.lukasl.flux4j.store.Store;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

/**
 * The {@link AsynchronousDispatcher} is a {@link Dispatcher}
 * implementation that dispatches actions asynchronously using its
 * {@link #executorService}.
 *
 * @param <S> the type of the state
 */
public class AsynchronousDispatcher<S> extends SimpleDispatcher<S> {
    /**
     * The executor service to use for dispatching actions.
     */
    private final ExecutorService executorService;

    /**
     * Initializes a new {@link AsynchronousDispatcher<S>} which dispatches
     * actions to the {@param store} asynchronously using the
     * {@param executorService}.
     *
     * @param store           the store to dispatch actions to
     * @param executorService the executor service to use for dispatching
     */
    public AsynchronousDispatcher(Store<S> store, ExecutorService executorService) {
        super(store);
        this.executorService = executorService;
    }

    /**
     * Dispatches an {@param action} to the {@link Store<S>} asynchronously
     * using the {@link #executorService} to mutate its state.
     *
     * @param action the {@link Action} to be performed
     */
    @Override
    public void dispatch(@NotNull Action action) {
        this.executorService.submit(() -> super.dispatch(action));
    }

    /**
     * Dispatches many {@param action}s to the {@link Store<S>} asynchronously
     * using the {@link #executorService} to mutate its state.
     *
     * @param actions the {@link Action}s to be performed
     */
    @Override
    public void dispatchAll(@NotNull Collection<@NotNull Action> actions) {
        this.executorService.submit(() -> actions.forEach(super::dispatch));
    }
}
