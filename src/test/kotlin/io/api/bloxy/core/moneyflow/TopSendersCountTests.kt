package io.api.bloxy.core.moneyflow

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class TopSendersCountTests : BloxyTester() {

    @Test
    fun valid() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.topSendersCount(address, limit = 10)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mayValid(result[0].annotation)
        mustValid(result[0].sender)
        mustValid(result[0].transactions)
        mustValid(result[0].typeAsString)
        mustValid(result[0].addrType)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.topSendersCount(address)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val address = "0xCea08A2d404d3172d2AdD29A45be56dA40e2949"
        api.moneyFlow.topSendersCount(address)
    }
}