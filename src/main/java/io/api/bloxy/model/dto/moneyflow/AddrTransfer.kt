package io.api.bloxy.model.dto.moneyflow

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class AddrTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val direction: String = "",
    val party: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    val party_type: String = "",
    val party_annotation: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && tx_time.isEmpty()
    }
}