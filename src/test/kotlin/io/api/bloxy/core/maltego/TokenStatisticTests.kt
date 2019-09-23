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
class TokenStatisticTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.maltego.tokenStatistic(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].circulatingSupply)
        mustValid(result[0].decimals)
        mayValid(result[0].firstTransferAsString)
        mustValid(result[0].name)
        mustValid(result[0].symbol)
        mustValid(result[0].holdersCount)
        mayValid(result[0].latestTransferAsString)
        mustValid(result[0].receivers)
        mustValid(result[0].senders)
        mayValid(result[0].tokenAnnotation)
        mustValid(result[0].transferedAmount)
        mustValid(result[0].transfers)
        mustValid(result[0].typeAsString)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
        if (result[0].latestTransferAsString.isNotEmpty()) {
            mustValid(result[0].haveLastTransferTime())
            mustValid(result[0].latestTransfer)
        }
        if (result[0].firstTransferAsString.isNotEmpty()) {
            mustValid(result[0].haveFirstTransferTime())
            mustValid(result[0].firstTransfer)
        }
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.maltego.tokenStatistic(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.maltego.tokenStatistic(contract)
    }
}