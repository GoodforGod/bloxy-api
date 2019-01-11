package io.api.bloxy.model.dto.dapp

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel

/**
 * DApp User Statistic
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
data class DAppUser(
    val address: String = "",
    val annotation: String = "",
    val calls: Long = 0L,
    val transfers: Long = 0L,
    @Json(name = "sent_amount") val sentAmount: Double = .0,
    @Json(name = "received_amount") val receivedAmount: Double = .0,
    @Json(name = "first_tx_time") val firstTxTime: String = "",
    @Json(name = "last_tx_time") val lastTxTime: String = "",
    @Json(name = "multi_source") val multiSource: String = ""
) : IModel {
    override fun isEmpty() = address.isEmpty() && calls == 0L && transfers == 0L
}