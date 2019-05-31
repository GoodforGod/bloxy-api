package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class SaleDistribution(
    val address: String = "",
    val transactions: Long = 0L,
    val amount: BigDecimal = BigDecimal.ZERO,
    val annotation: String = "",
    @Json(name = "from_time") val fromTimeAsString: String = "",
    @Json(name = "till_time") val tillTimeAsString: String = ""
) : IModel {

    @Json(ignored = true) val fromTime = fromTimeAsString.asDateTime()
    @Json(ignored = true) val tillTime = tillTimeAsString.asDateTime()

    fun haveFromTime() : Boolean = fromTime != null
    fun haveTillTime() : Boolean = tillTime != null

    override fun isEmpty(): Boolean = address.isEmpty() && transactions == 0L

    override fun toString(): String {
        return "SaleDistribution(address='$address', transactions=$transactions, amount=$amount, " +
                "annotation='$annotation', fromTimeAsString='$fromTimeAsString', " +
                "tillTimeAsString='$tillTimeAsString', fromTime=$fromTime, tillTime=$tillTime)"
    }
}