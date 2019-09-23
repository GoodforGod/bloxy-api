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
        val list = api.dydx.positionsLS(limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].borrowedAmount)
        mustValid(list[0].depositInHeldTokenAmount)
        mayValid(list[0].depositInOwedTokenAmount)
        mustValid(list[0].depositTokenSymbol)
        mustValid(list[0].event)
        mustValid(list[0].heldAmount)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].interest)
        mayValid(list[0].lender)
        mayValid(list[0].loanFeeRecipient)
        mayValid(list[0].loanHash)
        mustValid(list[0].owedTokenSymbol)
        mustValid(list[0].positionId)
        mustValid(list[0].positionTokenAddress)
        mustValid(list[0].principalAmount)
        mustValid(list[0].trader)
        mayValid(list[0].traderAnnotation)
        mustValid(list[0].txFrom)
        mayValid(list[0].txFromAnnotation)
        mustValid(list[0].txHash)
        mustValid(list[0].txTime)
        mustValid(list[0].txTimeAsString)
        mustValid(list[0].haveTxTime())
        mustValid(list[0].toString())
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