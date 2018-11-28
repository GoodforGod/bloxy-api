package io.api.bloxy.model.dto.address

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IDangerModel
import io.api.bloxy.model.IValidModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.model.dto.DangerLevel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class AddrStatistic(
    val address: String = "",
    @Json(name = "level")
    val level_as_string: String = "",
    val note: String = "",
    val balance_eth: Double = .0,
    @Json(name = "type")
    val typeAsString: String = "",
    val send_tx_count: Long = 0,
    val send_to_count: Long = 0,
    val send_to_currencies: Long = 0,
    val send_eth_amount: Double = .0,
    val receive_tx_count: Long = 0,
    val receive_from_count: Long = 0,
    val receive_from_currencies: Long = 0,
    val receive_eth_amount: Double = .0,
    val first_tx_at: String = "",
    val last_tx_at: String = "",
    val annotation: String = ""
) : IValidModel, IDangerModel, IAddressModel {

    @Json(ignored = true)
    override val level: DangerLevel = DangerLevel.parse(level_as_string)

    override val addressType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean = address.isEmpty()

    override fun isValid(): Boolean = "Address was never used" != note
}