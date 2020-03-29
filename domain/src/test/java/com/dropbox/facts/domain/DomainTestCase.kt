package com.dropbox.facts.domain

import org.junit.Before
import org.mockito.MockitoAnnotations

open class DomainTestCase {

    @Before
    @Throws(Exception::class)
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

}