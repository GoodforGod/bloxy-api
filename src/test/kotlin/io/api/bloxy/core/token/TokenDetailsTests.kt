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
class TokenDetailsTests : BloxyTester() {

    @Test
    fun valid() {
        val contracts = listOf("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token.details(contracts)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].decimals)
        mustValid(result[0].name)
        mustValid(result[0].symbol)
        mustValid(result[0].typeAsString)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contracts = listOf("0xB17048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token.details(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x97048628DB6B661D4C2aA833e95Dbe1A905B280")
        api.token.details(contracts)
    }
}