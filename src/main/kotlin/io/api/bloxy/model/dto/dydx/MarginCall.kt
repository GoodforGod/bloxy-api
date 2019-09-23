package io.api.bloxy.model.dto.dydx

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.02.2019
 */
data class MarginCall(
    val symbol: String = "",
    val trader: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "positionId") val positionId: String = "",
    @Json(name = "positionTokenAddress") val positionTokenAddress: String = "",
    @Json(name = "owedTokenSymbol") val owedTokenSymbol: String = "",
    @Json(name = "heldTokenSymbol") val heldTokenSymbol: String = "",
    @Json(name = "owedTokenAddress") val owedTokenAddress: String = "",
    @Json(name = "heldTokenAddress") val heldTokenAddress: String = "",
    @Json(name = "tokensRedeemed") val tokensRedeemed: Double = .0,
    @Json(name = "heldTokenPayout") val heldTokenPayout: Double = .0,
    @Json(name = "trader_annotation") val traderAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val txTime = txTimeAsString.asDateTime()

    fun haveTxTime(): Boolean = txTime != null

    override fun isEmpty(): Boolean = txHash.isEmpty() && trader.isEmpty()
}