package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType
import java.math.BigDecimal


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
    @Json(name = "eth_amount") val ethAmount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "token_amount") val tokenAmount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "token_buyers") val tokenBuyers: Long = 0
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean = tokenAddress.isEmpty() && symbol.isEmpty()
            && transactions == 0L && ethAmount == BigDecimal.ZERO

    override fun toString(): String {
        return "Sale(symbol='$symbol', transactions=$transactions, typeAsString='$typeAsString'," +
                " ethAmount=$ethAmount, tokenAddress='$tokenAddress', tokenAmount=$tokenAmount," +
                " tokenBuyers=$tokenBuyers, tokenType=$tokenType)"
    }
}