package com.dropbox.facts

import org.junit.Before
import org.mockito.MockitoAnnotations

open class UITestCase {

    @Before
    @Throws(Exception::class)
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

}