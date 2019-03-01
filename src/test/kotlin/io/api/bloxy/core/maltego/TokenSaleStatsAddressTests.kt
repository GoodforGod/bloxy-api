package io.api.bloxy.core.maltego

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.core.tokesale.SalesTests
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
class TokenSaleStatsAddressTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
        if (!sale.isEmpty()) {
            val result = api.maltego.tokenSaleStatsAddress(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertTrue(result[0].haveFromTime())
            assertTrue(result[0].haveTillTime())
            assertNotNull(result[0].ethAmount)
            assertNotNull(result[0].etherReceiver)
            assertNotNull(result[0].fromTimeAsString)
            assertNotNull(result[0].fromTime)
            assertNotNull(result[0].tillTimeAsString)
            assertNotNull(result[0].tillTime)
            assertNotNull(result[0].etherReceiverAnnotation)
            assertNotNull(result[0].tokenSenderAnnotation)
            assertNotNull(result[0].etherReceiverAnnotation)
            assertNotNull(result[0].etherReceiverAnnotation)
            assertNotNull(result[0].tokenBuyers)
            assertNotNull(result[0].transactions)
            assertNotNull(result[0].tokenBuyers)
            assertNotNull(result[0].tokenAmount)
            assertNotNull(result[0].tokenSender)
            assertNotNull(result[0].toString())
        }
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.maltego.tokenSaleStatsAddress(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.maltego.tokenSaleStatsAddress(contract)
    }
}