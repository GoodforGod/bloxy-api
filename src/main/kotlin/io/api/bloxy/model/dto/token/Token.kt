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
data class Token(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    @Json(name = "type")
    val typeAsString: String = "",
    val created: String = "",
    val transactions: Long = 0,
    @Json(name = "latest_tx")
    val latestTxAsString: String = ""
) : IModel, ITokenModel {

    @Json(ignored = true) val latestTx = latestTxAsString.asDateTime()

    fun haveLatestTxTime() : Boolean = latestTx != null

    override val tokenType: TokenType = TokenType.parse(typeAsString)

    override fun isEmpty(): Boolean {
        return address.isEmpty() && name.isEmpty() && symbol.isEmpty() && typeAsString.isEmpty()
    }

    override fun toString(): String {
        return "Token(address='$address', name='$name', symbol='$symbol', decimals=$decimals," +
                " typeAsString='$typeAsString', created='$created', transactions=$transactions, " +
                "latestTxAsString='$latestTxAsString', latestTx=$latestTx, tokenType=$tokenType)"
    }
}