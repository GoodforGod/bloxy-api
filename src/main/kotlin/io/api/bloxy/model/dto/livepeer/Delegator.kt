package io.api.bloxy.model.dto.livepeer

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
data class Delegator(
    val address: String = "",
    val amount: Double = .0,
    val bonded: Double = .0,
    val rebonded: Double = .0,
    val unbonded: Double = .0,
    val count: Long = 0L,
    val delegates: Long = 0L,
    @Json(name = "first_tx_at") val firstTxAt: String = "",
    @Json(name = "last_tx_at") val lastTxAt: String = "",
    @Json(name = "address_annotation") val addressAnnotation: String = ""
) : IModel {
    override fun isEmpty(): Boolean = count == 0L && address.isEmpty() && delegates == 0L
}