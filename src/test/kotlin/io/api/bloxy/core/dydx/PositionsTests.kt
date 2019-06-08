package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class PositionsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.dydx.positions(limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].creator)
        mustValid(list[0].creatorAnnotation)
        mustValid(list[0].depositAmount)
        mustValid(list[0].depositTokenAddress)
        mustValid(list[0].depositTokenSymbol)
        mustValid(list[0].endTime)
        mustValid(list[0].endTimeAsString)
        mustValid(list[0].heldTokenAddress)
        mayValid(list[0].heldTokenFromSell)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].depositAmount)
        mustValid(list[0].depositTokenSymbol)
        mustValid(list[0].depositTokenAddress)
        mustValid(list[0].interestRate)
        mustValid(list[0].lender)
        mayValid(list[0].lenderAnnotation)
        mustValid(list[0].marginTxHash)
        mustValid(list[0].openTime)
        mustValid(list[0].openTimeAsString)
        mustValid(list[0].owedTokenAddress)
        mustValid(list[0].owedTokenSymbol)
        mustValid(list[0].positionId)
        mustValid(list[0].positionTokenAddress)
        mayValid(list[0].positionTokenAnnotation)
        mustValid(list[0].status)
        mustValid(list[0].symbol)
        mustValid(list[0].priceFactor)
        mustValid(list[0].priceFormula)
        mustValid(list[0].principalAmount)
        mustValid(list[0].haveEndTime())
        mustValid(list[0].haveOpenTime())
        mustValid(list[0].toString())
        if (list.size > 1) {
            assertNotEquals(list[0], list[1])
            assertNotEquals(list[0].hashCode(), list[1].hashCode())
        }
    }

    @Test
    fun `position not exist`() {
        val contract = "0xe1e284277648fcdb09b8efc1832c73c09b5ecf59"
        val list = api.dydx.positions(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}