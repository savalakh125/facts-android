package com.dropbox.facts.domain.usecase

import com.dropbox.facts.domain.thread.ObserverThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.Executor

/**
 * The generic base class which is used to define a use-case in the DOMAIN Layer.
 * NOTE: Currently there is only one use-case but this generic class has been added with the main to make it
 * future ready.
 *
 * OBSERVABLE - The observable where the operations take place within the use-case
 * OBSERVER - The observer who shall be notified of results of the use-case streams
 * PARAMS - Any parameters which are required for the execution of the respective use-case.
 */
abstract class UseCase<OBSERVABLE, OBSERVER, PARAMS> internal constructor(protected val observerThread: ObserverThread,
                                                                          protected val threadExecutor: Executor) {

    private val disposables = CompositeDisposable()

    /**
     * Execute a use-case with PARAMS, if any.
     * Receive the results in the OBSERVER.
     */
    abstract fun execute(observer: OBSERVER, params: PARAMS?)

    internal abstract fun constructObservable(params: PARAMS?): OBSERVABLE

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    /*
        TODO:
        A mechanism where the observable is accessible by the consumer/caller.
        And then it can be fed into the use-case for a threaded execution.

        Access to the observable would help with background threaded data handling by the consumer.
        Currently the observable is created when the API request execution starts.
        This means that any processing done on any data (in DOMAIN and/or others layers) before API execution
        begins, happens on the main thread.

        Ideally everything should happen on a background thread.
     */

}