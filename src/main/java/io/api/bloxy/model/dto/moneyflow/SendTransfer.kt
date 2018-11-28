package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SendTransfer(
    val tx_hash: String = "",
    @Json(name = "tx_time")
    val tx_time_as_string: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    @Json(name = "receiver_type")
    val receiver_type_as_string: String = "",
    val receiver_annotation: String = ""
) : IModel, IAddressModel {

    @Json(ignored = true)
    val tx_datetime = ParamConverter.parseDateTime(tx_time_as_string)

    fun haveDateTime() : Boolean = tx_datetime != null

    override val addressType: AddressType = AddressType.parse(receiver_type_as_string)

    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && tx_time_as_string.isEmpty()
    }
}