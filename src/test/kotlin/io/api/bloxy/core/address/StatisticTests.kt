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
class StatisticTests : BloxyTester() {

    @Test
    fun valid() {
        val addresses =
            listOf("0x9eAb08daA285183F9A04269747D4125F08e634B0", "0x16f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val list = api.address.statistics(addresses)
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
        mustValid(list[0].receiveFromCurrencies)
        mayValid(list[0].receiveEthAmount)
        mayValid(list[0].receiveFromCount)
        mayValid(list[0].receiveTxCount)
        mustValid(list[0].sendToCurrencies)
        mayValid(list[0].sendEthAmount)
        mayValid(list[0].sendToCount)
        mayValid(list[0].sendTxCount)
        mustValid(list[0].addrTypeAsString)
        mustValid(list[0].addrType)
        mustValid(list[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val addresses =
            listOf("0x1eAb08daA285183F9A04269747D4125F08e634B0", "0x26f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val list = api.address.statistics(addresses)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val addresses = listOf("0x1eAb08daA285183F9A04269747D4125F08e634B0", "0x6f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        api.address.statistics(addresses)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.address.statistics(emptyList())
    }
}