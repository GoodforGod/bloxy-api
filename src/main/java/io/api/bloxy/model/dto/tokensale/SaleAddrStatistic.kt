package io.api.bloxy.model.dto.tokensale


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleAddrStatistic(
    val ether_receiver: String = "",
    val token_sender: String = "",
    val transactions: Int = 0,
    val eth_amount: Number = .0,
    val token_amount: Long = 0,
    val token_buyers: Int = 0,
    val from_time: String = "",
    val till_time: String =""
) {
}