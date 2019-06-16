package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleBuyer(
    val transactions: Int = 0,
    @Json(name = "token_amount") val tokenAmount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "token_buyer") val tokenBuyer: String = "",
    @Json(name = "eth_amount") val ethAmount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "from_time") val fromTimeAsString: String = "",
    @Json(name = "till_time") val tillTimeAsString: String = "",
    @Json(name = "token_buyer_annotation") val tokenBuyerAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val fromTime = fromTimeAsString.asDateTime()
    @Json(ignored = true) val tillTime = tillTimeAsString.asDateTime()

    fun haveFromTime() : Boolean = fromTime != null
    fun haveTillTime() : Boolean = tillTime != null

    override fun isEmpty(): Boolean = tokenBuyer.isEmpty() && transactions == 0
            && ethAmount == BigDecimal.ZERO && tokenAmount == BigDecimal.ZERO

    override fun toString(): String {
        return "SaleBuyer(transactions=$transactions, tokenAmount=$tokenAmount, tokenBuyer='$tokenBuyer', " +
                "ethAmount=$ethAmount, fromTimeAsString='$fromTimeAsString', tillTimeAsString='$tillTimeAsString', " +
                "tokenBuyerAnnotation='$tokenBuyerAnnotation', fromTime=$fromTime, tillTime=$tillTime)"
    }
}