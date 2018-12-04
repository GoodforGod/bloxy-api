package io.api.bloxy.model.dto.contract

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
data class Event(
    val count: Long = 0L,
    val name: String = "",
    val signature: String = "",
    @Json(name = "signature_hash") val signatureHash: String = "",
    @Json(name = "first_tx_at") val firstTxAtAsString: String = "",
    @Json(name = "last_tx_at") val lastTxAtAsString: String = ""
) : IModel {

    @Json(ignored = true) val firstTxAt = firstTxAtAsString.asDateTime()
    @Json(ignored = true) val lastTxAt = lastTxAtAsString.asDateTime()

    fun haveFirstTxAt() : Boolean = firstTxAt != null
    fun haveLastTxAt() : Boolean = lastTxAt != null

    override fun isEmpty(): Boolean {
        return name.isEmpty() && signature.isEmpty() && signatureHash.isEmpty()
    }
}