package io.api.bloxy.model.dto.tokensale


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleTx(
    val tx_time: String = "",
    val token_address: String = "",
    val symbol: String = "",
    val token_type: String = "",
    val eth_amount: Number = .0,
    val token_amount: Long = 0,
    val gas_price: Number = .0,
    val gas_value: Number = .0,
    val tx_hash: String = "",
    val token_buyer: String ="",
    val token_sender: String = "",
    val ether_receiver: String = ""
) {
}