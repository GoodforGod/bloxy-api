package io.api.bloxy.model.dto.dapp

import com.beust.klaxon.Json

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
data class MultiSource(
    val annotation: String = "",
    @Json(name = "multi_source") val multi_source: String = "",
    @Json(name = "source_amount") val sourceAmount: Double = .0,
    @Json(name = "created_address_count") val createdAddressCount: Long = 0L
) {
}