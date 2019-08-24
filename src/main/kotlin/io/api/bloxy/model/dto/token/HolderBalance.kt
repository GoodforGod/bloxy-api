package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
data class HolderBalance(
    @Json("token_type") val tokenTypeAsString: String = "",
    val symbol: String = "",
    val address: String = "",
    val inbound: BigDecimal = BigDecimal.ZERO,
    val outbound: BigDecimal = BigDecimal.ZERO,
    val balance: BigDecimal = BigDecimal.ZERO
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(tokenTypeAsString)

    override fun isEmpty(): Boolean = tokenType == TokenType.UNKNOWN && symbol.isEmpty() && address.isEmpty()

    override fun toString(): String {
        return "HolderBalance(tokenTypeAsString='$tokenTypeAsString', symbol='$symbol', address='$address', " +
                "inbound=$inbound, outbound=$outbound, balance=$balance, tokenType=$tokenType)"
    }
}