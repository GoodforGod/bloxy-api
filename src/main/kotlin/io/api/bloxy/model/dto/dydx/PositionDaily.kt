package io.api.bloxy.model.dto.dydx

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDate


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.02.2019
 */
data class PositionDaily(
    val closed: Int = 0,
    val increased: Int = 0,
    @Json(name = "tx_date") val txDateAsString: String = "",
    @Json(name = "depositedInHeldToken") val depositedInHeldToken: Double = .0,
    @Json(name = "depositedInOwedToken") val depositedInOwedToken: Double = .0,
    @Json(name = "borrowedAmountChange") val borrowedAmountChange: Double = .0,
    @Json(name = "principalAmountChange") val principalAmountChange: Double = .0,
    @Json(name = "heldAmountChange") val heldAmountChange: Double = .0,
    @Json(name = "principalAmount") val principalAmount: Double = .0,
    @Json(name = "paidInHeldToken") val paidInHeldToken: Double = .0,
    @Json(name = "paidInOwedToken") val paidInOwedToken: Double = .0,
    @Json(name = "borrowedAmount") val borrowedAmount: Double = .0,
    @Json(name = "heldAmount") val heldAmount: Double = .0
) : IModel {

    @Json(ignored = true) val txDate = txDateAsString.asDate()

    fun haveDate(): Boolean = txDate != null

    override fun isEmpty(): Boolean = closed == 0 && increased == 0 && txDateAsString.isEmpty()
}