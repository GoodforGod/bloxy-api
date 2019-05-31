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
        val result = api.tokenSale.saleTxs()
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTxTime())
        ifValid(result[0].ethAmount)
        ifValid(result[0].symbol)
        ifValid(result[0].tokenAddress)
        ifValid(result[0].etherReceiverAnnotation)
        ifValid(result[0].tokenBuyerAnnotation)
        ifValid(result[0].tokenSenderAnnotation)
        ifValid(result[0].tokenAmount)
        ifValid(result[0].etherReceiver)
        ifValid(result[0].gasPrice)
        ifValid(result[0].gasValue)
        ifValid(result[0].tokenAmount)
        ifValid(result[0].tokenAddress)
        ifValid(result[0].tokenBuyer)
        ifValid(result[0].tokenSender)
        ifValid(result[0].txHash)
        ifValid(result[0].txTime)
        ifValid(result[0].txTimeAsString)
        ifValid(result[0].typeAsString)
        ifValid(result[0].tokenType)
        ifValid(result[0].toString())
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