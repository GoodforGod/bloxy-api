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
    val levelAsString: String = "",
    val annotation: String = "",
    @Json("note") private val noteParsed: String?
) : IValidModel, IDangerModel {

    @Json(ignored = true)
    val note: String = noteParsed ?: ""
    @Json(ignored = true)
    override val level: DangerLevel = DangerLevel.parse(levelAsString)

    override fun isEmpty(): Boolean = address.isEmpty()

    override fun isValid(): Boolean = "Address was never used" != note && "Address never received currency" != note

    override fun toString(): String {
        return "AddrDetails(address='$address', levelAsString='$levelAsString', " +
                "note='$note', annotation='$annotation', level=$level)"
    }
}