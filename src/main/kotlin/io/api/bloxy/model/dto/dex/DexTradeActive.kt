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
    val dexes: Int = 0,
    val currencies: Int = 0,
    @Json(name = "from_time") val fromTimeAsString: String = "",
    @Json(name = "till_time") val tillTimeAsString: String = "",
    @Json(name = "maker_trades") val makerTrades: Int = 0,
    @Json(name = "taker_trades") val takerTrades: Int = 0,
    @Json(name = "contract_type") val contractType: String = "",
    @Json(name = "type_as_string") val typeAsString: String = "",
    @Json(name = "address_annotation") val addressAnnotation: String = ""
) : IModel, IAddressModel {

    @Json(ignored = true) val fromTime = ParamConverter.parseDateTime(fromTimeAsString)
    @Json(ignored = true) val tillTime = ParamConverter.parseDateTime(tillTimeAsString)

    fun haveFromTime() : Boolean = fromTime != null
    fun haveTillTime() : Boolean = tillTime != null

    override val addrType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && typeAsString.isEmpty()
    }

    override fun toString(): String {
        return "DexTradeActive(address='$address', dexes=$dexes, currencies=$currencies, " +
                "fromTimeAsString='$fromTimeAsString', tillTimeAsString='$tillTimeAsString', makerTrades=$makerTrades, " +
                "takerTrades=$takerTrades, contractType='$contractType', typeAsString='$typeAsString', " +
                "addressAnnotation='$addressAnnotation', fromTime=$fromTime, tillTime=$tillTime, addrType=$addrType)"
    }
}