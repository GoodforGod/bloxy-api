package io.api.bloxy.model.dto.transaction

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


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
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "sender_type") val senderTypeAsString: String = "",
    @Json(name = "token_symbol") val tokenSymbol: String = "",
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "receiver_type") val receiverTypeAsString: String = "",
    @Json(name = "sender_annotation") val senderAnnotation: String = "",
    @Json(name = "receiver_annotation") val receiverAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val txTime = txTimeAsString.asDateTime()

    val receiverType = AddressType.parse(receiverTypeAsString)
    val senderType = AddressType.parse(senderTypeAsString)

    fun isEth() : Boolean = tokenSymbol == "ETH"

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean {
        return txHash.isEmpty() && sender.isEmpty() && receiver.isEmpty() && amount == .0
    }

    override fun toString(): String {
        return "TxTransfer(sender='$sender', receiver='$receiver', amount=$amount, txHash='$txHash', " +
                "txTimeAsString='$txTimeAsString', tokenSymbol='$tokenSymbol', tokenAddress='$tokenAddress', " +
                "senderAnnotation='$senderAnnotation', receiverAnnotation='$receiverAnnotation', " +
                "senderTypeAsString='$senderTypeAsString', receiverTypeAsString='$receiverTypeAsString', " +
                "receiverType=$receiverType, senderType=$senderType, tx_time=$txTime)"
    }
}