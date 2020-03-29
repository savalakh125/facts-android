package com.dropbox.facts.data

import com.dropbox.facts.data.carrier.APIError
import com.dropbox.facts.data.carrier.APIResult
import com.dropbox.facts.data.client.DropboxAPI
import com.dropbox.facts.data.mapper.DataModelMapperFactory
import com.dropbox.facts.data.response.facts.APIFacts
import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.repository.RemoteRepository
import com.dropbox.facts.domain.usecase.facts.Facts
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Implementation of [RemoteRepository] and helps fetch data from the remote sever
 * via [DropboxAPI].
 * The data is then mapped into DOMAIN layer models via mappers that are provided through [DataModelMapperFactory]
 */
class APIRemoteRepository @Inject constructor(private val api: DropboxAPI,
                                              private val dataMapperFactory: DataModelMapperFactory) : RemoteRepository {

    override fun facts(): Observable<DResult<Facts>> {
        return api.facts().doOnError {
            APIError(it.message.orEmpty())
        }.map {
            dataMapperFactory.create<APIFacts, Facts>(APIFacts::class.java).toDomain(APIResult(it))
        }
    }
}