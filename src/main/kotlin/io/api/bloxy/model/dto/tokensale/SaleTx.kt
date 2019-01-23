package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleTx(
    val symbol: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "gas_price") val gasPrice: Double = .0,
    @Json(name = "gas_value") val gasValue: Double = .0,
    @Json(name = "token_type") val typeAsString: String = "",
    @Json(name = "eth_amount") val ethAmount: Double = .0,
    @Json(name = "token_buyer") val tokenBuyer: String = "",
    @Json(name = "token_sender") val tokenSender: String = "",
    @Json(name = "token_amount") val tokenAmount: Double = .0,
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "ether_receiver") val etherReceiver: String = "",
    @Json(name = "token_buyer_annotation") val tokenBuyerAnnotation: String = "",
    @Json(name = "token_sender_annotation") val tokenSenderAnnotation: String = "",
    @Json(name = "ether_receiver_annotation") val etherReceiverAnnotation: String = ""
) : IModel, ITokenModel {

    @Json(ignored = true) val txTime = txTimeAsString.asDateTime()

    fun haveTxTime() : Boolean = txTime != null

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean = tokenBuyer.isEmpty() && tokenAddress.isEmpty() && txHash.isEmpty()

    override fun toString(): String {
        return "SaleTx(symbol='$symbol', txTimeAsString='$txTimeAsString', txHash='$txHash', gasPrice=$gasPrice, " +
                "gasValue=$gasValue, typeAsString='$typeAsString', ethAmount=$ethAmount, tokenBuyer='$tokenBuyer', " +
                "tokenSender='$tokenSender', tokenAmount=$tokenAmount, tokenAddress='$tokenAddress', " +
                "etherReceiver='$etherReceiver', tokenBuyerAnnotation='$tokenBuyerAnnotation', " +
                "tokenSenderAnnotation='$tokenSenderAnnotation', etherReceiverAnnotation='$etherReceiverAnnotation', " +
                "txTime=$txTime, tokenType=$tokenType)"
    }
}