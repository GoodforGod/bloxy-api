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
data class ReceiveTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val sender: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    @Json(name = "sender_type")
    val senderTypeAsString: String = "",
    val sender_annotation: String = ""
) : IModel, IAddressModel {

    override val addressType: AddressType = AddressType.parse(senderTypeAsString)

    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && tx_time.isEmpty()
    }
}