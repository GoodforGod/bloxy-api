package io.api.bloxy.core.moneyflow

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test
import java.time.LocalDate


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class TransfersReceivedTests : BloxyTester() {

    @Test
    fun valid() {
        val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val result = api.moneyFlow.transfersReceived(addresses, since = LocalDate.of(2016, 1, 1), limit = 10)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        ifValid(result[0].amount)
        ifValid(result[0].receiver)
        ifValid(result[0].sender)
        mayValid(result[0].senderAnnotation)
        mayValid(result[0].senderFlag)
        ifValid(result[0].senderType)
        ifValid(result[0].tokenAddress)
        ifValid(result[0].tokenSymbol)
        ifValid(result[0].txHash)
        ifValid(result[0].txTime)
        ifValid(result[0].addrType)
        ifValid(result[0].haveTxTime())
        ifValid(result[0].toString())
    }

    @Test
    fun `valid with contract`() {
        val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val contracts = listOf("0xd26114cd6ee289accf82350c8d8487fedb8a0c07")
        val result = api.moneyFlow.transfersReceived(addresses, contracts, since = LocalDate.of(2016, 1, 1))
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val contracts = listOf("0xd16114cd6ee289accf82350c8d8487fedb8a0c07")
        val result = api.moneyFlow.transfersReceived(addresses, contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val addresses = listOf("0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val result = api.moneyFlow.transfersReceived(addresses)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val addresses = listOf("0x0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        api.moneyFlow.transfersReceived(addresses)
    }
}