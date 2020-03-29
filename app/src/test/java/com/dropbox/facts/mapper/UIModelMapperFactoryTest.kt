package com.dropbox.facts.mapper

import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.usecase.facts.Facts
import com.dropbox.facts.screen.home.mapper.UIFactsMapper
import com.dropbox.facts.screen.home.model.UIFactItem
import com.dropbox.facts.screen.home.model.UIFacts
import org.junit.Assert.assertEquals
import org.junit.Test

class UIModelMapperFactoryTest {


    @Test
    fun factsMapperTest() {
        assertEquals(
            "The mapper instance returned is not of type: " + UIFactsMapper::class.java,
            UIModelMapperFactory().create<UIFacts, DResult<Facts>>(UIFacts::class.java).javaClass,
            UIFactsMapper::class.java
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun noMapperFoundTest() {
        UIModelMapperFactory().create<UIFactItem, DResult<Facts>>(UIFactItem::class.java).javaClass
    }


}