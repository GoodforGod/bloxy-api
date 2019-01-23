package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleAddrStat(
    val transactions: Int = 0,
    @Json(name = "from_time") val fromTimeAsString: String = "",
    @Json(name = "till_time") val tillTimeAsString: String = "",
    @Json(name = "eth_amount") val ethAmount: Double = .0,
    @Json(name = "token_sender") val tokenSender: String = "",
    @Json(name = "token_amount") val tokenAmount: Double = .0,
    @Json(name = "token_buyers") val tokenBuyers: Int = 0,
    @Json(name = "ether_receiver") val etherReceiver: String = "",
    @Json(name = "token_sender_annotation") val tokenSenderAnnotation: String = "",
    @Json(name = "ether_receiver_annotation") val etherReceiverAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val fromTime = fromTimeAsString.asDateTime()
    @Json(ignored = true) val tillTime = tillTimeAsString.asDateTime()

    fun haveFromTime() : Boolean = fromTime != null
    fun haveTillTime() : Boolean = tillTime != null

    override fun isEmpty(): Boolean = etherReceiver.isEmpty() && tokenSender.isEmpty() && transactions == 0

    override fun toString(): String {
        return "SaleAddrStat(transactions=$transactions, fromTimeAsString='$fromTimeAsString', " +
                "tillTimeAsString='$tillTimeAsString', ethAmount=$ethAmount, tokenSender='$tokenSender', " +
                "tokenAmount=$tokenAmount, tokenBuyers=$tokenBuyers, etherReceiver='$etherReceiver', " +
                "tokenSenderAnnotation='$tokenSenderAnnotation', etherReceiverAnnotation='$etherReceiverAnnotation', " +
                "fromTime=$fromTime, tillTime=$tillTime)"
    }
}