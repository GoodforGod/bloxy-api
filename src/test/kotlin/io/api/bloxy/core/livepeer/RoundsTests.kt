package io.api.bloxy.core.livepeer

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class RoundsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.livepeer.rounds(limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].currentInflation)
        mustValid(list[0].currentMintableTokens)
        mustValid(list[0].block)
        mustValid(list[0].round)
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
        val roundManagerProxy = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.livepeer.rounds(roundManagerProxy)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}