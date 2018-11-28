package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.error.BloxyException
import io.api.bloxy.error.HttpException
import io.api.bloxy.executor.impl.HttpClient
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.11.2018
 */
class BloxyTests {

    @Test(expected = BloxyException::class)
    fun `bloxy key empty`() {
        BloxyApi("")
    }

    @Test(expected = BloxyException::class)
    fun `bloxy key blank`() {
        BloxyApi("       ")
    }

    @Test(expected = HttpException::class)
    fun `invalid url`() {
        HttpClient().get("hdrhd")
    }
}