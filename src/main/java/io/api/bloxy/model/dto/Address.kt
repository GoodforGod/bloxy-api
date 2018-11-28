package io.api.bloxy.model.dto

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Address(
    val address: String = "",
    @Json(name = "address_type")
    val type_as_string: String = "",
    val amount: Double = .0,
    val annotation: String = ""
) : IModel, IAddressModel {

    override val addressType: AddressType = AddressType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty()
    }
}