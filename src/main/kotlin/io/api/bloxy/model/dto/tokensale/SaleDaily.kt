package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDate


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleDaily(
    val transactions: Int = 0,
    @Json(name = "tx_date") val txDateAsString: String = "",
    @Json(name = "eth_amount") val ethAmount: Double = .0,
    @Json(name = "token_amount") val tokenAmount: Double = .0,
    @Json(name = "token_buyers") val tokenBuyers: Int = 0
) : IModel {

    @Json(ignored = true)
    val txDate = txDateAsString.asDate()

    fun haveDate() : Boolean = txDate != null

    override fun isEmpty(): Boolean = txDateAsString.isEmpty() && transactions == 0 && tokenAmount == .0

    override fun toString(): String {
        return "SaleDaily(transactions=$transactions, txDateAsString='$txDateAsString', ethAmount=$ethAmount, " +
                "tokenAmount=$tokenAmount, tokenBuyers=$tokenBuyers, txDate=$txDate)"
    }
}