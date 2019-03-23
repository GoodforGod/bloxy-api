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
        val list = api.dydx.positions()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].creator)
        assertNotNull(list[0].creatorAnnotation)
        assertNotNull(list[0].depositAmount)
        assertNotNull(list[0].depositTokenAddress)
        assertNotNull(list[0].depositTokenSymbol)
        assertNotNull(list[0].endTime)
        assertNotNull(list[0].endTimeAsString)
        assertNotNull(list[0].heldTokenAddress)
        assertNotNull(list[0].heldTokenFromSell)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].depositAmount)
        assertNotNull(list[0].depositTokenSymbol)
        assertNotNull(list[0].depositTokenAddress)
        assertNotNull(list[0].interestRate)
        assertNotNull(list[0].lender)
        assertNotNull(list[0].lenderAnnotation)
        assertNotNull(list[0].marginTxHash)
        assertNotNull(list[0].openTime)
        assertNotNull(list[0].openTimeAsString)
        assertNotNull(list[0].owedTokenAddress)
        assertNotNull(list[0].owedTokenSymbol)
        assertNotNull(list[0].positionId)
        assertNotNull(list[0].positionTokenAddress)
        assertNotNull(list[0].positionTokenAnnotation)
        assertNotNull(list[0].status)
        assertNotNull(list[0].symbol)
        assertNotNull(list[0].priceFactor)
        assertNotNull(list[0].priceFormula)
        assertNotNull(list[0].principalAmount)
        assertNotNull(list[0].haveEndTime())
        assertNotNull(list[0].haveOpenTime())
        assertNotNull(list[0].toString())
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