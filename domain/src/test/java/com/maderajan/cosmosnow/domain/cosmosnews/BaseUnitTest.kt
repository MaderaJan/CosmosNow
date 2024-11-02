package com.maderajan.cosmosnow.domain.cosmosnews

import kotlinx.coroutines.test.TestScope
import org.junit.Before

abstract class BaseUnitTest {

    protected val testScope = TestScope()

    abstract fun setup()

    @Before
    fun init() {
        setup()
    }
}