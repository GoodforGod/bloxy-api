package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Sale(
    val symbol: String = "",
    val transactions: Long = 0,
    @Json(name = "token_type") val typeAsString: String = "",
    @Json(name = "eth_amount") val ethAmount: Number = .0,
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "token_amount") val tokenAmount: Number = .0,
    @Json(name = "token_buyers") val tokenBuyers: Long = 0
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean = tokenAddress.isEmpty() && symbol.isEmpty() && transactions == 0L && ethAmount == .0

    override fun toString(): String {
        return "Sale(symbol='$symbol', transactions=$transactions, typeAsString='$typeAsString'," +
                " ethAmount=$ethAmount, tokenAddress='$tokenAddress', tokenAmount=$tokenAmount," +
                " tokenBuyers=$tokenBuyers, tokenType=$tokenType)"
    }
}