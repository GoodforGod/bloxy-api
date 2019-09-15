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
class AddrStatisticsTests : BloxyTester() {

    @Test
    fun valid() {
        val addresses = listOf("0x9eAb08daA285183F9A04269747D4125F08e634B0", "0x16f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val list = api.maltego.addrStatistics(addresses)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].address)
        mayValid(list[0].annotation)
        mustValid(list[0].balanceEth)
        mustValid(list[0].lastTxAt)
        mustValid(list[0].firstTxAt)
        mustValid(list[0].levelAsString)
        mayValid(list[0].note)
        mustValid(list[0].receiveEthAmount)
        mustValid(list[0].receiveFromCount)
        mustValid(list[0].receiveTxCount)
        mayValid(list[0].sendEthAmount)
        mustValid(list[0].sendToCount)
        mustValid(list[0].addrTypeAsString)
        mustValid(list[0].addrType)
        mustValid(list[0].receiveFromCurrencies)
        mustValid(list[0].sendTxCount)
        mustValid(list[0].sendToCurrencies)
        mustValid(list[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val addresses = listOf("0x2eAb08daA285183F9A04269747D4125F08e634B0", "0x36f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val list = api.maltego.addrStatistics(addresses)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val addresses = listOf("0x1eAb08daA285183F9A04269747D4125F08e634B0", "0x6f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        api.maltego.addrStatistics(addresses)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.maltego.addrStatistics(emptyList())
    }
}