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
        assertNotNull(list[0].balance_eth)
        assertNotNull(list[0].last_tx_at)
        assertNotNull(list[0].first_tx_at)
        assertNotNull(list[0].levelAsString)
        assertNotNull(list[0].note)
        assertNotNull(list[0].receive_eth_amount)
        assertNotNull(list[0].receive_from_count)
        assertNotNull(list[0].receive_tx_count)
        assertNotNull(list[0].send_eth_amount)
        assertNotNull(list[0].send_to_count)
        assertNotNull(list[0].typeAsString)
        assertNotNull(list[0].addressType)
        assertNotNull(list[0].receive_from_currencies)
        assertNotNull(list[0].send_tx_count)
        assertNotNull(list[0].send_to_currencies)
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