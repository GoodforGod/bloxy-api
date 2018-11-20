package io.api.bloxy.model.dto.transaction

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class TxTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val sender: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    val sender_type: String = "",
    val receiver_type: String = "",
    val sender_annotation: String = "",
    val receiver_annotation: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && sender.isEmpty() && receiver.isEmpty() && amount == .0
    }
}