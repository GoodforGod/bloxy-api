package io.api.bloxy.core.tokesale

import com.beust.klaxon.Klaxon
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

        fun getTokenSales(api: BloxyApi): List<Sale> {
            if (sales.isEmpty()) {
                sales = api.tokenSale.sales(limit = 5)

                if(sales.isEmpty()) {
                    sales = Klaxon().parseArray("[{\"token_address\":\"0x86fa049857e0209aa7d9e616f7eb3b3b78ecfdb0\"," +
                            "\"symbol\":\"EOS\",\"token_type\":\"ERC20\"," +
                            "\"transactions\":234215,\"eth_amount\":7211776.138200799," +
                            "\"token_amount\":0,\"token_buyers\":65077}," +
                            "{\"token_address\":\"0x744d70fdbe2ba4cf95131626614a1763df805b9e\"," +
                            "\"symbol\":\"SNT\",\"token_type\":\"ERC20\"," +
                            "\"transactions\":28543,\"eth_amount\":567464.843404922," +
                            "\"token_amount\":2958594260.4471626,\"token_buyers\":20376}]") ?: emptyList()
                }
            }

            assertNotNull(sales)
            assertFalse(sales.isEmpty())
            assertFalse(sales[0].isEmpty())
            assertFalse(sales[0].tokenAddress.isEmpty())
            return sales
        }

        fun getTokenSaleAddress(api: BloxyApi): List<String> {
            return getTokenSales(api).asSequence().map { it.tokenAddress }.toList()
        }

        fun getRandomTokenSale(api: BloxyApi): String {
            return getTokenSaleAddress(api)[0]
        }
    }

    @Test
    fun valid() {
        val result = getTokenSales(api)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].ethAmount)
        mustValid(result[0].symbol)
        mustValid(result[0].tokenAddress)
        mayValid(result[0].tokenAmount)
        mustValid(result[0].tokenBuyers)
        mustValid(result[0].transactions)
        mustValid(result[0].typeAsString)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
    }

//    @Test
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
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d")
        api.tokenSale.sales(contracts)
    }
}