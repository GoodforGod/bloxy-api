package io.api.bloxy.model.dto.dapp

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime

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
    @Json(name = "first_tx_time") val firstTxAtAsString: String = "",
    @Json(name = "last_tx_time") val lastTxAtAsString: String = "",
    @Json(name = "multi_source") val multiSource: String = "",
    @Json(name = "received_amount") val receivedAmount: Double = .0
) : IModel {

    @Json(ignored = true) val firstTxTime = firstTxAtAsString.asDateTime()
    @Json(ignored = true) val lastTxTime = lastTxAtAsString.asDateTime()

    fun haveFirstTxTime() : Boolean = firstTxTime != null
    fun haveLastTxTime() : Boolean = lastTxTime != null

    override fun isEmpty() = address.isEmpty() && calls == 0L && transfers == 0L
}