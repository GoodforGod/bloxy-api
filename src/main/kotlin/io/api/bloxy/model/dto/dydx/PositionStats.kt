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
data class PositionStats(
    val symbol: String = "",
    val type: String = "",
    @Json(name = "owedTokenSymbol") val owedTokenSymbol: String = "",
    @Json(name = "heldTokenSymbol") val heldTokenSymbol: String = "",
    @Json(name = "traders_in_profit") val tradersInProfit: Int = 0,
    @Json(name = "traders_in_loss") val tradersInLoss: Int = 0,
    @Json(name = "positionId") val positionId: String = "",
    @Json(name = "open_time") val openTimeAsString: String = "",
    @Json(name = "end_time") val endTimeAsString: String = "",
    @Json(name = "traders") val traders: Int = 0,
    @Json(name = "held_increased_count") val heldIncreasedCount: Int = 0,
    @Json(name = "owed_increased_count") val owedIncreasedCount: Int = 0,
    @Json(name = "held_closed_count") val heldClosedCount: Int = 0,
    @Json(name = "owed_closed_count") val owedClosedCount: Int = 0,
    @Json(name = "depositHeld") val depositHeld: Double = .0,
    @Json(name = "depositOwed") val depositOwed: Double = .0,
    @Json(name = "heldBalance") val heldBalance: Double = .0,
    @Json(name = "payoutHeld") val payoutHeld: Double = .0,
    @Json(name = "payoutOwed") val payoutOwed: Double = .0,
    @Json(name = "depositPrincipalAmount") val depositPrincipalAmount: Double = .0,
    @Json(name = "payoutPrincipalAmount") val payoutPrincipalAmount: Double = .0,
    @Json(name = "positionTokenAddress") val positionTokenAddress: String = "",
    @Json(name = "pl_held_percentage") val plHeldPercentage: Double = .0,
    @Json(name = "pl_owed_percentage") val plOwedPercentage: Double = .0,
    @Json(name = "held_principal_used_amount") val heldPrincipalUsedAmount: Double = .0,
    @Json(name = "owed_principal_used_amount") val owedPrincipalUsedAmount: Double = .0,
    @Json(name = "pl_percentage") val plPercentage: Double = .0,
    @Json(name = "principalBalance") val principalBalance: Double = .0,
    @Json(name = "payout_percentage") val payoutPercentage: Double = .0,
    @Json(name = "owedTokenAddress") val owedTokenAddress: String = "",
    @Json(name = "heldTokenAddress") val heldTokenAddress: String = ""
) : IModel {

    @Json(ignored = true) val openTime = openTimeAsString.asDateTime()
    @Json(ignored = true) val endTime = endTimeAsString.asDateTime()

    fun haveOpenTime() : Boolean = openTime != null
    fun haveEndTime() : Boolean = endTime != null

    override fun isEmpty(): Boolean = traders == 0 && symbol.isEmpty() && type.isEmpty()
}