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
class MethodsTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xd26114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.contract.methods(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].count)
        assertNotNull(result[0].firstTxAtAsString)
        assertNotNull(result[0].lastTxAtAsString)
        assertNotNull(result[0].name)
        assertNotNull(result[0].signature)
        assertNotNull(result[0].signatureHash)
        assertNotNull(result[0].haveFirstTxAt())
        assertNotNull(result[0].haveLastTxAt())
        assertNotNull(result[0].lastTxAt)
        assertNotNull(result[0].firstTxAt)
        assertNotNull(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.contract.methods(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val contract = "0xd6114cd6ee289accf82350c8d8487fedb8a0c07"
        api.contract.methods(contract)
    }
}