package io.api.bloxy.core.dex

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class TradesTests : Tester() {

    @Test
    fun `valid empty params`() {
        val list = api.dex.trades(emptyList())
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
        assertNotNull(list[0].amountBuy)
        assertNotNull(list[0].amountSell)
        assertNotNull(list[0].buyAddress)
        assertNotNull(list[0].buyCurrencyId)
        assertNotNull(list[0].buySymbol)
        assertNotNull(list[0].contract_type)
        assertNotNull(list[0].maker)
        assertNotNull(list[0].makerFee)
        assertNotNull(list[0].maker_annotation)
        assertNotNull(list[0].protocol)
        assertNotNull(list[0].sellAddress)
        assertNotNull(list[0].sellCurrencyId)
        assertNotNull(list[0].sellSymbol)
        assertNotNull(list[0].smart_contract_address)
        assertNotNull(list[0].smart_contract_id)
        assertNotNull(list[0].tx_hash)
        assertNotNull(list[0].tx_sender)
        assertNotNull(list[0].tx_time_as_string)
        assertNotNull(list[0].tx_date_as_string)
        assertNotNull(list[0].tx_datetime)
    }

    @Test
    fun `valid with protocol`() {
        val protocols = listOf("IDEX")
        val list = api.dex.trades(protocols)
        assertNotNull(list)
        assertFalse(list.isEmpty())
        assertFalse(list[0].isEmpty())
    }

    @Test
    fun `valid with contracts`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.trades(dexContracts = contracts, timeSpanDays = 40)
        assertNotNull(list)
    }

    @Test
    fun `dex contract not exist empty result`() {
        val contracts = listOf("0x1a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.trades(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid dex contract`() {
        val contracts = listOf("0xa0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val list = api.dex.trades(dexContracts = contracts)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `dex protocol not exist`() {
        val protocols = listOf("IDEXIA")
        val list = api.dex.trades(protocols)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `valid with protocol and contract`() {
        val contracts = listOf("0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208")
        val protocols = listOf("IDEX")
        val list = api.dex.trades(protocols, contracts)
        assertNotNull(list)
    }
}