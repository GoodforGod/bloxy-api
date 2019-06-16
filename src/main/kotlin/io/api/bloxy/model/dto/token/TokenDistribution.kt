package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class TokenDistribution(
    val amount: BigDecimal = BigDecimal.ZERO,
    val symbol: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "gas_price") val gasPrice: BigDecimal = BigDecimal.ZERO,
    @Json(name = "gas_value") val gasValue: BigDecimal = BigDecimal.ZERO,
    @Json(name = "token_sender") val tokenSender: String = "",
    @Json(name = "token_receiver") val tokenReceiver: String = "",
    @Json(name = "token_origin") val tokenOrigin: String = "",
    @Json(name = "token_buyer") val tokenBuyer: String = "",
    @Json(name = "tx_from") val txFrom: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "token_sender_annotation") val tokenSenderAnnotation: String = "",
    @Json(name = "token_receiver_annotation") val tokenReceiverAnnotation: String = "",
    @Json(name = "token_origin_annotation") val tokenOriginAnnotation: String = "",
    @Json(name = "token_buyer_annotation") val tokenBuyerAnnotation: String = "",
    @Json(name = "tx_from_annotation") val txFromAnnotation: String = ""
) : IModel {

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    override fun isEmpty(): Boolean = symbol.isEmpty() && txFrom.isEmpty() && txHash.isEmpty() && amount == BigDecimal.ZERO

    override fun toString(): String {
        return "TokenDistribution(amount=$amount, symbol='$symbol', txTimeAsString='$txTimeAsString', " +
                "gasPrice=$gasPrice, gasValue=$gasValue, tokenSender='$tokenSender', tokenReceiver='$tokenReceiver', " +
                "tokenOrigin='$tokenOrigin', tokenBuyer='$tokenBuyer', txFrom='$txFrom', txHash='$txHash', " +
                "tokenSenderAnnotation='$tokenSenderAnnotation', tokenReceiverAnnotation='$tokenReceiverAnnotation', " +
                "tokenOriginAnnotation='$tokenOriginAnnotation', tokenBuyerAnnotation='$tokenBuyerAnnotation', " +
                "txFromAnnotation='$txFromAnnotation', txTime=$txTime)"
    }
}