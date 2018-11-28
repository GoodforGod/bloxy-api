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
data class HolderDetails(
    val address: String = "",
    @Json(name = "address_type")
    val typeAsString: String = "",
    val to_count: Long = 0,
    val uniq_senders: Long = 0,
    val from_count: Long = 0,
    val uniq_receivers: Long = 0,
    val to_amount: Double = .0,
    val from_amount: Double = .0,
    val first_tx_at: String = "",
    val last_tx_at: String = "",
    val balance: Double = .0,
    val annotation: String = ""
) : IModel, IAddressModel {

    override val addressType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && typeAsString.isEmpty() && to_count == 0L && from_count == 0L
    }
}