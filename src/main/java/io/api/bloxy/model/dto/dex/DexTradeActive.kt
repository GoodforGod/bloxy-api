package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


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
    val from_time: String = "",
    val till_time: String = "",
    val contract_type: String = "",
    @Json(name = "address_type")
    val typeAsString: String = "",
    val address_annotation: String = ""
) : IModel, IAddressModel {

    override val addressType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && typeAsString.isEmpty()
    }
}