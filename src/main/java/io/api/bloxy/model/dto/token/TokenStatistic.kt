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
data class TokenStatistic(
    @Json(name = "first_transfer")
    val first_transfer_as_string: String = "",
    @Json(name = "latest_transfer")
    val latest_transfer_as_string: String = "",
    val transfers: Long = 0,
    val senders: Long = 0,
    val receivers: Long = 0,
    val transfered_amount: Double = .0,
    val token_annotation: String = "",
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    @Json(name = "type")
    val type_as_string: String = "",
    val holders_count: Long = 0,
    val circulating_supply: Double = .0
) : IModel, ITokenModel {

    @Json(ignored = true) val first_transfer = ParamConverter.parseDateTime(first_transfer_as_string)
    @Json(ignored = true) val latest_transfer = ParamConverter.parseDateTime(latest_transfer_as_string)

    fun haveFirstTransferTime() : Boolean = first_transfer != null
    fun haveLastTransferTime() : Boolean = latest_transfer != null

    override val tokenType: TokenType = TokenType.parse(type_as_string)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && first_transfer_as_string.isEmpty() && latest_transfer_as_string.isEmpty()
                && name.isEmpty() && symbol.isEmpty() && transfers == 0L && decimals == 0
    }
}