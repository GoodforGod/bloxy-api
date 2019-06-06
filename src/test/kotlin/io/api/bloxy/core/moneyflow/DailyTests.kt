package io.api.bloxy.core.moneyflow

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 06.06.2019
 */
class DailyTests : BloxyTester() {

    @Test
    fun valid() {
        val addresses = listOf("0xe49C438d7Fee8F36cE95658AB875faf197952dD8")
        val result = api.moneyFlow.daily(addresses)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        ifValid(result[0].address)
        ifValid(result[0].date)
        mayValid(result[0].receiveAmount)
        mayValid(result[0].receiveCount)
        mayValid(result[0].sentAmount)
        mayValid(result[0].sentCount)
        ifValid(result[0].symbol)
        ifValid(result[0].token)
        mayValid(result[0].uniqueReceivers)
        mayValid(result[0].uniqueSenders)
        ifValid(result[0].toString())
    }

    @Test
    fun `valid with contract`() {
        val addresses = listOf("0xe49C438d7Fee8F36cE95658AB875faf197952dD8")
        val contract = listOf("0x45555629aabfea138ead1c1e5f2ac3cce2add830")
        val result = api.moneyFlow.daily(addresses, contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val addresses = listOf("0xe49C438d7Fee8F36cE95658AB875faf197952dD8")
        val contract = listOf("0xd16114cd6ee289accf82350c8d8487fedb8a0c07")
        val result = api.moneyFlow.daily(addresses, contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val addresses = listOf("0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val result = api.moneyFlow.daily(addresses)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val addresses = listOf("0x0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        api.moneyFlow.daily(addresses)
    }
}