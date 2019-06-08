package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class PositionsDailyTests : BloxyTester() {

    @Test
    fun valid() {
        val positionId = "5f0b07c13925075b2d0617fbcc3e3a3c322bcf780ce100fc70787a2372acd3a6"
        val list = api.dydx.positionsDaily(positionId, limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].borrowedAmount)
        mustValid(list[0].borrowedAmountChange)
        mayValid(list[0].closed)
        mustValid(list[0].depositedInHeldToken)
        mustValid(list[0].depositedInOwedToken)
        mustValid(list[0].heldAmount)
        mustValid(list[0].heldAmountChange)
        mustValid(list[0].increased)
        mayValid(list[0].paidInHeldToken)
        mayValid(list[0].paidInOwedToken)
        mustValid(list[0].principalAmount)
        mustValid(list[0].principalAmountChange)
        mustValid(list[0].txDate)
        mustValid(list[0].txDateAsString)
        mustValid(list[0].haveDate())
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `position not exist`() {
        val positionId = "1f0b07c13925075b2d0617fbcc3e3a3c322bcf780ce100fc70787a2372acd3a6"
        val list = api.dydx.positionsDaily(positionId)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}