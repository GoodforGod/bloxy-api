package io.api.bloxy.model.dto.tokensale

import io.api.bloxy.model.IModel


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
) : IModel {
    override fun isEmpty(): Boolean {
        return token_buyer.isEmpty() && token_address.isEmpty() && symbol.isEmpty() && tx_hash.isEmpty()
                && eth_amount == .0 && token_amount == 0L
    }
}