package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class TradesStatsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.dydx.tradersStats()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].depositHeld)
        assertNotNull(list[0].depositOwed)
        assertNotNull(list[0].depositPrincipalAmount)
        assertNotNull(list[0].addressAnnotation)
        assertNotNull(list[0].address)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].heldClosedCount)
        assertNotNull(list[0].heldIncreasedCount)
        assertNotNull(list[0].heldPrincipalUsedAmount)
        assertNotNull(list[0].heldTokenAddress)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].payoutHeld)
        assertNotNull(list[0].payoutOwed)
        assertNotNull(list[0].payoutPercentage)
        assertNotNull(list[0].payoutPrincipalAmount)
        assertNotNull(list[0].positionTokenAddress)
        assertNotNull(list[0].plHeldPercentage)
        assertNotNull(list[0].plOwedPercentage)
        assertNotNull(list[0].plPercentage)
        assertNotNull(list[0].firstIncreaseTime)
        assertNotNull(list[0].firstIncreaseTimeAsString)
        assertNotNull(list[0].lastCloseTime)
        assertNotNull(list[0].lastCloseTimeAsString)
        assertNotNull(list[0].owedClosedCount)
        assertNotNull(list[0].owedIncreasedCount)
        assertNotNull(list[0].owedPrincipalClosed)
        assertNotNull(list[0].owedPrincipalIncreased)
        assertNotNull(list[0].owedPrincipalUsedAmount)
        assertNotNull(list[0].owedTokenAddress)
        assertNotNull(list[0].owedTokenSymbol)
        assertNotNull(list[0].success)
        assertNotNull(list[0].symbol)
        assertNotNull(list[0].type)
        assertNotNull(list[0].haveFirstIncreaseTime())
        assertNotNull(list[0].haveLastCloseTime())
        assertNotNull(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `contract not exist`() {
        val contract = "0x1fa7938eae77a1241d0bdce6c2ce88575d956381"
        val list = api.dydx.tradersStats(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}