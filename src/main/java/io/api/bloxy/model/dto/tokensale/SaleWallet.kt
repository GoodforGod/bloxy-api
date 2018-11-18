package io.api.bloxy.model.dto.tokensale


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleWallet(
    val address: String = "",
    val amount_received: Number = .0,
    val transfers_received: Int = 0,
    val amount_sent: Number = .0,
    val transfers_sent: Int = 0,
    val from_time: String = "",
    val till_time: String = "",
    val balance: Number = .0
) {
}