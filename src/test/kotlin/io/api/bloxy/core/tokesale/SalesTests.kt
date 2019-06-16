package io.api.bloxy.core.tokesale

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.error.ParamException
import io.api.bloxy.model.dto.tokensale.Sale
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class SalesTests : BloxyTester() {

    companion object {

        private var sales: List<Sale> = emptyList()

        fun getTokenSale(api: BloxyApi): List<String> {
            val result = if (sales.isEmpty()) {
                sales = api.tokenSale.sales(limit = 5)
                sales
            } else {
                sales
            }

            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertFalse(result[0].tokenAddress.isEmpty())
            return result.asSequence().map { it.tokenAddress }.toList()
        }

        fun getRandomTokenSale(api: BloxyApi): String {
            val result = if (sales.isEmpty()) {
                sales = api.tokenSale.sales()
                sales
            } else {
                sales
            }

            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertFalse(result[0].tokenAddress.isEmpty())
            return result[0].tokenAddress
        }
    }

    @Test
    fun valid() {
        val result = api.tokenSale.sales()
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].ethAmount)
        mustValid(result[0].symbol)
        mustValid(result[0].tokenAddress)
        mustValid(result[0].tokenAmount)
        mustValid(result[0].tokenBuyers)
        mustValid(result[0].transactions)
        mustValid(result[0].typeAsString)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
    }

    @Test
    fun `valid with sale`() {
        val sale = getRandomTokenSale(api)
        val contracts = listOf(sale)
        val result = api.tokenSale.sales(contracts)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
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