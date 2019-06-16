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
        val result = api.tokenSale.txsSources(sale, limit = 5, depth = 1)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mayValid(result[0].depth)
        mustValid(result[0].amount)
        mustValid(result[0].receiver)
        mustValid(result[0].receiverType)
        mustValid(result[0].receiverTypeAsString)
        mayValid(result[0].receiverAnnotation)
        mustValid(result[0].sender)
        mustValid(result[0].senderType)
        mustValid(result[0].senderTypeAsString)
        mayValid(result[0].senderAnnotation)
        mustValid(result[0].txHash)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txTime)
        mustValid(result[0].toString())
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