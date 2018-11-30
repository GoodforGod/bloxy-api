package io.api.bloxy.core.address

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class CorrelatedTests : BloxyTester() {

    @Test
    fun valid() {
        val address = "0x9eAb08daA285183F9A04269747D4125F08e634B0"
        val list = api.address.correlated(address)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        assertNotNull(list[0].address)
        assertNotNull(list[0].symbol)
        assertNotNull(list[0].likelihood)
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0x1eAb08daA285183F9A04269747D4125F08e634B0"
        val details = api.address.correlated(address)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val address = "0xeAb08daA285183F9A04269747D4125F08e634B0"
        api.address.correlated(address)
    }
}