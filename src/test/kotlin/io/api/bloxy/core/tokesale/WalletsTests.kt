package io.api.bloxy.core.tokesale

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import io.api.bloxy.model.dto.TokenType
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class WalletsTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
            val result = api.tokenSale.wallets(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertNotEquals(TokenType.UNKNOWN, result[0].tokenType)
            ifValid(result[0].ethAmount)
            ifValid(result[0].annotation)
            ifValid(result[0].symbol)
            ifValid(result[0].tokenAddress)
            ifValid(result[0].tokenAmount)
            ifValid(result[0].tokenBuyers)
            ifValid(result[0].transactions)
            ifValid(result[0].typeAsString)
            ifValid(result[0].tokenType)
            ifValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.tokenSale.wallets(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.tokenSale.wallets(contract)
    }
}