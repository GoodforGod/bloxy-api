package io.api.bloxy.core.contract

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
class EventsTests : BloxyTester(){

    @Test
    fun valid() {
        val contract = "0xd26114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.contract.events(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].count)
        mustValid(result[0].firstTxAtAsString)
        mustValid(result[0].lastTxAtAsString)
        mustValid(result[0].name)
        mustValid(result[0].signature)
        mustValid(result[0].signatureHash)
        mustValid(result[0].haveFirstTxAt())
        mustValid(result[0].haveLastTxAt())
        mustValid(result[0].lastTxAt)
        mustValid(result[0].firstTxAt)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.contract.events(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val contract = "0xd6114cd6ee289accf82350c8d8487fedb8a0c07"
        api.contract.events(contract)
    }
}