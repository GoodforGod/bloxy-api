package io.api.bloxy.model.dto

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Tx(
    val depth: Int = 0,
    val sender: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "sender_type") val senderTypeAsString: String = "",
    @Json(name = "receiver_type") val receiverTypeAsString: String = "",
    @Json(name = "sender_annotation") val senderAnnotation: String = "",
    @Json(name = "receiver_annotation") val receiverAnnotation: String = ""
) : IModel {

    val receiverType = AddressType.parse(receiverTypeAsString)
    val senderType = AddressType.parse(senderTypeAsString)

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean = txHash.isEmpty() && receiver.isEmpty() && sender.isEmpty()

    override fun toString(): String {
        return "Tx(depth=$depth, sender='$sender', receiver='$receiver', amount=$amount, txHash='$txHash', " +
                "txTimeAsString='$txTimeAsString', senderTypeAsString='$senderTypeAsString', " +
                "receiverTypeAsString='$receiverTypeAsString', senderAnnotation='$senderAnnotation', " +
                "receiverAnnotation='$receiverAnnotation', receiverType=$receiverType, " +
                "senderType=$senderType, txTime=$txTime)"
    }
}