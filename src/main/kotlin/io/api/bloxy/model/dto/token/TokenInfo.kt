package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class TokenInfo(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val txs: Long = 0,
    @Json(name = "token_type")
    val typeAsString: String = ""
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean = address.isEmpty() && symbol.isEmpty() && txs == 0L

    override fun toString(): String {
        return "TokenInfo(address='$address', name='$name', symbol='$symbol', " +
                "txs=$txs, typeAsString='$typeAsString', tokenType=$tokenType)"
    }
}