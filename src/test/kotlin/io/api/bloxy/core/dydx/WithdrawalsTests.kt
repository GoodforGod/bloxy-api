package io.api.bloxy.core.dydx

import io.api.bloxy.core.BloxyTester
import org.junit.Test

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 14.03.2019
 */
class WithdrawalsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = api.dydx.withdrawals(limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        mustValid(list[0].heldTokenAddress)
        mustValid(list[0].heldTokenPayout)
        mustValid(list[0].heldTokenSymbol)
        mustValid(list[0].owedTokenAddress)
        mustValid(list[0].owedTokenSymbol)
        mustValid(list[0].positionId)
        mustValid(list[0].positionTokenAddress)
        mustValid(list[0].symbol)
        mustValid(list[0].tokensRedeemed)
        mustValid(list[0].trader)
        mayValid(list[0].traderAnnotation)
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
    fun `contract not exist`() {
        val contract = "0x1fa7938eae77a1241d0bdce6c2ce88575d956381"
        val list = api.dydx.withdrawals(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}