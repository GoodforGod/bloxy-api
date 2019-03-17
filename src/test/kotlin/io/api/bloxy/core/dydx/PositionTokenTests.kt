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
        assertNotNull(list[0])
        assertNotNull(list[0].heldPrice)
        assertNotNull(list[0].holders)
        assertNotNull(list[0].interest)
        assertNotNull(list[0].leverage)
        assertNotNull(list[0].marketcap)
        assertNotNull(list[0].owedPrice)
        assertNotNull(list[0].price)
        assertNotNull(list[0].priceFactor)
        assertNotNull(list[0].supply)
        assertNotNull(list[0].time)
        assertNotNull(list[0].timeAsString)
        assertNotNull(list[0].haveTime())
        assertNotNull(list[0].toString())
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