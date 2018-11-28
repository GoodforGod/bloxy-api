package io.api.bloxy.core.dex

import io.api.bloxy.core.Tester
import io.api.bloxy.error.BloxyException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class ContractsTests : Tester() {

    @Test
    fun valid() {
        val protocols = listOf("IDEX")
        val list = api.dex.contracts(protocols)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        assertNotNull(list[0].annotation)
        assertNotNull(list[0].latest_trade)
        assertNotNull(list[0].protocol)
        assertNotNull(list[0].protocol)
        assertNotNull(list[0].smart_contract_address)
        assertNotNull(list[0].trades)
    }

    @Test(expected = BloxyException::class)
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.contracts(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid empty params`() {
        val list = api.dex.contracts(emptyList())
        assertNotNull(list)
        assertFalse(list.isEmpty())
    }
}