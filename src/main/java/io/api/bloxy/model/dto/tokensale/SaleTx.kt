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
data class SaleTx(
    val tx_time: String = "",
    val token_address: String = "",
    val symbol: String = "",
    @Json(name = "token_type")
    val typeAsString: String = "",
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    val gas_price: Double = .0,
    val gas_value: Double = .0,
    val tx_hash: String = "",
    val token_buyer: String = "",
    val token_sender: String = "",
    val ether_receiver: String = ""
) : IModel, ITokenModel {

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return token_buyer.isEmpty() && token_address.isEmpty() && symbol.isEmpty() && tx_hash.isEmpty()
                && eth_amount == .0 && token_amount == .0
    }
}