package io.api.bloxy.model.dto.moneyflow


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Receiver(
    val receiver: String = "",
    val receiver_type: String = "",
    val amount: Double = .0,
    val transactions: Long = 0,
    val annotation: String = ""
) {
}