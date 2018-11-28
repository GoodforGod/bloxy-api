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
    @Json(name = "tx_time")
    val tx_time_as_string: String = "",
    val token_address: String = "",
    val symbol: String = "",
    @Json(name = "token_type")
    val type_as_string: String = "",
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    val gas_price: Double = .0,
    val gas_value: Double = .0,
    val tx_hash: String = "",
    val token_buyer: String = "",
    val token_sender: String = "",
    val ether_receiver: String = ""
) : IModel, ITokenModel {

    @Json(ignored = true) val tx_time = ParamConverter.parseDateTime(tx_time_as_string)

    fun haveTxTime() : Boolean = tx_time != null

    override val tokenType: TokenType = TokenType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return token_buyer.isEmpty() && token_address.isEmpty() && symbol.isEmpty() && tx_hash.isEmpty()
                && eth_amount == .0 && token_amount == .0
    }
}