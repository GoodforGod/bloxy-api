package io.api.bloxy.core.tokesale

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class TokenDistributionTests : Tester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
        if (!sale.isEmpty()) {
            val result = api.tokenSale.tokenDistribution(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertNotNull(result[0].tx_time)
            assertNotNull(result[0].tx_hash)
            assertNotNull(result[0].sender_annotation)
            assertNotNull(result[0].sender)
            assertNotNull(result[0].senderTypeAsString)
            assertNotNull(result[0].senderType)
            assertNotNull(result[0].receiver_annotation)
            assertNotNull(result[0].receiver)
            assertNotNull(result[0].receiverTypeAsString)
            assertNotNull(result[0].receiverType)
            assertNotNull(result[0].depth)
            assertNotNull(result[0].amount)
        }
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.tokenSale.tokenDistribution(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.tokenSale.tokenDistribution(contract)
    }
}