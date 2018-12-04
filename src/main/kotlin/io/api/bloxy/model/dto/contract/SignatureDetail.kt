package io.api.bloxy.model.dto.contract

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
data class SignatureDetail(
    val name: String = "",
    val signature: String = "",
    @Json(name = "signature_hash") val signatureHash: String = "",
    val type: String = "",
    val abi: String = ""
) : IModel {

    fun isEvent() : Boolean = type == "Event"

    fun isFunc() : Boolean = type == "Function"

    override fun isEmpty(): Boolean {
        return signature.isEmpty() && signatureHash.isEmpty() && name.isEmpty()
    }
}