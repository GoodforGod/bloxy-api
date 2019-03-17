package io.api.bloxy.core.livepeer

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.03.2019
 */
class BondEventsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.livepeer.bondEvents()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].amount)
        assertNotNull(list[0].block)
        assertNotNull(list[0].round)
        assertNotNull(list[0].txFrom)
        assertNotNull(list[0].txFromAnnotation)
        assertNotNull(list[0].txHash)
        assertNotNull(list[0].txTime)
        assertNotNull(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `address not exist`() {
        val delegate = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.livepeer.bondEvents(delegate)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}