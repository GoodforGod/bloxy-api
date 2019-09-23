package io.api.bloxy.core.maltego

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
class AddrBalanceTests : BloxyTester(){

    @Test
    fun valid() {
        val address = "0x9eAb08daA285183F9A04269747D4125F08e634B0"
        val balance = api.maltego.addrBalance(address)
        assertNotNull(balance)
        assertFalse(balance.isEmpty())
        assertFalse(balance.getAll()[0].isEmpty())
        mustValid(balance.getAll()[0].balance)
        mustValid(balance.getAll()[0].receivedAmount)
        mustValid(balance.getAll()[0].receivedTxs)
        mustValid(balance.getAll()[0].sentAmount)
        mustValid(balance.getAll()[0].sentTxs)
        mustValid(balance.getAll()[0].symbol)
        mustValid(balance.getAll()[0].tokenAddress)
        mustValid(balance.get("ETH"))
        mustValid(balance.getEth())
        mustValid(balance.exist("ETH"))
        mustValid(balance.getAll().any { it.isEth() })

        assertFalse(balance.getSendOnly().isEmpty())
        assertFalse(balance.getTopTenReceived().isEmpty())
        assertFalse(balance.getTopTenSend().isEmpty())

        mustValid(balance.toString())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0x1eAb08daA285183F9A04269747D4125F08e634B0"
        val balance = api.maltego.addrBalance(address)
        assertNotNull(balance)
        assertTrue(balance.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val address = "01eAb08daA285183F9A04269747D4125F08e634B0"
        api.maltego.addrBalance(address)
    }
}