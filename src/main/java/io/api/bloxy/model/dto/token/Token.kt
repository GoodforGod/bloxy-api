package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class Token(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    @Json(name = "type")
    val type_as_string: String = "",
    val created: String = "",
    val transactions: Long = 0,
    @Json(name = "latest_tx")
    val latest_tx_as_string: String = ""
) : IModel, ITokenModel {

    @Json(ignored = true) val latest_tx = ParamConverter.parseDateTime(latest_tx_as_string)

    fun haveFirstTxTime() : Boolean = latest_tx != null

    override val tokenType: TokenType = TokenType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && name.isEmpty() && symbol.isEmpty() && type_as_string.isEmpty() && transactions == 0L
    }
}