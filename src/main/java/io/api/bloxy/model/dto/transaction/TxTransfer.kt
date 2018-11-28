package io.api.bloxy.model.dto.transaction

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class TxTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val sender: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    @Json(name = "sender_type")
    val senderTypeAsString: String = "",
    @Json(name = "receiver_type")
    val receiverTypeAsString: String = "",
    val sender_annotation: String = "",
    val receiver_annotation: String = ""
) : IModel {

    val receiverType = AddressType.parse(receiverTypeAsString)
    val senderType = AddressType.parse(senderTypeAsString)

    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && sender.isEmpty() && receiver.isEmpty() && amount == .0
    }
}