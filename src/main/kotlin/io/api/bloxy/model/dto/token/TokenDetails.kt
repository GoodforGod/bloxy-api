package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenDetails(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    @Json(name = "type")
    val typeAsString: String = ""
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && name.isEmpty() && symbol.isEmpty() && decimals == 0
    }
}