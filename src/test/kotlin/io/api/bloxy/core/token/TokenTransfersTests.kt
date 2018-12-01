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
class TokenTransfersTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.tokenTransfers(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTxTime())
        assertNotNull(result[0].amount)
        assertNotNull(result[0].gasPrice)
        assertNotNull(result[0].gasValue)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].tokenReceiver)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].tokenReceiverAnnotation)
        assertNotNull(result[0].tokenSender)
        assertNotNull(result[0].tokenSenderAnnotation)
        assertNotNull(result[0].txFrom)
        assertNotNull(result[0].txFromAnnotation)
        assertNotNull(result[0].txHash)
        assertNotNull(result[0].txTimeAsString)
        assertNotNull(result[0].txTime)
        assertNotNull(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.tokenTransfers(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.tokenTransfers(contract)
    }
}