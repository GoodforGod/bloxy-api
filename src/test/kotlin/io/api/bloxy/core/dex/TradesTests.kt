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
class TradesTests : BloxyTester() {

    @Test
    fun `valid empty params`() {
        val result = api.dex.trades(emptyList(), limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveTxTime())
        mustValid(result[0].amountBuy)
        mustValid(result[0].amountSell)
        mayValid(result[0].buyAddress)
        mustValid(result[0].buyCurrencyId)
        mayValid(result[0].buySymbol)
        mustValid(result[0].contractType)
        mustValid(result[0].maker)
        mayValid(result[0].makerFee)
        mayValid(result[0].makerAnnotation)
        mustValid(result[0].protocol)
        mayValid(result[0].sellAddress)
        mustValid(result[0].sellCurrencyId)
        mayValid(result[0].sellSymbol)
        mustValid(result[0].smartContractAddress)
        mustValid(result[0].smartContractId)
        mustValid(result[0].txHash)
        mustValid(result[0].txSender)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txTime)
        mustValid(result[0].taker)
        mayValid(result[0].takerAnnotation)
        mayValid(result[0].takerFee)
        mustValid(result[0].toString())
    }

    @Test
    fun `valid with protocol`() {
        val protocols = listOf("IDEX")
        val list = api.dex.trades(protocols)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }

    @Test
    fun `valid with contracts`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.trades(dexContracts = contracts, since = LocalDate.now().minusMonths(4))
        assertNotNull(list)
    }

    @Test
    fun `dex contract not exist empty result`() {
        val contracts = listOf("0x1a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.trades(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid dex contract`() {
        val contracts = listOf("0xa0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.trades(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.trades(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid with protocol and contract`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val protocols = listOf("IDEX")
        val list = api.dex.trades(protocols, contracts)
        assertNotNull(list)
    }
}