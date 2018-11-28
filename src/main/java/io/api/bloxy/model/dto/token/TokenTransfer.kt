package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenTransfer(
    @Json(name = "tx_time")
    val tx_time_as_string: String = "",
    val amount: Double = .0,
    val symbol: String = "",
    val token_sender: String = "",
    val token_receiver: String = "",
    val tx_from: String = "",
    val gas_price: Double = .0,
    val gas_value: Double = .0,
    val tx_hash: String = "",
    val token_sender_annotation: String = "",
    val token_receiver_annotation: String = "",
    val tx_from_annotation: String = ""
) : IModel {

    @Json(ignored = true)
    val tx_time = ParamConverter.parseDateTime(tx_time_as_string)

    fun haveDateTime() : Boolean = tx_time != null

    override fun isEmpty(): Boolean {
        return symbol.isEmpty() && tx_hash.isEmpty() && tx_from.isEmpty()
                && token_sender.isEmpty() && token_receiver.isEmpty()
    }
}