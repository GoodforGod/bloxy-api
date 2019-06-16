package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 12.06.2019
 */
data class TokenGroupGraph(
    val address: String = "",
    val annotation: String = "",
    val type: String = ""
) : IModel, IAddressModel {

    override val addrType: AddressType = AddressType.parse(type)

    override fun isEmpty(): Boolean = address.isEmpty() && type.isEmpty()

    override fun toString(): String {
        return "TokenGroupGraph(address='$address', annotation='$annotation', type='$type', addrType=$addrType)"
    }
}