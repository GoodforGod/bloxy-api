package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class HolderSimilarTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.holderSimilar(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].commonHolders)
        mustValid(result[0].percentage)
        mustValid(result[0].symbol)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.holderSimilar(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.holderSimilar(contract)
    }
}