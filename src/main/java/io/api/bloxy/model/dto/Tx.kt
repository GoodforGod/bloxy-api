package io.api.bloxy.model.dto


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Tx(
    val depth: Int = 0,
    val tx_time: String = "",
    val tx_hash: String = "",
    val sender: String = "",
    val receiver: String = "",
    val amount: Number = .0,
    val sender_type: String = "",
    val sender_annotation: String = "",
    val receiver_type: String = "",
    val receiver_annotation: String = ""
) {
}