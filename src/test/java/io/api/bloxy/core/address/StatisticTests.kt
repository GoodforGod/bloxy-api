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
class StatisticTests : Tester() {

    @Test
    fun valid() {
        val addresses =
            listOf("0x9eAb08daA285183F9A04269747D4125F08e634B0", "0x16f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val list = api.address.statistics(addresses)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        assertNotNull(list[0].address)
        assertNotNull(list[0].annotation)
        assertNotNull(list[0].balanceEth)
        assertNotNull(list[0].lastTxAt)
        assertNotNull(list[0].firstTxAt)
        assertNotNull(list[0].levelAsString)
        assertNotNull(list[0].note)
        assertNotNull(list[0].receiveEthAmount)
        assertNotNull(list[0].receiveFromCount)
        assertNotNull(list[0].receiveTxCount)
        assertNotNull(list[0].sendEthAmount)
        assertNotNull(list[0].sendToCount)
        assertNotNull(list[0].addrTypeAsString)
        assertNotNull(list[0].addrType)
        assertNotNull(list[0].receiveFromCurrencies)
        assertNotNull(list[0].sendTxCount)
        assertNotNull(list[0].sendToCurrencies)
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