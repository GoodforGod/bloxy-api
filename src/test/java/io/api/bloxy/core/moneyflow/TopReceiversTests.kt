package io.api.bloxy.core.moneyflow

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class TopReceiversTests : Tester() {

    @Test
    fun valid() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.topReceivers(address, limit = 2000)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].receiver)
        assertNotNull(result[0].transactions)
        assertNotNull(result[0].amount)
        assertNotNull(result[0].typeAsString)
        assertNotNull(result[0].addressType)
    }

    @Test
    fun `valid with contract`() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val contract = "0xd26114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.moneyFlow.topReceivers(address, contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.moneyFlow.topReceivers(address, contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.topReceivers(address)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val address = "0xCea08A2d404d3172d2AdD29A45be56dA40e2949"
        api.moneyFlow.topReceivers(address)
    }
}