package io.api.bloxy.model.dto.token


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenTransfer(
    val tx_time: String?,
    val amount: Number?,
    val symbol: String?,
    val token_sender: String?,
    val token_receiver: String?,
    val tx_from: String?,
    val gas_price: Number?,
    val gas_value: Number?,
    val tx_hash: String?,
    val token_sender_annotation: String?,
    val token_receiver_annotation: String?,
    val tx_from_annotation: String?
) {
}