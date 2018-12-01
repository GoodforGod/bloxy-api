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
class TradesTests : BloxyTester() {

    @Test
    fun `valid empty params`() {
        val result = api.dex.trades(emptyList())
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTxTime())
        assertNotNull(result[0].amountBuy)
        assertNotNull(result[0].amountSell)
        assertNotNull(result[0].buyAddress)
        assertNotNull(result[0].buyCurrencyId)
        assertNotNull(result[0].buySymbol)
        assertNotNull(result[0].contractType)
        assertNotNull(result[0].maker)
        assertNotNull(result[0].makerFee)
        assertNotNull(result[0].makerAnnotation)
        assertNotNull(result[0].protocol)
        assertNotNull(result[0].sellAddress)
        assertNotNull(result[0].sellCurrencyId)
        assertNotNull(result[0].sellSymbol)
        assertNotNull(result[0].smartContractAddress)
        assertNotNull(result[0].smartContractId)
        assertNotNull(result[0].txHash)
        assertNotNull(result[0].txSender)
        assertNotNull(result[0].txTimeAsString)
        assertNotNull(result[0].txDateAsString)
        assertNotNull(result[0].txTime)
        assertNotNull(result[0].taker)
        assertNotNull(result[0].takerAnnotation)
        assertNotNull(result[0].takerFee)
        assertNotNull(result[0].toString())
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
        val list = api.dex.trades(dexContracts = contracts, timeSpanDays = 40)
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