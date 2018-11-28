package io.api.bloxy.model.dto

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Tx(
    val depth: Int = 0,
    val tx_time: String = "",
    val tx_hash: String = "",
    val sender: String = "",
    val receiver: String = "",
    val amount: Double = .0,
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
        return tx_hash.isEmpty() && receiver.isEmpty() && sender.isEmpty()
    }
}