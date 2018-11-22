package io.api.bloxy.core.address

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class BalanceTests : Tester(){

    @Test
    fun valid() {
        val address = "0x9eAb08daA285183F9A04269747D4125F08e634B0"
        val balance = api.address().balance(address)
        assertNotNull(balance)
        assertFalse(balance.isEmpty())
        assertFalse(balance.getAll()[0].isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0x1eAb08daA285183F9A04269747D4125F08e634B0"
        val balance = api.address().balance(address)
        assertNotNull(balance)
        assertTrue(balance.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val address = "01eAb08daA285183F9A04269747D4125F08e634B0"
        val balance = api.address().balance(address)
    }
}