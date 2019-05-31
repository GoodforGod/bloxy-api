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
class BuyersTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
            val result = api.tokenSale.buyers(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertTrue(result[0].haveFromTime())
            assertTrue(result[0].haveTillTime())
            ifValid(result[0].ethAmount)
            ifValid(result[0].tokenBuyerAnnotation)
            ifValid(result[0].fromTime)
            ifValid(result[0].fromTimeAsString)
            ifValid(result[0].tillTime)
            ifValid(result[0].tillTimeAsString)
            ifValid(result[0].tokenAmount)
            ifValid(result[0].tokenBuyer)
            ifValid(result[0].transactions)
            ifValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.tokenSale.buyers(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.tokenSale.buyers(contract)
    }
}