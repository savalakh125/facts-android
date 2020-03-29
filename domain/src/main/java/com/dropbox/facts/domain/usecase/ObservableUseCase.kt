package com.dropbox.facts.domain.usecase

import com.dropbox.facts.domain.thread.ObserverThread
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

/**
 * Another generic class which inherits from [UseCase] and where the OBSERVABLE is
 * specifically of type [Observable]. This observable contains data defined by [MODEL]
 * and [PARAMS], is any.
 */
abstract class ObservableUseCase<MODEL, PARAMS>(observerThread: ObserverThread, threadExecutor: Executor) : UseCase<Observable<MODEL>, DisposableObserver<MODEL>, PARAMS>(observerThread, threadExecutor) {

    /**
     * Execute (with [PARAMS]) and observe via a [DisposableObserver] on the respective threads,
     * as defined in [ObserverThread] and [Executor]
     */
    override fun execute(observer: DisposableObserver<MODEL>, params: PARAMS?) {
        val observable = this.constructObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(observerThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }

}