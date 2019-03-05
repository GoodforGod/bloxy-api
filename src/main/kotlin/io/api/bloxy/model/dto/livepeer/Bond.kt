package io.api.bloxy.model.dto.livepeer

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
data class Bond(
    @Json(name = "tx_time") val txTime: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_from") val txFrom: String = "",
    @Json(name = "tx_from_annotation") val txFromAnnotation: String = "",
    val amount: Double = .0,
    val block: Long = -1L,
    val round: Long = 0L
) : IModel {
    override fun isEmpty(): Boolean = block == -1L && txHash.isEmpty() && txFrom.isEmpty()
}