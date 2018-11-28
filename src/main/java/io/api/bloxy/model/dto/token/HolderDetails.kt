package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class HolderDetails(
    val address: String = "",
    @Json(name = "address_type")
    val type_as_string: String = "",
    val to_count: Long = 0,
    val uniq_senders: Long = 0,
    val from_count: Long = 0,
    val uniq_receivers: Long = 0,
    val to_amount: Double = .0,
    val from_amount: Double = .0,
    @Json(name = "first_tx_at")
    val first_tx_at_as_string: String = "",
    @Json(name = "last_tx_at")
    val last_tx_at_as_string: String = "",
    val balance: Double = .0,
    val annotation: String = ""
) : IModel, IAddressModel {

    @Json(ignored = true) val first_tx_at = ParamConverter.parseDateTime(first_tx_at_as_string)
    @Json(ignored = true) val last_tx_at = ParamConverter.parseDateTime(last_tx_at_as_string)

    fun haveFirstTxTime() : Boolean = first_tx_at != null
    fun haveLastTxTime() : Boolean = last_tx_at != null

    override val addressType: AddressType = AddressType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && type_as_string.isEmpty() && to_count == 0L && from_count == 0L
    }
}