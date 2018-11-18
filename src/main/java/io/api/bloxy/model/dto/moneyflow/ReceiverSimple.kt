package io.api.bloxy.model.dto.moneyflow


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class ReceiverSimple(
    val receiver: String = "",
    val receiver_type: String = "",
    val transactions: Long = 0,
    val annotation: String = ""
) {
}