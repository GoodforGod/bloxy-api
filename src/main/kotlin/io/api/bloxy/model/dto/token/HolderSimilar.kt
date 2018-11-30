package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class HolderSimilar(
    val address: String = "",
    val symbol: String = "",
    @Json(name = "common_holders")
    val commonHolders: Long = 0,
    val percentage: Double = .0
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && symbol.isEmpty() && commonHolders == 0L
    }
}