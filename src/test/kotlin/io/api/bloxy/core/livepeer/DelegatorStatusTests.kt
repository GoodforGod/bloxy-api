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

    @Test
    fun valid() {
        val list = api.livepeer.delegatorStatus()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].address)
        assertNotNull(list[0].bondedAmount)
        assertNotNull(list[0].delegate)
        assertNotNull(list[0].delegatedAmount)
        assertNotNull(list[0].fees)
        assertNotNull(list[0].lastClaimRound)
        assertNotNull(list[0].pendingStake)
        assertNotNull(list[0].startRound)
        assertNotNull(list[0].totalStake)
        assertNotNull(list[0].unclaimedEarnings)
        assertNotNull(list[0].withdrawRound)
        assertNotNull(list[0].toString())
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