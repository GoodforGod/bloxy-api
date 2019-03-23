package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class PositionsLSTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.dydx.positionsLS()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].borrowedAmount)
        assertNotNull(list[0].depositInHeldTokenAmount)
        assertNotNull(list[0].depositInOwedTokenAmount)
        assertNotNull(list[0].depositTokenSymbol)
        assertNotNull(list[0].event)
        assertNotNull(list[0].heldAmount)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].interest)
        assertNotNull(list[0].lender)
        assertNotNull(list[0].loanFeeRecipient)
        assertNotNull(list[0].loanHash)
        assertNotNull(list[0].owedTokenSymbol)
        assertNotNull(list[0].positionId)
        assertNotNull(list[0].positionTokenAddress)
        assertNotNull(list[0].principalAmount)
        assertNotNull(list[0].trader)
        assertNotNull(list[0].traderAnnotation)
        assertNotNull(list[0].txFrom)
        assertNotNull(list[0].txFromAnnotation)
        assertNotNull(list[0].txHash)
        assertNotNull(list[0].txTime)
        assertNotNull(list[0].txTimeAsString)
        assertNotNull(list[0].haveTxTime())
        assertNotNull(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `position not exist`() {
        val contract = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.dydx.positionsLS(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}