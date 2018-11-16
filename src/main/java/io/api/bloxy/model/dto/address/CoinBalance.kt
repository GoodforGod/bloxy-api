package io.api.bloxy.model.dto.address

import java.math.BigInteger


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
    val sent_amount: BigInteger = BigInteger.ZERO,
    val received_txs: Long = 0,
    val received_amount: BigInteger = BigInteger.ZERO,
    val balance: BigInteger = BigInteger.ZERO
) {
}