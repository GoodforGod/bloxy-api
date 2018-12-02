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
        val result = api.contract.signaturesByName(hashes)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].abi)
        assertNotNull(result[0].type)
        assertNotNull(result[0].name)
        assertNotNull(result[0].signature)
        assertNotNull(result[0].signature_hash)
        assertNotNull(result[0].toString())
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