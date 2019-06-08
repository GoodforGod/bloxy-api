package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TokenByNameOrSymbolTests : BloxyTester() {

    @Test
    fun `valid token symbol`() {
        val symbol = "PAY"
        val result = api.token.findToken(symbol)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isUnknown())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveLatestTxTime())
        mustValid(result[0].address)
        mustValid(result[0].created)
        mustValid(result[0].decimals)
        mustValid(result[0].latestTxAsString)
        mustValid(result[0].latestTx)
        mustValid(result[0].name)
        mustValid(result[0].symbol)
        mustValid(result[0].transactions)
        mustValid(result[0].typeAsString)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
    }

    @Test
    fun `valid token name`() {
        val name = "TenX"
        val result = api.token.findToken(name)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `non exist token symbol empty result`() {
        val symbol = "PARIAPA"
        val result = api.token.findToken(symbol)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }
}