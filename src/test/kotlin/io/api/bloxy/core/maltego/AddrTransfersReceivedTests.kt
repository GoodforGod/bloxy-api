package io.api.bloxy.core.maltego

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test
import java.time.LocalDate


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
class AddrTransfersReceivedTests : BloxyTester() {

    @Test
    fun valid() {
        val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val result = api.maltego.addrTransfersReceived(addresses, limit = 5, since = LocalDate.of(2016, 1, 1))
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].amount)
        mustValid(result[0].receiver)
        mustValid(result[0].sender)
        mayValid(result[0].senderAnnotation)
        mayValid(result[0].senderFlag)
        mustValid(result[0].senderType)
        mustValid(result[0].tokenAddress)
        mustValid(result[0].tokenSymbol)
        mustValid(result[0].txHash)
        mustValid(result[0].txTime)
        mustValid(result[0].addrType)
        mustValid(result[0].haveTxTime())
        mustValid(result[0].toString())
    }

    @Test
    fun `valid with contract`() {
        val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val contracts = listOf("0xd26114cd6ee289accf82350c8d8487fedb8a0c07")
        val result = api.maltego.addrTransfersReceived(addresses, contracts, since = LocalDate.of(2016, 1, 1))
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val contracts = listOf("0xd16114cd6ee289accf82350c8d8487fedb8a0c07")
        val result = api.maltego.addrTransfersReceived(addresses, contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val addresses = listOf("0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val result = api.maltego.addrTransfersReceived(addresses)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val addresses = listOf("0x0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        api.maltego.addrTransfersReceived(addresses)
    }
}