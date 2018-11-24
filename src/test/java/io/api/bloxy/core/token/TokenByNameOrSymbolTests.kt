package io.api.bloxy.core.token

import io.api.bloxy.core.Tester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TokenByNameOrSymbolTests : Tester(){

    @Test
    fun `valid token symbol`() {
        val symbol = "PAY"
        val result = api.token().tokenByNameOrSymbol(symbol)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid token name`() {
        val name = "TenX"
        val result = api.token().tokenByNameOrSymbol(name)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `non exist token symbol empty result`() {
        val symbol = "PARIAPA"
        val result = api.token().tokenByNameOrSymbol(symbol)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }
}