package com.dropbox.facts.domain.usecase.facts

import com.dropbox.facts.domain.DomainTestCase
import com.dropbox.facts.domain.carrier.DError
import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.repository.RemoteRepository
import com.dropbox.facts.domain.thread.ObserverThread
import io.reactivex.Observable
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*
import java.util.concurrent.Executor

class FetchFactsUseCaseTest : DomainTestCase() {

    @Mock
    private lateinit var repository: RemoteRepository
    @Mock
    private lateinit var observerThread: ObserverThread
    @Mock
    private lateinit var executor: Executor

    private lateinit var useCase: FetchFactsUseCase

    private val defaultResponse: DResult<Facts>
        get() {
            val list = ArrayList<Fact>()
            list.add(default)

            return DResult(Facts("title", list))
        }

    private val errorDataResponse: DResult<Facts>
        get() {
            return DResult(error = DError("something went wrong"))
        }

    private val default: Fact
        get() {
            return Fact("title", "desc", "href")
        }

    override fun setUp() {
        super.setUp()

        useCase = FetchFactsUseCase(observerThread, executor, repository)
    }

    @Test
    fun factsCompletionTest() {
        Mockito.`when`(repository.facts()).thenReturn(Observable.just(defaultResponse))

        val observer = useCase.constructObservable(null).test()

        observer.assertComplete()
    }

    @Test
    fun factsRepositoryTest() {
        Mockito.`when`(repository.facts()).thenReturn(Observable.just(defaultResponse))

        useCase.constructObservable(null).test()

        Mockito.verify(repository).facts()
    }

    @Test
    fun factsDataValidationTest() {
        val response = defaultResponse
        Mockito.`when`(repository.facts()).thenReturn(Observable.just(response))

        val observer = useCase.constructObservable(null).test()

        observer.assertValue(response)
    }

    @Test
    fun factsHasErrorTest() {
        Mockito.`when`(repository.facts()).thenReturn(Observable.just(errorDataResponse))

        val observer = useCase.constructObservable(null).test()

        val result = observer.values()[0]
        assertTrue(result.success == null)
        assertTrue(result.error != null)
        assertTrue(result.error?.message == errorDataResponse.error?.message)
    }

}