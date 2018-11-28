package io.api.bloxy.core.token

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TokenStatisticTests : Tester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.tokenStatistic(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].address)
        assertNotNull(result[0].circulating_supply)
        assertNotNull(result[0].decimals)
        assertNotNull(result[0].first_transfer)
        assertNotNull(result[0].name)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].holders_count)
        assertNotNull(result[0].latest_transfer)
        assertNotNull(result[0].receivers)
        assertNotNull(result[0].senders)
        assertNotNull(result[0].token_annotation)
        assertNotNull(result[0].transfered_amount)
        assertNotNull(result[0].transfers)
        assertNotNull(result[0].typeAsString)
        assertNotNull(result[0].tokenType)
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.tokenStatistic(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.tokenStatistic(contract)
    }
}