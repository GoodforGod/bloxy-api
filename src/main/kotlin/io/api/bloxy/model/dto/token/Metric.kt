package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDate
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
data class Metric(
    @Json("date") val dateAsString: String = "",
    @Json("price_usd") val priceUsd: BigDecimal = BigDecimal.ZERO,
    @Json("price_eth") val priceEth: BigDecimal = BigDecimal.ZERO,
    val holders: Int = 0,
    val supply: BigDecimal = BigDecimal.ZERO,
    val gini: BigDecimal = BigDecimal.ZERO,
    val gini10k: BigDecimal = BigDecimal.ZERO,
    val theil: BigDecimal = BigDecimal.ZERO,
    val theil10k: BigDecimal = BigDecimal.ZERO,
    val nakamoto51: Int = 0,
    val index999: Int = 0,
    val index99: Int = 0,
    val index9: Int = 0
) : IModel {

    @Json(ignored = true) val date = dateAsString.asDate()

    override fun isEmpty(): Boolean = date == null && holders == 0 && nakamoto51 == 0 && supply == BigDecimal.ZERO

    override fun toString(): String {
        return "Metric(dateAsString='$dateAsString', priceUsd=$priceUsd, priceEth=$priceEth, holders=$holders, " +
                "supply=$supply, gini=$gini, gini10k=$gini10k, theil=$theil, theil10k=$theil10k, " +
                "nakamoto51=$nakamoto51, index999=$index999, index99=$index99, index9=$index9, date=$date)"
    }
}