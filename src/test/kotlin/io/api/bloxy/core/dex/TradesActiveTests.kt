package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test
import java.time.LocalDate


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
        mustValid(result[0].haveFromTime())
        mustValid(result[0].haveTillTime())
        mustValid(result[0].address)
        mayValid(result[0].addressAnnotation)
        mayValid(result[0].contractType)
        mayValid(result[0].typeAsString)
        mustValid(result[0].currencies)
        mustValid(result[0].dexes)
        mustValid(result[0].fromTime)
        mustValid(result[0].fromTimeAsString)
        mustValid(result[0].makerTrades)
        mustValid(result[0].takerTrades)
        mustValid(result[0].tillTime)
        mustValid(result[0].tillTimeAsString)
        mustValid(result[0].toString())
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
        val list = api.dex.tradesActive(dexContracts = contracts, since = LocalDate.now().minusYears(1))
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