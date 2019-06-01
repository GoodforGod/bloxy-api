package io.api.bloxy.model.dto.address

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDate
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class AddrDaily(
    @Json(name = "date") val dateAsString: String = "",
    val total: String = "",
    val symbol: String = "",
    val deposit: BigDecimal = BigDecimal.ZERO,
    val withdraw: BigDecimal = BigDecimal.ZERO,
    val change: BigDecimal = BigDecimal.ZERO,
    val profit: BigDecimal = BigDecimal.ZERO,
    val deposited: BigDecimal = BigDecimal.ZERO,
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "deposit_value") val depositValue: BigDecimal = BigDecimal.ZERO,
    @Json(name = "withdraw_value") val withdrawValue: BigDecimal = BigDecimal.ZERO,
    @Json(name = "daily_price") val dailyPrice: BigDecimal = BigDecimal.ZERO,
    @Json(name = "daily_balance") val dailyBalance: BigDecimal = BigDecimal.ZERO,
    @Json(name = "daily_value") val dailyValue: BigDecimal = BigDecimal.ZERO,
    @Json(name = "daily_profit") val dailyProfit: BigDecimal = BigDecimal.ZERO,
    @Json(name = "profit_lifetime") val profitLifetime: BigDecimal = BigDecimal.ZERO,
    @Json(name = "in_daily_value") val inDailyValue: BigDecimal = BigDecimal.ZERO,
    @Json(name = "roi_daily") val roiDaily: BigDecimal = BigDecimal.ZERO,
    @Json(name = "realized_gain") val realizedGain: BigDecimal = BigDecimal.ZERO,
    @Json(name = "unrealized_gain") val unrealizedGain: BigDecimal = BigDecimal.ZERO
) : IModel {

    @Json(ignored = true)
    val date = dateAsString.asDate()

    fun haveTotal() : Boolean = "false" != total

    override fun isEmpty(): Boolean = dateAsString.isEmpty()
            && deposit == BigDecimal.ZERO && withdraw == BigDecimal.ZERO
            && symbol.isEmpty() && tokenAddress.isEmpty()
}

enum class Currency {
    USD,
    ETH;

    companion object {
        fun default(): Currency = ETH
    }
}