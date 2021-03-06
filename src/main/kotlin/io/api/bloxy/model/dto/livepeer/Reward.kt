package io.api.bloxy.model.dto.livepeer

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
data class Reward(
    @Json(name = "tx_time") val txTime: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "recipient_annotation") val recipientAnnotation: String = "",
    val recipient: String = "",
    val amount: Double = .0,
    val block: Long = -1L,
    val round: Double = .0
) : IModel {
    override fun isEmpty(): Boolean = txTime.isEmpty() && amount == .0 && block == -1L
}