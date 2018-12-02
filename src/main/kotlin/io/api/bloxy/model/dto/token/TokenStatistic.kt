package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.model.ITokenModel
import io.api.bloxy.model.dto.TokenType
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenStatistic(
    val transfers: Long = 0,
    val receivers: Long = 0,
    val address: String = "",
    val symbol: String = "",
    val name: String = "",
    val senders: Long = 0,
    val decimals: Int = 0,
    @Json(name = "type") val typeAsString: String = "",
    @Json(name = "holders_count") val holdersCount: Long = 0,
    @Json(name = "token_annotation") val tokenAnnotation: String = "",
    @Json(name = "transfered_amount") val transferedAmount: Double = .0,
    @Json(name = "circulating_supply") val circulatingSupply: Double = .0,
    @Json(name = "first_transfer_as_string") val firstTransferAsString: String = "",
    @Json(name = "latest_transfer_as_string") val latestTransferAsString: String = ""
) : IModel, ITokenModel {

    @Json(ignored = true) val firstTransfer = firstTransferAsString.asDateTime()
    @Json(ignored = true) val latestTransfer = latestTransferAsString.asDateTime()

    fun haveFirstTransferTime() : Boolean = firstTransfer != null
    fun haveLastTransferTime() : Boolean = latestTransfer != null

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && symbol.isEmpty() && transfers == 0L && decimals == 0
    }

    override fun toString(): String {
        return "TokenStatistic(transfers=$transfers, receivers=$receivers, address='$address', " +
                "symbol='$symbol', name='$name', senders=$senders, decimals=$decimals, " +
                "typeAsString='$typeAsString', firstTransferAsString='$firstTransferAsString', " +
                "latestTransferAsString='$latestTransferAsString', transferedAmount=$transferedAmount, " +
                "tokenAnnotation='$tokenAnnotation', holdersCount=$holdersCount, circulatingSupply=$circulatingSupply, " +
                "firstTransfer=$firstTransfer, latestTransfer=$latestTransfer, tokenType=$tokenType)"
    }
}