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
        val list = api.dex.pendingTxs(emptyList())
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        assertNotNull(list[0].method)
        assertNotNull(list[0].arguments)
        assertNotNull(list[0].methodAsString)
        assertNotNull(list[0].protocol)
        assertNotNull(list[0].signature)
        assertNotNull(list[0].smartContractAddress)
        assertNotNull(list[0].txHash)
        assertNotNull(list[0].txSender)
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