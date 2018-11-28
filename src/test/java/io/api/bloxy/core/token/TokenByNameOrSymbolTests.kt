package io.api.bloxy.core.token

import io.api.bloxy.core.Tester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TokenByNameOrSymbolTests : Tester() {

    @Test
    fun `valid token symbol`() {
        val symbol = "PAY"
        val result = api.token.tokenByNameOrSymbol(symbol)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].address)
        assertNotNull(result[0].created)
        assertNotNull(result[0].decimals)
        assertNotNull(result[0].latest_tx_as_string)
        assertNotNull(result[0].latest_tx)
        assertNotNull(result[0].name)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].transactions)
        assertNotNull(result[0].type_as_string)
        assertNotNull(result[0].tokenType)
    }

    @Test
    fun `valid token name`() {
        val name = "TenX"
        val result = api.token.tokenByNameOrSymbol(name)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `non exist token symbol empty result`() {
        val symbol = "PARIAPA"
        val result = api.token.tokenByNameOrSymbol(symbol)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }
}