package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.BloxyException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class DepositsTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.dex.deposits(limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].protocol)
        mustValid(result[0].amount)
        mayValid(result[0].amountInCurrency)
        mustValid(result[0].opIndex)
        mustValid(result[0].smartContractAddress)
        mustValid(result[0].symbol)
        mustValid(result[0].token)
        mustValid(result[0].txHash)
        mustValid(result[0].txSender)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].user)
        mayValid(result[0].userAnnotation)
        mustValid(result[0].toString())
    }

    @Test
    fun `valid with protocol`() {
        val protocols = listOf("IDEX")
        val result = api.dex.deposits(protocols, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].protocol)
        mustValid(result[0].amount)
        mayValid(result[0].amountInCurrency)
        mustValid(result[0].opIndex)
        mustValid(result[0].smartContractAddress)
        mustValid(result[0].symbol)
        mustValid(result[0].token)
        mustValid(result[0].txHash)
        mustValid(result[0].txSender)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].user)
        mayValid(result[0].userAnnotation)
        mustValid(result[0].toString())
    }

    @Test(expected = BloxyException::class)
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.deposits(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid empty params`() {
        val list = api.dex.deposits(emptyList(), limit = 5)
        assertNotNull(list)
        assertFalse(list.isEmpty())
    }
}