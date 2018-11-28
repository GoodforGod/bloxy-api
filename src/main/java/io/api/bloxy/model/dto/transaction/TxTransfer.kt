package io.api.bloxy.model.dto.transaction

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class TxTransfer(
    val tx_hash: String = "",
    @Json(name = "tx_time")
    val tx_time_as_string: String = "",
    val sender: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    @Json(name = "sender_type")
    val sender_type_as_string: String = "",
    @Json(name = "receiver_type")
    val receiver_type_as_string: String = "",
    val sender_annotation: String = "",
    val receiver_annotation: String = ""
) : IModel {

    val receiverType = AddressType.parse(receiver_type_as_string)
    val senderType = AddressType.parse(sender_type_as_string)

    @Json(ignored = true) val tx_time = ParamConverter.parseDateTime(tx_time_as_string)

    fun haveTxTime() : Boolean = tx_time != null

    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && sender.isEmpty() && receiver.isEmpty() && amount == .0
    }
}