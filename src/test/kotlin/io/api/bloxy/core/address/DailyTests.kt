package io.api.bloxy.core.address

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.06.2019
 */
class DailyTests : BloxyTester() {

    @Test
    fun valid() {
        val address = "0x9eAb08daA285183F9A04269747D4125F08e634B0"
        val list = api.address.daily(address)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mayValid(list[0].change)
        mayValid(list[0].symbol)
        mustValid(list[0].dailyBalance)
        mustValid(list[0].dailyPrice)
        mustValid(list[0].dailyProfit)
        mustValid(list[0].dailyValue)
        mustValid(list[0].date)
        mustValid(list[0].dateAsString)
        mustValid(list[0].deposit)
        mustValid(list[0].depositValue)
        mustValid(list[0].deposited)
        mayValid(list[0].inDailyValue)
        mustValid(list[0].profit)
        mustValid(list[0].profitLifetime)
        mayValid(list[0].realizedGain)
        mustValid(list[0].roiDaily)
        mayValid(list[0].tokenAddress)
        mustValid(list[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0x1eAb08daA285183F9A04269747D4125F08e634B0"
        val details = api.address.daily(address)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val address = "0xeAb08daA285183F9A04269747D4125F08e634B0"
        api.address.daily(address)
    }
}