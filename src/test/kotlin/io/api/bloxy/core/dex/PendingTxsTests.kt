package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class PendingTxsTests : BloxyTester() {

    @Test
    fun `valid empty params`() {
        val result = api.dex.pendingTxs(emptyList())
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].methodAsString)
        mayValid(result[0].method)
        mustValid(result[0].arguments)
        mustValid(result[0].protocol)
        mustValid(result[0].signature)
        mustValid(result[0].smartContractAddress)
        mustValid(result[0].txHash)
        mustValid(result[0].txSender)
        mustValid(result[0].toString())
    }

    @Test
    fun `valid with protocol`() {
        val protocols = listOf("IDEX")
        val list = api.dex.pendingTxs(protocols)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        assertNotNull(list[0].method)
    }

    @Test
    fun `valid with contracts`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.pendingTxs(dexContracts = contracts)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid dex contract`() {
        val contracts = listOf("0xa0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.pendingTxs(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `empty dex contract not exist`() {
        val contracts = listOf("0x1a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.pendingTxs(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.pendingTxs(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid with protocol and contract`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val protocols = listOf("IDEX")
        val list = api.dex.pendingTxs(protocols, contracts)
        assertNotNull(list)
    }
}