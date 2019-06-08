package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class PositionTokenTests : BloxyTester() {

    @Test
    fun valid() {
        val positionsTokens = "0x9fa7938eae77a1241d0bdce6c2ce88575d956381"
        val list = api.dydx.positionToken(positionsTokens)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].heldPrice)
        mustValid(list[0].holders)
        mustValid(list[0].interest)
        mustValid(list[0].leverage)
        mustValid(list[0].marketcap)
        mustValid(list[0].owedPrice)
        mustValid(list[0].price)
        mustValid(list[0].priceFactor)
        mustValid(list[0].supply)
        mustValid(list[0].time)
        mustValid(list[0].timeAsString)
        mustValid(list[0].haveTime())
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `contract not exist`() {
        val positionsTokens = "0x1fa7938eae77a1241d0bdce6c2ce88575d956381"
        val list = api.dydx.positionToken(positionsTokens)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}