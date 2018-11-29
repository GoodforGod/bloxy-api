package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType
import io.api.bloxy.util.ParamConverter


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
    @Json(name = "ether_receiver") val etherReceiver: String = ""
) : IModel, ITokenModel {

    @Json(ignored = true) val txTime = ParamConverter.parseDateTime(txTimeAsString)

    fun haveTxTime() : Boolean = txTime != null

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return tokenBuyer.isEmpty() && tokenAddress.isEmpty() && symbol.isEmpty() && txHash.isEmpty()
                && ethAmount == .0 && tokenAmount == .0
    }
}