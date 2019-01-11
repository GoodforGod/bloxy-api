package io.api.bloxy.model.dto.makerdao

import com.beust.klaxon.Json

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
data class Poke(
    val block: Long = 0L,
    val from: String = "",
    val sender: String = "",
    @Json("tx_time") val tx_time: String = "",
    @Json("tx_hash") val tx_hash: String = "",
    @Json("val") val value : Double = .0
)