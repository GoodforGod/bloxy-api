package io.api.bloxy.model.dto.dex

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
data class DexTradeActive(
    val address: String = "",
    val maker_trades: Int = 0,
    val taker_trades: Int = 0,
    val dexes: Int = 0,
    val currencies: Int = 0,
    @Json(name = "from_time")
    val from_time_as_string: String = "",
    @Json(name = "till_time")
    val till_time_as_string: String = "",
    val contract_type: String = "",
    @Json(name = "address_type")
    val type_as_string: String = "",
    val address_annotation: String = ""
) : IModel, IAddressModel {

    @Json(ignored = true) val from_time = ParamConverter.parseDateTime(from_time_as_string)
    @Json(ignored = true) val till_time = ParamConverter.parseDateTime(till_time_as_string)

    fun haveFromTime() : Boolean = from_time != null
    fun haveTillTime() : Boolean = till_time != null

    override val addressType: AddressType = AddressType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && type_as_string.isEmpty()
    }
}