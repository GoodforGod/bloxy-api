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
        assertNotNull(list[0])
        assertNotNull(list[0].depositHeld)
        assertNotNull(list[0].depositOwed)
        assertNotNull(list[0].depositPrincipalAmount)
        assertNotNull(list[0].endTime)
        assertNotNull(list[0].endTimeAsString)
        assertNotNull(list[0].heldBalance)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].heldClosedCount)
        assertNotNull(list[0].heldIncreasedCount)
        assertNotNull(list[0].heldPrincipalUsedAmount)
        assertNotNull(list[0].heldTokenAddress)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].openTime)
        assertNotNull(list[0].openTimeAsString)
        assertNotNull(list[0].endTimeAsString)
        assertNotNull(list[0].endTime)
        assertNotNull(list[0].payoutHeld)
        assertNotNull(list[0].payoutOwed)
        assertNotNull(list[0].payoutPercentage)
        assertNotNull(list[0].payoutPrincipalAmount)
        assertNotNull(list[0].positionTokenAddress)
        assertNotNull(list[0].plHeldPercentage)
        assertNotNull(list[0].plOwedPercentage)
        assertNotNull(list[0].plPercentage)
        assertNotNull(list[0].traders)
        assertNotNull(list[0].tradersInLoss)
        assertNotNull(list[0].tradersInProfit)
        assertNotNull(list[0].haveEndTime())
        assertNotNull(list[0].haveOpenTime())
        assertNotNull(list[0].toString())
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