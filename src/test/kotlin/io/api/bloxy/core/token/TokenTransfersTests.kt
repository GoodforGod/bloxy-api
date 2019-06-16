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
        val result = api.token.transfers(contract, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveTxTime())
        mustValid(result[0].amount)
        mayValid(result[0].gasPrice)
        mayValid(result[0].gasValue)
        mustValid(result[0].symbol)
        mustValid(result[0].tokenReceiver)
        mustValid(result[0].symbol)
        mustValid(result[0].tokenSender)
        mayValid(result[0].tokenReceiverAnnotation)
        mayValid(result[0].tokenSenderAnnotation)
        mayValid(result[0].txFromAnnotation)
        mustValid(result[0].txFrom)
        mustValid(result[0].txHash)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txTime)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.transfers(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.transfers(contract)
    }
}