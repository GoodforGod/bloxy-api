package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class TradesArbitrageTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.dex.tradesArbitrage(limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].amount)
        mustValid(result[0].amountInCurrency)
        mustValid(result[0].symbol)
        mustValid(result[0].contractTypes)
        mustValid(result[0].smartContracts)
        mustValid(result[0].tradeIndexes)
        mustValid(result[0].trader)
        mustValid(result[0].txHash)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].toString())
    }
}