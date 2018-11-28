package io.api.bloxy.model.dto.address

import com.beust.klaxon.Json
import io.api.bloxy.model.IDangerModel
import io.api.bloxy.model.IValidModel
import io.api.bloxy.model.dto.DangerLevel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class AddrDetails(
    val address: String = "",
    @Json(name = "level")
    val level_as_string: String = "",
    val note: String = "",
    val annotation: String = ""
) : IValidModel, IDangerModel {

    @Json(ignored = true)
    override val level: DangerLevel = DangerLevel.parse(level_as_string)

    override fun isEmpty(): Boolean = address.isEmpty()

    override fun isValid(): Boolean = "Address was never used" != note
}