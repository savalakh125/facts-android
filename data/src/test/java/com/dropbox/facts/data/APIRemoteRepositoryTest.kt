package com.dropbox.facts.data

import com.dropbox.facts.data.carrier.APIError
import com.dropbox.facts.data.carrier.APIResult
import com.dropbox.facts.data.client.DropboxAPI
import com.dropbox.facts.data.mapper.DataModelMapperFactory
import com.dropbox.facts.data.response.facts.APIFact
import com.dropbox.facts.data.response.facts.APIFacts
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class APIRemoteRepositoryTest {


    @Mock
    private lateinit var api: DropboxAPI

    private lateinit var dataModelMapperFactory: DataModelMapperFactory

    private lateinit var apiRemoteRepository: APIRemoteRepository

    private val defaultResponse: APIResult<APIFacts>
        get() {
            val list = ArrayList<APIFact>()
            list.add(default)

            return APIResult(APIFacts("title", list))
        }

    private val errorDataResponse: APIResult<APIFacts>
        get() {
            return APIResult(error = APIError("something went wrong"))
        }

    private val default: APIFact
        get() {
            return APIFact("title", "desc", "href")
        }

    private val defaultFacts: APIFacts
        get() {
            return APIFacts("title", listOf(default))
        }


    @Before
    @Throws(IllegalArgumentException::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)

        dataModelMapperFactory = DataModelMapperFactory()
        apiRemoteRepository = APIRemoteRepository(api, dataModelMapperFactory)

    }

    @Test
    fun charactersAPITest() {
        Mockito.`when`(api.facts()).thenReturn(Observable.just(defaultFacts))

        apiRemoteRepository.facts()

        Mockito.verify(api).facts()

    }

    @Test
    fun charactersCompletionTest() {

        Mockito.`when`(api.facts()).thenReturn(Observable.just(defaultFacts))

        apiRemoteRepository.facts().test().assertComplete()
        apiRemoteRepository.facts().test().assertValueCount(1)

    }


}