package io.api.bloxy.core.livepeer

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class DelegatorsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.livepeer.delegators()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].address)
        assertNotNull(list[0].addressAnnotation)
        assertNotNull(list[0].amount)
        assertNotNull(list[0].count)
        assertNotNull(list[0].bonded)
        assertNotNull(list[0].delegates)
        assertNotNull(list[0].firstTxAt)
        assertNotNull(list[0].lastTxAt)
        assertNotNull(list[0].rebonded)
        assertNotNull(list[0].unbonded)
        assertNotNull(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `address not exist`() {
        val roundManagerProxy = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.livepeer.delegators(roundManagerProxy)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}