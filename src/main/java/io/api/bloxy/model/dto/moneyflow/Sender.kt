package io.api.bloxy.model.dto.moneyflow


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Sender(
    val sender: String = "",
    val sender_type: String = "",
    val amount: Double = .0,
    val transactions: Long = 0,
    val annotation: String = ""
) {
}