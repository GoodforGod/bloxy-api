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
class StatsDailyTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
            val result = api.tokenSale.statsDaily(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            ifValid(result[0].eth_amount)
            ifValid(result[0].token_amount)
            ifValid(result[0].token_buyers)
            ifValid(result[0].transactions)
            ifValid(result[0].txDateAsString)
            ifValid(result[0].toString())
            if (result[0].txDateAsString.isNotEmpty()) {
                ifValid(result[0].haveDate())
                ifValid(result[0].txDate)
            }
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.tokenSale.statsDaily(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.tokenSale.statsDaily(contract)
    }
}