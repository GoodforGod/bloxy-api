package io.api.bloxy.model.dto.address


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class CoinBalance(
    val symbol: String = "",
    val token_address: String = "",
    val sent_txs: Long = 0,
    val sent_amount: Double = .0,
    val received_txs: Long = 0,
    val received_amount: Double = .0,
    val balance: Double = .0
) {
}