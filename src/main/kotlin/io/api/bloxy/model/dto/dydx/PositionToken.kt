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
data class PositionToken(
    val holders: Int = 0,
    val price: Double = .0,
    val supply: Double = .0,
    val interest: Double = .0,
    val leverage: Double = .0,
    val marketcap: Double = .0,
    @Json(name = "time") val timeAsString: String = "",
    @Json(name = "priceFactor") val priceFactor: Double = .0,
    @Json(name = "held_price") val heldPrice: Double = .0,
    @Json(name = "owed_price") val owedPrice: Double = .0
) : IModel {

    @Json(ignored = true) val time = timeAsString.asDate()

    fun haveTime(): Boolean = time != null

    override fun isEmpty(): Boolean = timeAsString.isEmpty() && holders == 0 && supply == .0
}