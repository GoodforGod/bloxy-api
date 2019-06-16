package io.api.bloxy.core.moneyflow

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.BloxyException
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class MoneySourceTests : BloxyTester() {

    @Test
    fun valid() {
        val address = "0xe49C438d7Fee8F36cE95658AB875faf197952dD8"
        val result = api.moneyFlow.moneySource(address, limit = 5, depth = 1)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].amount)
        mayValid(result[0].annotation)
        mustValid(result[0].typeAsString)
        mustValid(result[0].addrType)
        mustValid(result[0].toString())
    }

    @Test(expected = BloxyException::class)
    fun `too many transaction in specified timeframe`() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.moneySource(address, limit = 5, depth = 1)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid with contract`() {
        val address = "0xe49C438d7Fee8F36cE95658AB875faf197952dD8"
        val contract = "0x45555629aabfea138ead1c1e5f2ac3cce2add830"
        val result = api.moneyFlow.moneySource(address, contract, limit = 5, depth = 1)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val address = "0xe49C438d7Fee8F36cE95658AB875faf197952dD8"
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.moneyFlow.moneySource(address, contract, limit = 5, depth = 1)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.moneySource(address)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val address = "0xCea08A2d404d3172d2AdD29A45be56dA40e2949"
        api.moneyFlow.moneySource(address)
    }
}