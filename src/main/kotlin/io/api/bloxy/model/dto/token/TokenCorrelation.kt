package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class TokenCorrelation(
    val symbols: String = "",
    val addresses: String = "",
    @Json(name = "transfer_to_uniques")
    val transferToUniques: Long = 0
) : IModel {
    override fun isEmpty(): Boolean {
        return addresses.isEmpty() && transferToUniques == 0L
    }

    override fun toString(): String {
        return "TokenCorrelation(symbols='$symbols', addresses='$addresses', transferToUniques=$transferToUniques)"
    }
}