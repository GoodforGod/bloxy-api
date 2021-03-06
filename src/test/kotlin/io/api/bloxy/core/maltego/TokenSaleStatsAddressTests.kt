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
        val result = api.maltego.tokenSaleStatsAddress(sale)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveFromTime())
        mustValid(result[0].haveTillTime())
        mustValid(result[0].ethAmount)
        mustValid(result[0].etherReceiver)
        mustValid(result[0].fromTimeAsString)
        mustValid(result[0].fromTime)
        mustValid(result[0].tillTimeAsString)
        mustValid(result[0].tillTime)
        mayValid(result[0].etherReceiverAnnotation)
        mayValid(result[0].tokenSenderAnnotation)
        mayValid(result[0].etherReceiverAnnotation)
        mayValid(result[0].etherReceiverAnnotation)
        mustValid(result[0].tokenBuyers)
        mustValid(result[0].transactions)
        mustValid(result[0].tokenBuyers)
        mayValid(result[0].tokenAmount)
        mustValid(result[0].tokenSender)
        mustValid(result[0].toString())
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