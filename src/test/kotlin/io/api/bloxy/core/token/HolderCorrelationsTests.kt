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
class HolderCorrelationsTests : BloxyTester() {

    @Test
    fun valid() {
        val contracts = listOf("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280", "0x744d70FDBE2Ba4CF95131626614a1763DF805B9E")
        val result = api.token.holderCorrelations(contracts)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].addresses)
        mustValid(result[0].symbols)
        mustValid(result[0].transferToUniques)
        mustValid(result[0].toString())
    }

    @Test
    fun `single contract empty correlation`() {
        val contracts = listOf("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token.holderCorrelations(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val contracts = listOf("0xB17048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token.holderCorrelations(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x97048628DB6B661D4C2aA833e95Dbe1A905B280")
        api.token.holderCorrelations(contracts)
    }
}