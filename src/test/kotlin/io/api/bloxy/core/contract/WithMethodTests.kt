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
        val result = api.contract.withMethod(hash)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].name)
        assertNotNull(result[0].count)
        assertNotNull(result[0].signature)
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].smart_contract)
        assertNotNull(result[0].toString())
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