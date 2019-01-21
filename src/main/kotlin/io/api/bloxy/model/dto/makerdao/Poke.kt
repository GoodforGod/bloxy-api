package io.api.bloxy.model.dto.makerdao

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
data class Poke(
    val block: Long = -1L,
    val from: String = "",
    val sender: String = "",
    @Json("tx_time") val txTimeAsString: String = "",
    @Json("tx_hash") val txHash: String = ""
//    @Json("val") val value: Double = .0
) : IModel {

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    fun haveTxTime(): Boolean = txTime != null

    override fun isEmpty(): Boolean = block == -1L && from.isEmpty() && sender.isEmpty()
}