package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test
import java.time.LocalDate


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
class HolderSpecificTests : BloxyTester() {

    @Test
    fun valid() {
        val contracts = listOf(
            "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xd26114cd6EE289AccF82350c8d8487fedB8A0C07"
        )

        val result = api.token.holderSpecific(contracts, till = LocalDate.of(2017, 7, 7))
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mayValid(result[0].annotation)
        mustValid(result[0].type)
        mustValid(result[0].addrType)
        mustValid(result[0].balances)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contracts = listOf(
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB27048628DB6B661D4C2aA833e95Dbe1A905B280"
        )

        val result = api.token.holderSpecific(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `only one address`() {
        val contracts = listOf("0xB17048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token.holderSpecific(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `more than 10 addresses`() {
        val contracts = listOf(
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280",
            "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        )

        val result = api.token.holderSpecific(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x97048628DB6B661D4C2aA833e95Dbe1A905B280", "0x7048628DB6B661D4C2aA833e95Dbe1A905B280")
        api.token.holderSpecific(contracts)
    }
}