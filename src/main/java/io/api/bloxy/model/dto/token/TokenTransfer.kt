package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenTransfer(
    val amount: Double = .0,
    val symbol: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_from") val txFrom: String = "",
    @Json(name = "gas_price") val gasPrice: Double = .0,
    @Json(name = "gas_value") val gasValue: Double = .0,
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "token_sender") val tokenSender: String = "",
    @Json(name = "token_receiver") val tokenReceiver: String = "",
    @Json(name = "tx_from_annotation") val txFromAnnotation: String = "",
    @Json(name = "token_sender_annotation") val tokenSenderAnnotation: String = "",
    @Json(name = "token_receiver_annotation") val tokenReceiverAnnotation: String = ""
) : IModel {

    @Json(ignored = true)
    val txTime = ParamConverter.parseDateTime(txTimeAsString)

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean {
        return symbol.isEmpty() && txHash.isEmpty() && txFrom.isEmpty()
                && tokenSender.isEmpty() && tokenReceiver.isEmpty()
    }
}