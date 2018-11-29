package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexProtocol(
    val protocol: String = "",
    @Json(name = "smart_contracts") val smartContracts: Int = 0
) : IModel {
    override fun isEmpty(): Boolean {
        return smartContracts == 0 && protocol.isEmpty()
    }
}