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
class SignaturesByNameTests : BloxyTester() {

    @Test
    fun valid() {
        val hashes = listOf("Transfer")
        val result = api.contract.signaturesByName(hashes, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].abi)
        mustValid(result[0].type)
        mustValid(result[0].name)
        mustValid(result[0].signature)
        mustValid(result[0].signatureHash)
        mustValid(result[0].isEvent())
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val hashes = listOf("yransfer")
        val result = api.contract.signaturesByName(hashes)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val hashes = listOf("   ")
        api.contract.signaturesByName(hashes)
    }
}