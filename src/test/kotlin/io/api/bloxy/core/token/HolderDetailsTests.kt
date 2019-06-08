package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class HolderDetailsTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.holderDetails(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveFirstTxTime())
        mustValid(result[0].haveLastTxTime())
        mustValid(result[0].address)
        mustValid(result[0].annotation)
        mustValid(result[0].balance)
        mustValid(result[0].firstTxAtAsString)
        mustValid(result[0].firstTxAt)
        mustValid(result[0].fromAmount)
        mustValid(result[0].fromCount)
        mustValid(result[0].fromCount)
        mustValid(result[0].lastTxAtAsString)
        mustValid(result[0].lastTxAt)
        mustValid(result[0].toAmount)
        mustValid(result[0].toCount)
        mustValid(result[0].typeAsString)
        mustValid(result[0].addrType)
        mustValid(result[0].uniqSenders)
        mustValid(result[0].uniqReceivers)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.holderDetails(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.holderDetails(contract)
    }
}