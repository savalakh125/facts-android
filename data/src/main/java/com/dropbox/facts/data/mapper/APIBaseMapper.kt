package com.dropbox.facts.data.mapper

import com.dropbox.facts.data.carrier.APIResult
import com.dropbox.facts.domain.carrier.DError
import com.dropbox.facts.domain.carrier.DResult

/**
 *
 * Base API Mapper implementation to handle all common mappings
 * to DOMAIN layer.
 * And also to handle common error scenarios.
 *
 * Based on the current set of requirements,
 * it is assumed that all models shall conform to APIResult<DATA_MODEL>.
 *
 * [APIMapper] can be directly implemented for models which do not conform to the above.
 */
abstract class APIBaseMapper<DATA_MODEL, DOMAIN_MODEL> :
    APIMapper<APIResult<DATA_MODEL>, DResult<DOMAIN_MODEL>> {

    override fun toDomain(data: APIResult<DATA_MODEL>): DResult<DOMAIN_MODEL> {
        return when {
            data.success != null -> {
                DResult(modelToDomain(data.success))
            }
            //If success is NULL then it is assumed that there has been an error
            else -> DResult(error = DError(data.error?.message.orEmpty()))
        }
    }

    protected abstract fun modelToDomain(dataModel: DATA_MODEL) : DOMAIN_MODEL
}