package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleDaily(
    val transactions: Int = 0,
    @Json(name = "tx_date") val txDateAsString: String = "",
    @Json(name = "eth_amount") val eth_amount: Double = .0,
    @Json(name = "token_amount") val token_amount: Double = .0,
    @Json(name = "token_buyers") val token_buyers: Int = 0
) : IModel {

    @Json(ignored = true)
    val txDate = ParamConverter.parseDate(txDateAsString)

    fun haveDate() : Boolean = txDate != null

    override fun isEmpty(): Boolean {
        return txDateAsString.isEmpty() && transactions == 0 && eth_amount == .0 && token_amount == .0
    }

    override fun toString(): String {
        return "SaleDaily(transactions=$transactions, txDateAsString='$txDateAsString', eth_amount=$eth_amount, " +
                "token_amount=$token_amount, token_buyers=$token_buyers, txDate=$txDate)"
    }
}