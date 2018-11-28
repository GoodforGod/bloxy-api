package io.api.bloxy.core.token

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TokenTransfersTests : Tester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.tokenTransfers(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].amount)
        assertNotNull(result[0].gas_price)
        assertNotNull(result[0].gas_value)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].token_receiver)
        assertNotNull(result[0].symbol)
        assertNotNull(result[0].token_receiver_annotation)
        assertNotNull(result[0].token_sender)
        assertNotNull(result[0].token_sender_annotation)
        assertNotNull(result[0].tx_from)
        assertNotNull(result[0].tx_from_annotation)
        assertNotNull(result[0].tx_hash)
        assertNotNull(result[0].tx_time_as_string)
        assertNotNull(result[0].tx_time)
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.tokenTransfers(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.tokenTransfers(contract)
    }
}