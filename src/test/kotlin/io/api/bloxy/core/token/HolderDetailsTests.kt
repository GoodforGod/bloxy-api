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
        assertTrue(result[0].haveFirstTxTime())
        assertTrue(result[0].haveLastTxTime())
        assertNotNull(result[0].address)
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].balance)
        assertNotNull(result[0].firstTxAtAsString)
        assertNotNull(result[0].firstTxAt)
        assertNotNull(result[0].fromAmount)
        assertNotNull(result[0].fromCount)
        assertNotNull(result[0].fromCount)
        assertNotNull(result[0].lastTxAtAsString)
        assertNotNull(result[0].lastTxAt)
        assertNotNull(result[0].toAmount)
        assertNotNull(result[0].toCount)
        assertNotNull(result[0].typeAsString)
        assertNotNull(result[0].addrType)
        assertNotNull(result[0].uniqSenders)
        assertNotNull(result[0].uniqReceivers)
        assertNotNull(result[0].toString())
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