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
data class Position(
    @Json(name = "open_time") val openTimeAsString: String = "",
    @Json(name = "end_time") val endTimeAsString: String = "",
    val status: String = "",
    val symbol: String = "",
    val type: String = "",
    @Json(name = "depositTokenSymbol") val depositTokenSymbol: String = "",
    @Json(name = "owedTokenSymbol") val owedTokenSymbol: String = "",
    @Json(name = "heldTokenSymbol") val heldTokenSymbol: String = "",
    @Json(name = "depositAmount") val depositAmount: Double = .0,
    @Json(name = "principalAmount") val principalAmount: Double = .0,
    @Json(name = "heldTokenFromSell") val heldTokenFromSell: Double = .0,
    @Json(name = "interestRate") val interestRate: Int = 0,
    @Json(name = "priceFactor") val priceFactor: Double = .0,
    @Json(name = "priceFormula") val priceFormula: String = "",
    val lender: String = "",
    val trader: String = "",
    val creator: String = "",
    @Json(name = "positionId") val positionId: String = "",
    @Json(name = "lender_annotation") val lenderAnnotation: String = "",
    @Json(name = "trader_annotation") val traderAnnotation: String = "",
    @Json(name = "creator_annotation") val creatorAnnotation: String = "",
    @Json(name = "position_token_annotation") val positionTokenAnnotation: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "margin_tx_hash") val marginTxHash: String = "",
    @Json(name = "owedTokenAddress") val owedTokenAddress: String = "",
    @Json(name = "heldTokenAddress") val heldTokenAddress: String = "",
    @Json(name = "positionTokenAddress") val positionTokenAddress: String = "",
    @Json(name = "depositTokenAddress") val depositTokenAddress: String = ""
) : IModel {

    @Json(ignored = true) val openTime = openTimeAsString.asDateTime()
    @Json(ignored = true) val endTime = endTimeAsString.asDateTime()

    fun haveOpenTime() : Boolean = openTime != null
    fun haveEndTime() : Boolean = endTime != null

    override fun isEmpty(): Boolean = trader.isNotEmpty() && lender.isNotEmpty() && txHash.isNotEmpty()
}