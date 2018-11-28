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
class HolderDetailsTests : Tester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.holderDetails(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].address)
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].balance)
        assertNotNull(result[0].first_tx_at_as_string)
        assertNotNull(result[0].first_tx_at)
        assertNotNull(result[0].from_amount)
        assertNotNull(result[0].from_count)
        assertNotNull(result[0].from_count)
        assertNotNull(result[0].last_tx_at_as_string)
        assertNotNull(result[0].last_tx_at)
        assertNotNull(result[0].to_amount)
        assertNotNull(result[0].to_count)
        assertNotNull(result[0].type_as_string)
        assertNotNull(result[0].addressType)
        assertNotNull(result[0].uniq_senders)
        assertNotNull(result[0].uniq_receivers)
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.holderDetails(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.holderDetails(contract)
    }
}