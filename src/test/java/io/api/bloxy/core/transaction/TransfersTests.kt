package io.api.bloxy.core.transaction

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TransfersTests : Tester() {

    @Test
    fun valid() {
        val list = listOf("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val transfer = api.tx.transfers(list)
        assertNotNull(transfer)
        assertFalse(transfer.isEmpty())
        assertFalse(transfer[0].isEmpty())
        assertNotNull(transfer[0].amount)
        assertNotNull(transfer[0].receiver)
        assertNotNull(transfer[0].receiverType)
        assertNotNull(transfer[0].receiver_annotation)
        assertNotNull(transfer[0].receiver_type_as_string)
        assertNotNull(transfer[0].sender)
        assertNotNull(transfer[0].senderType)
        assertNotNull(transfer[0].sender_annotation)
        assertNotNull(transfer[0].sender_type_as_string)
        assertNotNull(transfer[0].token_address)
        assertNotNull(transfer[0].token_symbol)
        assertNotNull(transfer[0].tx_hash)
        assertNotNull(transfer[0].tx_time)
        assertNotNull(transfer[0].tx_time_as_string)
    }

    @Test
    fun `non exist address empty result`() {
        val list = listOf("0x12a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val transfer = api.tx.transfers(list)
        assertNotNull(transfer)
        assertTrue(transfer.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val list = listOf("0x5a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        api.tx.transfers(list)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.tx.transfers(emptyList())
    }
}