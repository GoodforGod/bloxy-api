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
class AddressVolumeTests : BloxyTester() {

    @Test
    fun valid() {
        val addresses = listOf("0xe49C438d7Fee8F36cE95658AB875faf197952dD8")
        val result = api.moneyFlow.addressVolumes(addresses)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].address)
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].receivedAmount)
        assertNotNull(result[0].receivedTxs)
        assertNotNull(result[0].sentAmount)
        assertNotNull(result[0].sentTxs)
        assertNotNull(result[0].typeAsString)
        assertNotNull(result[0].addrType)
        assertNotNull(result[0].toString())
    }

    @Test
    fun `valid with contract`() {
        val addresses = listOf("0xe49C438d7Fee8F36cE95658AB875faf197952dD8")
        val contract = "0x45555629aabfea138ead1c1e5f2ac3cce2add830"
        val result = api.moneyFlow.addressVolumes(addresses, contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val addresses = listOf("0xe49C438d7Fee8F36cE95658AB875faf197952dD8")
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.moneyFlow.addressVolumes(addresses, contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val addresses = listOf("0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949")
        val result = api.moneyFlow.addressVolumes(addresses)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val addresses = listOf("0x0ea08A2d404d3172d2AdD29A45be56dA40e2949")
        api.moneyFlow.addressVolumes(addresses)
    }
}