package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class Holder(
    val address: String = "",
    @Json(name = "address_type")
    val type_as_string: String = "",
    val annotation: String = "",
    val balance: Double = .0
) : IModel, IAddressModel {

    override val addressType: AddressType = AddressType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && balance == .0
    }
}