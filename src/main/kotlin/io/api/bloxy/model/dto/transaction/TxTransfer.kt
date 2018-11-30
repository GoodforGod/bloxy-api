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
    val sender: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    @Json(name = "tx_time") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "tx_time") val tokenSymbol: String = "",
    @Json(name = "tx_time") val tokenAddress: String = "",
    @Json(name = "tx_time") val senderAnnotation: String = "",
    @Json(name = "tx_time") val receiverAnnotation: String = "",
    @Json(name = "sender_type") val senderTypeAsString: String = "",
    @Json(name = "receiver_type") val receiverTypeAsString: String = ""
) : IModel {

    val receiverType = AddressType.parse(receiverTypeAsString)
    val senderType = AddressType.parse(senderTypeAsString)

    @Json(ignored = true) val tx_time = ParamConverter.parseDateTime(txTimeAsString)

    fun haveTxTime() : Boolean = tx_time != null

    override fun isEmpty(): Boolean {
        return txHash.isEmpty() && sender.isEmpty() && receiver.isEmpty() && amount == .0
    }
}