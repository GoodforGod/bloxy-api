package io.api.bloxy.model.dto.dex

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
data class DexArbitrage(
    val trader: String = "",
    val symbol: String = "",
    val amount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "amount_in_currency") val amountInCurrency: BigDecimal = BigDecimal.ZERO,
    @Json(name = "smart_contracts") val smartContracts: String = "",
    @Json(name = "contract_types") val contractTypes: String = "",
    @Json(name = "trade_indexes") val tradeIndexes: String = ""
) : IModel {

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    override fun isEmpty(): Boolean = trader.isEmpty() && amount == BigDecimal.ZERO && txHash.isEmpty()

    override fun toString(): String {
        return "DexArbitrage(trader='$trader', symbol='$symbol', amount=$amount, txHash='$txHash', " +
                "txTime='$txTime', amountInCurrency=$amountInCurrency, smartContracts='$smartContracts', " +
                "contractTypes='$contractTypes', tradeIndexes='$tradeIndexes')"
    }
}