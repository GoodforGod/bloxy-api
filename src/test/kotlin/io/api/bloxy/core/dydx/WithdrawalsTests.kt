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
        val list = api.dydx.withdrawals()
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertNotNull(list[0])
        assertNotNull(list[0].heldTokenAddress)
        assertNotNull(list[0].heldTokenPayout)
        assertNotNull(list[0].heldTokenSymbol)
        assertNotNull(list[0].owedTokenAddress)
        assertNotNull(list[0].owedTokenSymbol)
        assertNotNull(list[0].positionId)
        assertNotNull(list[0].positionTokenAddress)
        assertNotNull(list[0].symbol)
        assertNotNull(list[0].tokensRedeemed)
        assertNotNull(list[0].trader)
        assertNotNull(list[0].traderAnnotation)
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
    fun `contract not exist`() {
        val contract = "0x1fa7938eae77a1241d0bdce6c2ce88575d956381"
        val list = api.dydx.withdrawals(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}