package com.dropbox.facts.data.mapper

import com.dropbox.facts.data.response.facts.APIFact
import com.dropbox.facts.data.response.facts.APIFacts
import com.dropbox.facts.domain.usecase.facts.Fact
import com.dropbox.facts.domain.usecase.facts.Facts
import org.junit.Assert.assertEquals
import org.junit.Test

class DataModelMapperFactoryTest {


    @Test
    fun factsMapperTest() {
        assertEquals(
            "The mapper instance returned is not of type: " + APIFactsMapper::class.java,
            DataModelMapperFactory().create<APIFacts, Facts>(APIFacts::class.java).javaClass,
            APIFactsMapper::class.java
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun noMapperFoundTest() {
        DataModelMapperFactory().create<APIFact, Fact>(APIFact::class.java).javaClass
    }


}