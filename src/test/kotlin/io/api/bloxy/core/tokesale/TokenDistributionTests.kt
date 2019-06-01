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
class TokenDistributionTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val result = SalesTests.getTokenSale(api).stream()
            .limit(10)
            .map { api.tokenSale.tokenDistribution(it) }
            .filter { it.isNotEmpty() }
            .findFirst().orElse(emptyList())

        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        ifValid(result[0].address)
        ifValid(result[0].amount)
        mayValid(result[0].annotation)
        mayValid(result[0].fromTime)
        mayValid(result[0].tillTime)
        mayValid(result[0].transactions)
        ifValid(result[0].toString())
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