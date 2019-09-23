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
class WalletsTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sales = SalesTests.getTokenSaleAddress(api)
        val result = sales.asSequence()
            .map { sale -> api.tokenSale.wallets(sale) }
            .filter { result -> result.isNotEmpty() }
            .first()

        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].balance)
        mayValid(result[0].annotation)
        mustValid(result[0].amountReceived)
        mayValid(result[0].amountSent)
        mustValid(result[0].transfersReceived)
        mayValid(result[0].transfersSent)
        mustValid(result[0].fromTime)
        mustValid(result[0].tillTime)
        mustValid(result[0].toString())
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