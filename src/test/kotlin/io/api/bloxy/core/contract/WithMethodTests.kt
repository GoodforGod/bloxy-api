package io.api.bloxy.core.contract

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
class WithMethodTests : BloxyTester() {

    @Test
    fun valid() {
        val hash = "a9059cbb"
        val result = api.contract.withMethod(hash, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].name)
        mustValid(result[0].count)
        mustValid(result[0].signature)
        mayValid(result[0].annotation)
        mustValid(result[0].smartContract)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val hash = "a1059cbb"
        val result = api.contract.withMethod(hash)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val hash = "   "
        api.contract.withMethod(hash)
    }
}