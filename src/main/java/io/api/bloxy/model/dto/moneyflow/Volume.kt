package io.api.bloxy.model.dto.moneyflow

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Volume(
    val address: String = "",
    val address_type: String = "",
    val received_amount: Double = .0,
    val received_txs: Long = 0,
    val sent_amount: Double = .0,
    val sent_txs: Long = 0,
    val annotation: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && address_type.isEmpty() && received_amount == .0 && sent_amount == .0
    }
}