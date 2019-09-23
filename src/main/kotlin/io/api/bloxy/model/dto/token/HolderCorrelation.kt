package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
data class HolderCorrelation(
    val address: String = "",
    val annotation: String = "",
    val type: String = "",
    val balances: List<HolderBalance> = emptyList()
): IModel, IAddressModel {

    enum class Mode {
        HOLDERS,
        RECEIVERS
    }

    override val addrType = AddressType.parse(type)

    override fun isEmpty(): Boolean = address.isEmpty() && type.isEmpty() && balances.isEmpty()

    override fun toString(): String {
        return "HolderCorrelation(address='$address', annotation='$annotation', " +
                "type='$type', balances=$balances, addrType=$addrType)"
    }
}