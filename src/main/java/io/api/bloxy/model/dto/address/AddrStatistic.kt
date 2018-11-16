package io.api.bloxy.model.dto.address

import java.math.BigInteger


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class AddrStatistic(
    val address: String = "",
    val level: String = "",
    val note: String = "",
    val balance_eth: BigInteger = BigInteger.ZERO,
    val type: String = "",
    val send_tx_count: Long = 0,
    val send_to_count: Long = 0,
    val send_to_currencies: Long = 0,
    val send_eth_amount: BigInteger = BigInteger.ZERO,
    val receive_tx_count: Long = 0,
    val receive_from_count: Long = 0,
    val receive_from_currencies: Long = 0,
    val receive_eth_amount: BigInteger = BigInteger.ZERO,
    val first_tx_at: String = "",
    val last_tx_at: String = "",
    val annotation: String = ""
) {
}