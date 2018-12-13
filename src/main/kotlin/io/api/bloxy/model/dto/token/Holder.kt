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
    val typeAsString: String = "",
    val annotation: String = "",
    val balance: Double = .0
) : IModel, IAddressModel {

    override val addrType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean = address.isEmpty() && balance == .0

    override fun toString(): String {
        return "Holder(address='$address', typeAsString='$typeAsString', " +
                "annotation='$annotation', balance=$balance, addrType=$addrType)"
    }
}