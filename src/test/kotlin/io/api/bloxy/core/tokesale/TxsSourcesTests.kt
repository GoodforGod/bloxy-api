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
class TxsSourcesTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
            val result = api.tokenSale.txsSources(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            ifValid(result[0].depth)
            ifValid(result[0].amount)
            ifValid(result[0].receiver)
            ifValid(result[0].receiverType)
            ifValid(result[0].receiverTypeAsString)
            ifValid(result[0].receiverAnnotation)
            ifValid(result[0].sender)
            ifValid(result[0].senderType)
            ifValid(result[0].senderTypeAsString)
            ifValid(result[0].senderAnnotation)
            ifValid(result[0].txHash)
            ifValid(result[0].txTimeAsString)
            ifValid(result[0].txTime)
            ifValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.tokenSale.txsSources(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.tokenSale.txsSources(contract)
    }
}