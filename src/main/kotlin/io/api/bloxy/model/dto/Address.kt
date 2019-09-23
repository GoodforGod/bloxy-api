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
    val typeAsString: String = "",
    val amount: Double = .0,
    val annotation: String = ""
) : IModel, IAddressModel {

    override val addrType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean = address.isEmpty()

    override fun toString(): String {
        return "Address(address='$address', typeAsString='$typeAsString', amount=$amount, " +
                "annotation='$annotation', addrType=$addrType)"
    }
}