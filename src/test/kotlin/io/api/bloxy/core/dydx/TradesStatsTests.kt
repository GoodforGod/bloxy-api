package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class TradesStatsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.dydx.tradersStats()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].depositHeld)
        mayValid(list[0].depositOwed)
        mustValid(list[0].depositPrincipalAmount)
        mayValid(list[0].addressAnnotation)
        mustValid(list[0].address)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].heldClosedCount)
        mustValid(list[0].heldIncreasedCount)
        mustValid(list[0].heldPrincipalUsedAmount)
        mustValid(list[0].heldTokenAddress)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].payoutHeld)
        mayValid(list[0].payoutOwed)
        mustValid(list[0].payoutPercentage)
        mustValid(list[0].payoutPrincipalAmount)
        mustValid(list[0].positionTokenAddress)
        mustValid(list[0].plHeldPercentage)
        mayValid(list[0].plOwedPercentage)
        mustValid(list[0].plPercentage)
        mustValid(list[0].firstIncreaseTime)
        mustValid(list[0].firstIncreaseTimeAsString)
        mustValid(list[0].lastCloseTime)
        mustValid(list[0].lastCloseTimeAsString)
        mayValid(list[0].owedClosedCount)
        mayValid(list[0].owedIncreasedCount)
        mayValid(list[0].owedPrincipalClosed)
        mayValid(list[0].owedPrincipalIncreased)
        mayValid(list[0].owedPrincipalUsedAmount)
        mustValid(list[0].owedTokenAddress)
        mustValid(list[0].owedTokenSymbol)
        mustValid(list[0].success)
        mustValid(list[0].symbol)
        mustValid(list[0].type)
        mustValid(list[0].haveFirstIncreaseTime())
        mustValid(list[0].haveLastCloseTime())
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `contract not exist`() {
        val contract = "0x1fa7938eae77a1241d0bdce6c2ce88575d956381"
        val list = api.dydx.tradersStats(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}