package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.BloxyException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class ContractsTests : BloxyTester() {

    @Test
    fun valid() {
        val protocols = listOf("IDEX")
        val result = api.dex.contracts(protocols)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTradeTime())
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].latestTradeAsString)
        assertNotNull(result[0].latestTrade)
        assertNotNull(result[0].protocol)
        assertNotNull(result[0].protocol)
        assertNotNull(result[0].smartContractAddress)
        assertNotNull(result[0].trades)
        assertNotNull(result[0].toString())
    }

    @Test(expected = BloxyException::class)
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.contracts(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid empty params`() {
        val list = api.dex.contracts(emptyList())
        assertNotNull(list)
        assertFalse(list.isEmpty())
    }
}