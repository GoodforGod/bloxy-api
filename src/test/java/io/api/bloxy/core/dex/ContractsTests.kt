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
        val list = api.dex().contracts(protocols)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }

    @Test(expected = BloxyException::class)
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex().contracts(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid empty params`() {
        val list = api.dex().contracts(emptyList())
        assertNotNull(list)
        assertFalse(list.isEmpty())
    }
}