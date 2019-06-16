package io.api.bloxy.core.transaction

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class DetailsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = listOf("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val result = api.tx.details(list)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveTxTime())
        mustValid(result[0].amount)
        mustValid(result[0].block)
        mustValid(result[0].gas)
        mustValid(result[0].gasPrice)
        mustValid(result[0].gasValue)
        mayValid(result[0].method)
        mayValid(result[0].receiver)
        mustValid(result[0].txFrom)
        mayValid(result[0].txFromAnnotation)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txHash)
        mustValid(result[0].txTo)
        mayValid(result[0].txToAnnotation)
        mustValid(result[0].txToType)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val list = listOf("0x12a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val details = api.tx.details(list)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val list = listOf("0x5a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        api.tx.details(list)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.tx.details(emptyList())
    }
}