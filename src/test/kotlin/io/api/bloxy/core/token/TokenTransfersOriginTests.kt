package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class TokenTransfersOriginTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.transfersOrigin(contract, limit = 5, depth = 1)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].amount)
        mustValid(result[0].gasPrice)
        mustValid(result[0].gasValue)
        mustValid(result[0].symbol)
        mustValid(result[0].tokenReceiver)
        mustValid(result[0].symbol)
        mayValid(result[0].tokenSenderAnnotation)
        mayValid(result[0].txFromAnnotation)
        mayValid(result[0].tokenBuyerAnnotation)
        mayValid(result[0].tokenOriginAnnotation)
        mayValid(result[0].tokenReceiverAnnotation)
        mustValid(result[0].tokenSender)
        mustValid(result[0].txFrom)
        mustValid(result[0].txHash)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txTime)
        mustValid(result[0].tokenBuyer)
        mustValid(result[0].tokenOrigin)
        mustValid(result[0].tokenReceiver)
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