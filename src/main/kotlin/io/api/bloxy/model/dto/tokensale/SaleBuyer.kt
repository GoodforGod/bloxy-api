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
data class SaleBuyer(
    val transactions: Int = 0,
    @Json(name = "token_amount") val tokenAmount: Double = .0,
    @Json(name = "token_buyer") val tokenBuyer: String = "",
    @Json(name = "eth_amount") val ethAmount: Double = .0,
    @Json(name = "from_time") val fromTimeAsString: String = "",
    @Json(name = "till_time") val tillTimeAsString: String = ""
) : IModel {

    @Json(ignored = true) val fromTime = ParamConverter.parseDateTime(fromTimeAsString)
    @Json(ignored = true) val tillTime = ParamConverter.parseDateTime(tillTimeAsString)

    fun haveFromTime() : Boolean = fromTime != null
    fun haveTillTime() : Boolean = tillTime != null

    override fun isEmpty(): Boolean {
        return tokenBuyer.isEmpty() && transactions == 0 && ethAmount == .0 && tokenAmount == .0
    }

    override fun toString(): String {
        return "SaleBuyer(transactions=$transactions, tokenAmount=$tokenAmount, tokenBuyer='$tokenBuyer', " +
                "ethAmount=$ethAmount, fromTimeAsString='$fromTimeAsString', " +
                "tillTimeAsString='$tillTimeAsString', fromTime=$fromTime, tillTime=$tillTime)"
    }
}