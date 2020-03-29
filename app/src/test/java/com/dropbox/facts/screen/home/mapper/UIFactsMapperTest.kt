package com.dropbox.facts.screen.home.mapper

import com.dropbox.facts.domain.carrier.DError
import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.usecase.facts.Fact
import com.dropbox.facts.domain.usecase.facts.Facts
import org.junit.Assert.assertTrue
import org.junit.Test

class UIFactsMapperTest {

    @Test
    fun fromDomainSuccessTest() {

        val domain = DResult(success = Facts("title", listOf(Fact("title", "desc", "imageHref"))))

        val ui = UIFactsMapper().fromDomain(domain)

        assertTrue(ui.data != null)
        assertTrue(ui.error == null)

        assertTrue(ui.data?.title == domain.success?.title)
        assertTrue(!ui.data?.facts.isNullOrEmpty())
        assertTrue(ui.data?.facts?.size == 1)
        assertTrue(ui.data?.facts?.get(0)?.body == domain.success?.list?.get(0)?.description)
        assertTrue(ui.data?.facts?.get(0)?.title == domain.success?.list?.get(0)?.title)
        assertTrue(ui.data?.facts?.get(0)?.image == domain.success?.list?.get(0)?.imageHref)

    }

    @Test
    fun fromDomainErrorTest() {

        val domain = DResult<Facts>(error = DError("something went wrong"))

        val ui = UIFactsMapper().fromDomain(domain)

        assertTrue(ui.data == null)
        assertTrue(ui.error != null)

        assertTrue(ui.error?.message == domain.error?.message)
    }

}