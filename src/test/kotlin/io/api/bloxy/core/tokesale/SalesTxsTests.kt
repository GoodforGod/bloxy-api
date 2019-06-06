package io.api.bloxy.core.tokesale

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class SalesTxsTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.tokenSale.saleTxs(limit = 3)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTxTime())
        mustValid(result[0].ethAmount)
        mustValid(result[0].symbol)
        mustValid(result[0].tokenAddress)
        mayValid(result[0].etherReceiverAnnotation)
        mayValid(result[0].tokenBuyerAnnotation)
        mayValid(result[0].tokenSenderAnnotation)
        mustValid(result[0].tokenAmount)
        mustValid(result[0].etherReceiver)
        mustValid(result[0].gasPrice)
        mustValid(result[0].gasValue)
        mustValid(result[0].tokenAmount)
        mustValid(result[0].tokenAddress)
        mustValid(result[0].tokenBuyer)
        mustValid(result[0].tokenSender)
        mustValid(result[0].txHash)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].typeAsString)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
    }

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
        if (!sale.isEmpty()) {
            val contracts = listOf(sale)
            val result = api.tokenSale.saleTxs(contracts)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
        }
    }

    @Test
    fun `non exist address empty result`() {
        val contracts = listOf("0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d")
        val result = api.tokenSale.saleTxs(contracts)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d")
        api.tokenSale.sales(contracts)
    }
}