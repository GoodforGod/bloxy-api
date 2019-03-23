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
        val list = api.dydx.positionsDaily(positionId)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].borrowedAmount)
        assertNotNull(list[0].borrowedAmountChange)
        assertNotNull(list[0].closed)
        assertNotNull(list[0].depositedInHeldToken)
        assertNotNull(list[0].depositedInOwedToken)
        assertNotNull(list[0].heldAmount)
        assertNotNull(list[0].heldAmountChange)
        assertNotNull(list[0].increased)
        assertNotNull(list[0].paidInHeldToken)
        assertNotNull(list[0].paidInOwedToken)
        assertNotNull(list[0].principalAmount)
        assertNotNull(list[0].principalAmountChange)
        assertNotNull(list[0].txDate)
        assertNotNull(list[0].txDateAsString)
        assertNotNull(list[0].haveDate())
        assertNotNull(list[0].toString())
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