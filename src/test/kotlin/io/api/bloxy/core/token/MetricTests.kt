package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
class MetricTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.metrics(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].date)
        mustValid(result[0].dateAsString)
        mustValid(result[0].gini)
        mustValid(result[0].gini10k)
        mustValid(result[0].holders)
        mustValid(result[0].supply)
        mustValid(result[0].supply)
        mustValid(result[0].index9)
        mustValid(result[0].index99)
        mustValid(result[0].index999)
        mustValid(result[0].nakamoto51)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.metrics(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.metrics(contract)
    }
}