package io.api.bloxy.core.tokesale

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.11.2018
 */
class BuyersOffsetAndLimitTests : BloxyTester() {

    @Test
    fun `valid with sale and offset and limit exceed single request maximum`() {
        val sale = SalesTests.getRandomTokenSale(api)
        if (!sale.isEmpty()) {
            val result = api.tokenSale.buyers(sale, offset = 10, limit = 4500)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertNotNull(result[0].toString())

            val resultBeginning = api.tokenSale.buyers(sale, limit = 10)
            assertNotNull(resultBeginning)
            assertFalse(resultBeginning.isEmpty())
            assertFalse(resultBeginning[0].isEmpty())
            assertNotEquals(result[0], resultBeginning[0])
        }
    }
}