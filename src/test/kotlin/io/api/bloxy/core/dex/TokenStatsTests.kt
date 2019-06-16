package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class TokenStatsTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.dex.tokenStats(contract, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].protocol)
        mustValid(result[0].smartContract)
        mustValid(result[0].symbol)
        mayValid(result[0].token)
        mustValid(result[0].trades)
        mustValid(result[0].volume)
        mustValid(result[0].toString())
    }

    @Test(expected = ParamException::class)
    fun `address not valid`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.dex.tokenStats(contract)
    }

    @Test
    fun `address not exist`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val list = api.dex.tokenStats(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}