package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class TradesByHashTests : BloxyTester() {

    @Test
    fun valid() {
        val txHash = "0x91b4e41fa027ddec7e913ba1a49a1d17863c836ddd99ccfbff13701bbc94587f"
        val result = api.dex.tradesByHash(txHash)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].protocol)
        mustValid(result[0].amountBuy)
        mustValid(result[0].amountSell)
        mustValid(result[0].buyAddress)
        mustValid(result[0].buyCurrencyId)
        mustValid(result[0].buySymbol)
        mustValid(result[0].contractType)
        mustValid(result[0].maker)
        mayValid(result[0].makerAnnotation)
        mayValid(result[0].makerFee)
        mustValid(result[0].protocol)
        mustValid(result[0].sellAddress)
        mustValid(result[0].sellCurrencyId)
        mustValid(result[0].sellSymbol)
        mustValid(result[0].smartContractAddress)
        mustValid(result[0].smartContractId)
        mustValid(result[0].taker)
        mayValid(result[0].takerAnnotation)
        mayValid(result[0].takerFee)
        mustValid(result[0].txHash)
        mustValid(result[0].txSender)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].toString())
    }

    @Test(expected = ParamException::class)
    fun `hash not valid`() {
        val txHash = "0x1b4e41fa027ddec7e913ba1a49a1d17863c836ddd99ccfbff13701bbc94587f"
        api.dex.tradesByHash(txHash)
    }

    @Test
    fun `hash not exist`() {
        val txHash = "0x11b4e41fa027ddec7e913ba1a49a1d17863c836ddd99ccfbff13701bbc94587f"
        val list = api.dex.tradesByHash(txHash)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}