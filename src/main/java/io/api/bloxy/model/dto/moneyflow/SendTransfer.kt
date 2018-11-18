package io.api.bloxy.model.dto.moneyflow


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SendTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    val receiver_type: String = "",
    val receiver_annotation: String= ""
) {
}