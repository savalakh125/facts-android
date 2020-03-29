package com.dropbox.facts.data.mapper

import com.dropbox.facts.data.carrier.APIError
import com.dropbox.facts.data.carrier.APIResult
import com.dropbox.facts.data.response.facts.APIFact
import com.dropbox.facts.data.response.facts.APIFacts
import org.junit.Assert.assertTrue
import org.junit.Test

class APIFactsMapperTest {

    @Test
    fun toDomainSuccessTest() {

        val data = APIResult(success = APIFacts("title", listOf(APIFact("title", "desc", "imageHref"))))

        val domain = APIFactsMapper().toDomain(data)

        assertTrue(domain.success != null)
        assertTrue(domain.error == null)

        assertTrue(domain.success?.title == data.success?.title)
        assertTrue(domain.success?.list?.isNotEmpty() == true)
        assertTrue(domain.success?.list?.size == data.success?.list?.size)
        assertTrue(domain.success?.list?.get(0)?.title == data.success?.list?.get(0)?.title)
        assertTrue(domain.success?.list?.get(0)?.description == data.success?.list?.get(0)?.description)
        assertTrue(domain.success?.list?.get(0)?.imageHref == data.success?.list?.get(0)?.imageHref)
    }

    @Test
    fun toDomainErrorTest() {

        val data = APIResult<APIFacts>(error = APIError("something went wrong"))

        val domain = APIFactsMapper().toDomain(data)

        assertTrue(domain.success == null)
        assertTrue(domain.error != null)

        assertTrue(domain.error?.message == data.error?.message)
    }

}