package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.error.BloxyException
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.11.2018
 */
class BloxyTests {

    @Test(expected = ParamException::class)
    fun `bloxy key empty`() {
        BloxyApi("")
    }

    @Test(expected = ParamException::class)
    fun `bloxy key blank`() {
        BloxyApi("       ")
    }

    @Test(expected = BloxyException::class)
    fun `bloxy invalid key`() {
        val api = BloxyApi("BOB")
        api.address.balance("0x9eAb08daA285183F9A04269747D4125F08e634B0")
    }
}