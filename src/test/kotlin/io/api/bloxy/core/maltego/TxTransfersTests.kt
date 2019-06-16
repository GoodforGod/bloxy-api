package io.api.bloxy.core.maltego

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
class TxTransfersTests  : BloxyTester() {

    @Test
    fun valid() {
        val list = listOf("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val result = api.maltego.txTransfers(list, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveTxTime())
        mustValid(result[0].amount)
        mustValid(result[0].receiver)
        mustValid(result[0].receiverType)
        mayValid(result[0].receiverAnnotation)
        mustValid(result[0].receiverTypeAsString)
        mustValid(result[0].sender)
        mustValid(result[0].senderType)
        mayValid(result[0].senderAnnotation)
        mustValid(result[0].senderTypeAsString)
        mayValid(result[0].tokenAddress)
        mustValid(result[0].tokenSymbol)
        mustValid(result[0].txHash)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].toString())
        if(result[0].tokenSymbol == "ETH")
            mustValid(result[0].isEth())
    }

    @Test
    fun `non exist address empty result`() {
        val list = listOf("0x12a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val result = api.maltego.txTransfers(list)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val list = listOf("0x5a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        api.maltego.txTransfers(list)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.maltego.txTransfers(emptyList())
    }
}