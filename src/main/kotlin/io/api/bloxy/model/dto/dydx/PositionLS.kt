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
data class PositionLS(
    val event: String = "",
    val symbol: String = "",
    val type: String = "",
    val interest: Double = .0,
    val trader: String = "",
    val lender: String = "",
    @Json(name = "loanHash") val loanHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_from") val txFrom: String = "",
    @Json(name = "loanFeeRecipient") val loanFeeRecipient: String = "",
    @Json(name = "principalAmount") val principalAmount: Double = .0,
    @Json(name = "owedTokenSymbol") val owedTokenSymbol: String = "",
    @Json(name = "heldTokenSymbol") val heldTokenSymbol: String = "",
    @Json(name = "borrowedAmount") val borrowedAmount: Double = .0,
    @Json(name = "heldAmount") val heldAmount: Double = .0,
    @Json(name = "positionId") val positionId: String = "",
    @Json(name = "depositInHeldTokenAmount") val depositInHeldTokenAmount: Double = .0,
    @Json(name = "depositInOwedTokenAmount") val depositInOwedTokenAmount: Double = .0,
    @Json(name = "positionTokenAddress") val positionTokenAddress: String = "",
    @Json(name = "depositTokenSymbol") val depositTokenSymbol: String = "",
    @Json(name = "tx_from_annotation") val txFromAnnotation: String = "",
    @Json(name = "trader_annotation") val traderAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val txTime = txTimeAsString.asDateTime()

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean = trader.isEmpty() && lender.isEmpty() && txHash.isEmpty()
}