package io.api.bloxy.model.dto.token

import java.math.BigInteger


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class HolderDetails(
    val address: String = "",
    val address_type: String = "",
    val to_count: Long = 0,
    val uniq_senders: Long = 0,
    val from_count: Long = 0,
    val uniq_receivers: Long = 0,
    val to_amount: BigInteger = BigInteger.ZERO,
    val from_amount: BigInteger = BigInteger.ZERO,
    val first_tx_at: String = "",
    val last_tx_at: String = "",
    val balance: BigInteger = BigInteger.ZERO,
    val annotation: String = ""
) {


}