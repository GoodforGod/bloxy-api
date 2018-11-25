package io.api.bloxy.model.dto.tokensale

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleDaily(
    val tx_date: String = "",
    val transactions: Int = 0,
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    val token_buyers: Int = 0
) : IModel {
    override fun isEmpty(): Boolean {
        return tx_date.isEmpty() && transactions == 0 && eth_amount == .0 && token_amount == .0
    }
}