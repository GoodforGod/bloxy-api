package io.api.bloxy.core.transaction

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class EventsByTxTests : BloxyTester() {

    @Test
    fun valid() {
        val txHash = "0x4a9593b8724284ebe3c90b8a26f8e28e92dd518e366e07163c391ba28dc91dc5"
        val result = api.tx.eventsByTx(txHash)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].block)
        mustValid(result[0].txFrom)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txHash)
        mustValid(result[0].signature)
        mustValid(result[0].smartContractAddress)
        mayValid(result[0].smartContractAnnotation)
        mayValid(result[0].txFromAnnotation)
        mustValid(result[0].smartContractType)
        mustValid(result[0].event)
        mustValid(result[0].addrType)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val txHash = "0x12a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986"
        val details = api.tx.eventsByTx(txHash)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val txHash = "0x2a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986"
        api.tx.eventsByTx(txHash)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.tx.eventsByTx("")
    }
}