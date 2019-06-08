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
class CallsByContractTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.tx.callsByContract(contract, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].block)
        mayValid(result[0].callPath)
        mustValid(result[0].external)
        mustValid(result[0].gasUsed)
        mustValid(result[0].gasPrice)
        mustValid(result[0].gasValue)
        mustValid(result[0].method)
        mustValid(result[0].txFrom)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].txHash)
        mustValid(result[0].signature)
        mustValid(result[0].smartContractAddress)
        mayValid(result[0].smartContractAnnotation)
        mayValid(result[0].txFromAnnotation)
        mayValid(result[0].txSenderAnnotation)
        mustValid(result[0].smartContractType)
        mustValid(result[0].txSender)
        mustValid(result[0].addrType)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val details = api.tx.callsByContract(contract)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0xB7048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.tx.callsByContract(contract)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.tx.callsByContract("")
    }
}