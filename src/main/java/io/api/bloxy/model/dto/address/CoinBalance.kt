package io.api.bloxy.model.dto.address

import io.api.bloxy.model.IModel


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
) : IModel {

    fun isEth() : Boolean = "ETH" == symbol

    override fun isEmpty(): Boolean {
        return symbol.isEmpty() && token_address.isEmpty()
    }
}