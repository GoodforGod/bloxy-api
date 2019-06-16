package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 12.06.2019
 */
class TokenFlowGroupTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val groupHash = "5269402581779268652"
        val result = api.token.flowGroup(contract, groupHash,  limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].annotation)
        mayValid(result[0].annotation)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val groupHash = "5269402581779268652"
        val result = api.token.flowGroup(contract, groupHash)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist group empty result`() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val groupHash = "1269402581779268652"
        val result = api.token.flowGroup(contract, groupHash)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val groupHash = "1269402581779268652"
        api.token.flowGroup(contract, groupHash)
    }

    @Test(expected = ParamException::class)
    fun `empty group param error`() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val groupHash = "      "
        api.token.flowGroup(contract, groupHash)
    }
}