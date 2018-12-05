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
        assertNotNull(result[0].ethAmount)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].tokenAddress)
        assertNotNull(result[0].etherReceiverAnnotation)
        assertNotNull(result[0].tokenBuyerAnnotation)
        assertNotNull(result[0].tokenSenderAnnotation)
        assertNotNull(result[0].tokenAmount)
        assertNotNull(result[0].etherReceiver)
        assertNotNull(result[0].gasPrice)
        assertNotNull(result[0].gasValue)
        assertNotNull(result[0].tokenAmount)
        assertNotNull(result[0].tokenAddress)
        assertNotNull(result[0].tokenBuyer)
        assertNotNull(result[0].tokenSender)
        assertNotNull(result[0].txHash)
        assertNotNull(result[0].txTime)
        assertNotNull(result[0].txTimeAsString)
        assertNotNull(result[0].typeAsString)
        assertNotNull(result[0].tokenType)
        assertNotNull(result[0].toString())
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