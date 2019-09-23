package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class PositionStatsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.dydx.positionStats()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].depositHeld)
        mustValid(list[0].depositOwed)
        mustValid(list[0].depositPrincipalAmount)
        mustValid(list[0].endTime)
        mustValid(list[0].endTimeAsString)
        mustValid(list[0].heldBalance)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].heldClosedCount)
        mustValid(list[0].heldIncreasedCount)
        mustValid(list[0].heldPrincipalUsedAmount)
        mustValid(list[0].heldTokenAddress)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].openTime)
        mustValid(list[0].openTimeAsString)
        mustValid(list[0].endTimeAsString)
        mustValid(list[0].endTime)
        mustValid(list[0].payoutHeld)
        mustValid(list[0].payoutOwed)
        mustValid(list[0].payoutPercentage)
        mustValid(list[0].payoutPrincipalAmount)
        mustValid(list[0].positionTokenAddress)
        mustValid(list[0].plHeldPercentage)
        mustValid(list[0].plOwedPercentage)
        mustValid(list[0].plPercentage)
        mustValid(list[0].traders)
        mustValid(list[0].tradersInLoss)
        mustValid(list[0].tradersInProfit)
        mustValid(list[0].haveEndTime())
        mustValid(list[0].haveOpenTime())
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `position not exist`() {
        val contract = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.dydx.positionStats(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}