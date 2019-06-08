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
        val result = api.dex.contracts(protocols, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveTradeTime())
        mustValid(result[0].annotation)
        mustValid(result[0].latestTradeAsString)
        mustValid(result[0].latestTrade)
        mustValid(result[0].protocol)
        mustValid(result[0].protocol)
        mustValid(result[0].smartContractAddress)
        mustValid(result[0].trades)
        mustValid(result[0].toString())
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