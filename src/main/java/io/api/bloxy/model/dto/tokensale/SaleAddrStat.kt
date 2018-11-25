package io.api.bloxy.model.dto.tokensale

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleAddrStat(
    val ether_receiver: String = "",
    val token_sender: String = "",
    val transactions: Int = 0,
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    val token_buyers: Int = 0,
    val from_time: String = "",
    val till_time: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return ether_receiver.isEmpty() && token_sender.isEmpty() && transactions == 0 && eth_amount == .0
    }
}