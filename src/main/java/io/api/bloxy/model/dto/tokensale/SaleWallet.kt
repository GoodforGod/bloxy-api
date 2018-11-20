package io.api.bloxy.model.dto.tokensale

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleWallet(
    val address: String = "",
    val amount_received: Double = .0,
    val transfers_received: Int = 0,
    val amount_sent: Double = .0,
    val transfers_sent: Int = 0,
    val from_time: String = "",
    val till_time: String = "",
    val balance: Double = .0
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && amount_received == .0 && amount_sent == .0 && amount_received == .0 && balance == .0
    }
}