package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class TradesActiveTests : BloxyTester() {

    @Test
    fun `valid empty params`() {
        val result = api.dex.tradesActive(emptyList())
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveFromTime())
        assertTrue(result[0].haveTillTime())
        assertNotNull(result[0].address)
        assertNotNull(result[0].addressAnnotation)
        assertNotNull(result[0].contractType)
        assertNotNull(result[0].currencies)
        assertNotNull(result[0].dexes)
        assertNotNull(result[0].fromTime)
        assertNotNull(result[0].fromTimeAsString)
        assertNotNull(result[0].makerTrades)
        assertNotNull(result[0].takerTrades)
        assertNotNull(result[0].tillTime)
        assertNotNull(result[0].tillTimeAsString)
        assertNotNull(result[0].typeAsString)
        assertNotNull(result[0].toString())
    }

    @Test
    fun `valid with protocol`() {
        val protocols = listOf("IDEX")
        val list = api.dex.tradesActive(protocols)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }

    @Test
    fun `valid with contracts`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.tradesActive(dexContracts = contracts, timeSpanDays = 800)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid dex contract`() {
        val contracts = listOf("0xa0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.tradesActive(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `empty dex contract not exist`() {
        val contracts = listOf("0x1a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.tradesActive(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.tradesActive(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid with protocol and contract`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val protocols = listOf("IDEX")
        val list = api.dex.tradesActive(protocols, contracts)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }
}