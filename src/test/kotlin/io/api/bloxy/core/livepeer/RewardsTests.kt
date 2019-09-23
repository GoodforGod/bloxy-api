package io.api.bloxy.core.livepeer

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class RewardsTests : BloxyTester() {

    @Test
    fun valid() {
        val address = "0xe9e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.livepeer.rewards(address, limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].amount)
        mustValid(list[0].block)
        mustValid(list[0].round)
        mustValid(list[0].recipient)
        mayValid(list[0].recipientAnnotation)
        mustValid(list[0].txHash)
        mustValid(list[0].txTime)
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `address not exist`() {
        val address = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.livepeer.rewards(address)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}