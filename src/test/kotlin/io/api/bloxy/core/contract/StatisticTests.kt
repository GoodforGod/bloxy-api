package io.api.bloxy.core.contract

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
class StatisticTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xd26114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.contract.statistic(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].annotation)
        mustValid(result[0].type)
        mustValid(result[0].callers)
        mustValid(result[0].calls)
        mustValid(result[0].senders)
        mustValid(result[0].days)
        mustValid(result[0].externalCalls)
        mustValid(result[0].firstCallAt)
        mustValid(result[0].firstCallAtAsString)
        mustValid(result[0].lastCallAt)
        mustValid(result[0].lastCallAtAsString)
        mustValid(result[0].methods)
        mustValid(result[0].period)
        mustValid(result[0].periodAsString)
        mustValid(result[0].txs)
        mustValid(result[0].addrType)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.contract.statistic(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val contract = "0x26114cd6ee289accf82350c8d8487fedb8a0c07"
        api.contract.statistic(contract)
    }
}