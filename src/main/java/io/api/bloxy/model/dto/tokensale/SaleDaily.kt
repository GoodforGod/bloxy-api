package io.api.bloxy.model.dto.tokensale


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleDaily(
    val tx_date: String = "",
    val transactions: Int = 0,
    val eth_amount: Number = .0,
    val token_amount: Int = 0,
    val token_buyers: Int = 0
) {
}