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
data class SaleWallet(
    val token_address: String = "",
    val symbol: String = "",
    @Json(name = "token_type")
    val typeAsString: String = "",
    val transactions: Long = 0,
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    val token_buyers: Int = 0
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return token_address.isEmpty() && symbol.isEmpty() && transactions == 0L && token_buyers == 0
    }
}