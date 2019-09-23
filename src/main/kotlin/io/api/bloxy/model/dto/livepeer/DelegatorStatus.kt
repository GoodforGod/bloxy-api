package io.api.bloxy.model.dto.livepeer

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
data class DelegatorStatus(
    val address: String = "",
    val delegate: String = "",
    val fees: Double = .0,
    @Json(name = "totalStake") val totalStake: Double = .0,
    @Json(name = "bondedAmount") val bondedAmount: Double = .0,
    @Json(name = "pendingStake")  val pendingStake: Double = .0,
    @Json(name = "startRound") val startRound: Long = 0L,
    @Json(name = "withdrawRound") val withdrawRound: Long = 0L,
    @Json(name = "lastClaimRound") val lastClaimRound: Long = 0L,
    @Json(name = "delegatedAmount") val delegatedAmount: Double = .0,
    @Json(name = "unclaimedEarnings") val unclaimedEarnings: Double = .0
) : IModel {
    override fun isEmpty(): Boolean = address.isEmpty() && delegate.isEmpty() && bondedAmount == .0
}