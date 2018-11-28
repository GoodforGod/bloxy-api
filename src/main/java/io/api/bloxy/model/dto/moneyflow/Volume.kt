package io.api.bloxy.model.dto.moneyflow

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
data class Volume(
    val address: String = "",
    @Json(name = "address_type")
    val type_as_string: String = "",
    val received_amount: Double = .0,
    val received_txs: Long = 0,
    val sent_amount: Double = .0,
    val sent_txs: Long = 0,
    val annotation: String = ""
) : IModel, IAddressModel {

    override val addressType: AddressType = AddressType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && type_as_string.isEmpty() && received_amount == .0 && sent_amount == .0
    }
}