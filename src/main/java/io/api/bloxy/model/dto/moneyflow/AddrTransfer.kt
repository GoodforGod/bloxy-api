package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class AddrTransfer(
    val tx_hash: String = "",
    @Json(name = "tx_time")
    val tx_time_as_string: String = "",
    val direction: String = "",
    val party: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    val party_type: String = "",
    val party_annotation: String = ""
) : IModel {

    @Json(ignored = true)
    val tx_time = ParamConverter.parseDateTime(tx_time_as_string)

    fun haveDateTime() : Boolean = tx_time != null

    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && tx_time_as_string.isEmpty()
    }
}