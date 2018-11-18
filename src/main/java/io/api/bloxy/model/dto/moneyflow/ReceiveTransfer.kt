package io.api.bloxy.model.dto.moneyflow


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class ReceiveTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val sender: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    val sender_type: String = "",
    val sender_annotation: String = ""
) {
}