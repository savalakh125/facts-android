package com.dropbox.facts.data.mapper

import com.dropbox.facts.data.carrier.APIResult
import com.dropbox.facts.data.response.facts.APIFacts
import com.dropbox.facts.domain.carrier.DResult
import javax.inject.Inject

/**
 * Factory which provides the respective implementation
 * of [APIMapper] based on the DOMAIN model type to be mapped
 * from the DATA layer model
 */
class DataModelMapperFactory @Inject constructor() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    fun <DATA, DOMAIN> create(dataModelType: Class<DATA>) : APIMapper<APIResult<DATA>, DResult<DOMAIN>> {

        val mapper: APIMapper<*, *> = when (dataModelType) {

            APIFacts::class.java -> APIFactsMapper()

            else -> throw IllegalArgumentException("API Data model class type unknown: $dataModelType")
        }

        return mapper as APIMapper<APIResult<DATA>, DResult<DOMAIN>>
    }

}