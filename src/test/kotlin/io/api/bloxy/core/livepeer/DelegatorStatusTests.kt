package io.api.bloxy.core.livepeer

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class DelegatorStatusTests : BloxyTester() {

    // Takes huge amount of time to complete
//    @Test
    fun valid() {
        val list = api.livepeer.delegatorStatus()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].address)
        mustValid(list[0].bondedAmount)
        mustValid(list[0].delegate)
        mayValid(list[0].delegatedAmount)
        mayValid(list[0].fees)
        mayValid(list[0].lastClaimRound)
        mustValid(list[0].pendingStake)
        mustValid(list[0].startRound)
        mustValid(list[0].totalStake)
        mustValid(list[0].unclaimedEarnings)
        mustValid(list[0].withdrawRound)
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `address not exist`() {
        val roundManagerProxy = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.livepeer.delegatorStatus(roundManagerProxy)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}