package io.api.bloxy.model.dto.dydx

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.KlaxonDecimal
import io.api.bloxy.util.KlaxonDouble
import io.api.bloxy.util.ParamConverter.Companion.asDateTime
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.02.2019
 */
data class TraderStats(
    val address: String = "",
    val type: String = "",
    val symbol: String = "",
    val success: String = "",
    @Json(name = "positionId") val positionId: String = "",
    @KlaxonDouble @Json(name = "depositHeld") val depositHeld: Double = .0,
    @KlaxonDouble @Json(name = "depositOwed") val depositOwed: Double = .0,
    @KlaxonDouble @Json(name = "payoutHeld") val payoutHeld: Double = .0,
    @KlaxonDouble @Json(name = "payoutOwed") val payoutOwed: Double = .0,
    @Json(name = "owedTokenSymbol") val owedTokenSymbol: String = "",
    @Json(name = "heldTokenSymbol") val heldTokenSymbol: String = "",
    @KlaxonDecimal @Json(name = "payoutHeldTokenAmount") val payoutHeldTokenAmount: BigDecimal = BigDecimal.ZERO,
    @KlaxonDecimal @Json(name = "payoutOwedTokenAmount") val payoutOwedTokenAmount: BigDecimal = BigDecimal.ZERO,
    @KlaxonDecimal @Json(name = "depositHeldTokenAmount") val depositHeldTokenAmount: BigDecimal = BigDecimal.ZERO,
    @KlaxonDecimal @Json(name = "depositOwedTokenAmount") val depositOwedTokenAmount: BigDecimal = BigDecimal.ZERO,
    @KlaxonDouble @Json(name = "depositPrincipalAmount") val depositPrincipalAmount: Double = .0,
    @KlaxonDouble @Json(name = "payoutPrincipalAmount") val payoutPrincipalAmount: Double = .0,
    @Json(name = "first_increase_time") val firstIncreaseTimeAsString: String = "",
    @Json(name = "last_close_time") val lastCloseTimeAsString: String = "",
    @KlaxonDouble @Json(name = "payout_percentage") val payoutPercentage: Double = .0,
    @KlaxonDouble @Json(name = "principal_used_amount") val principalUsedAmount: Double = .0,
    @KlaxonDouble @Json(name = "held_principal_closed") val heldPrincipalClosed: Double = .0,
    @KlaxonDouble @Json(name = "held_principal_increased") val heldPrincipalIncreased: Double = .0,
    @KlaxonDouble @Json(name = "owed_principal_increased") val owedPrincipalIncreased: Double = .0,
    @KlaxonDouble @Json(name = "owed_principal_closed") val owedPrincipalClosed: Double = .0,
    @Json(name = "held_increased_count") val heldIncreasedCount: Int = 0,
    @Json(name = "owed_increased_count") val owedIncreasedCount: Int = 0,
    @KlaxonDouble @Json(name = "pl_held_percentage") val plHeldPercentage: Double = .0,
    @KlaxonDouble @Json(name = "pl_owed_percentage") val plOwedPercentage: Double = .0,
    @Json(name = "held_closed_count") val heldClosedCount: Int = 0,
    @Json(name = "owed_closed_count") val owedClosedCount: Int = 0,
    @KlaxonDouble @Json(name = "held_principal_used_amount") val heldPrincipalUsedAmount: Double = .0,
    @KlaxonDouble @Json(name = "owed_principal_used_amount") val owedPrincipalUsedAmount: Double = .0,
    @Json(name = "positionTokenAddress") val positionTokenAddress: String = "",
    @KlaxonDouble @Json(name = "pl_percentage") val plPercentage: Double = .0,
    @Json(name = "owedTokenAddress") val owedTokenAddress: String = "",
    @Json(name = "heldTokenAddress") val heldTokenAddress: String = "",
    @Json(name = "address_annotation") val addressAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val firstIncreaseTime = firstIncreaseTimeAsString.asDateTime()
    @Json(ignored = true) val lastCloseTime = lastCloseTimeAsString.asDateTime()

    fun haveFirstIncreaseTime() : Boolean = firstIncreaseTime != null
    fun haveLastCloseTime() : Boolean = lastCloseTime != null

    override fun isEmpty(): Boolean = address.isEmpty() && type.isEmpty()
}