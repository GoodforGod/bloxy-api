package io.api.bloxy.core.tokesale

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class SalesTests : BloxyTester() {

    companion object {
        fun getRandomTokenSale(api: BloxyApi): String {
            val result = api.tokenSale.sales()
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertNotNull(result[0].ethAmount)
            assertNotNull(result[0].symbol)
            assertNotNull(result[0].tokenAddress)
            assertNotNull(result[0].tokenAmount)
            assertNotNull(result[0].tokenBuyers)
            assertNotNull(result[0].transactions)
            assertNotNull(result[0].typeAsString)
            assertNotNull(result[0].tokenType)
            return result[0].tokenAddress
        }
    }

    @Test
    fun valid() {
        assertNotNull(getRandomTokenSale(api))
    }

    @Test
    fun `valid with sale`() {
        val sale = getRandomTokenSale(api)
        if (!sale.isEmpty()) {
            val contracts = listOf(sale)
            val result = api.tokenSale.sales(contracts)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
        }
    }

    @Test
    fun `non exist address empty result`() {
        val contracts = listOf("0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d")
        val result = api.tokenSale.sales(contracts)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d")
        api.tokenSale.sales(contracts)
    }
}