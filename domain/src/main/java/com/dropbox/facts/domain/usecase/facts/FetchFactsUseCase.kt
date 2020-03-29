package com.dropbox.facts.domain.usecase.facts

import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.repository.RemoteRepository
import com.dropbox.facts.domain.thread.ObserverThread
import com.dropbox.facts.domain.usecase.ObservableUseCase
import io.reactivex.Observable
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * The use-case to fetch the list of facts from the remote API.
 */
class FetchFactsUseCase @Inject internal constructor(observerThread: ObserverThread,
                                                     threadExecutor: Executor,
                                                     private val remoteRepository: RemoteRepository
)
                                    : ObservableUseCase<DResult<Facts>, Void>(observerThread, threadExecutor) {


    override fun constructObservable(params: Void?): Observable<DResult<Facts>> {
        return remoteRepository.facts()
    }


}