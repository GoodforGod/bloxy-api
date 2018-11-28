package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleBuyer(
    val token_buyer: String = "",
    val transactions: Int = 0,
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    @Json(name = "from_time")
    val from_time_as_string: String = "",
    @Json(name = "till_time")
    val till_time_as_string: String = ""
) : IModel {

    @Json(ignored = true) val from_time = ParamConverter.parseDateTime(from_time_as_string)
    @Json(ignored = true) val till_time = ParamConverter.parseDateTime(till_time_as_string)

    fun haveFromTime() : Boolean = from_time != null
    fun haveTillTime() : Boolean = till_time != null

    override fun isEmpty(): Boolean {
        return token_buyer.isEmpty() && transactions == 0 && eth_amount == .0 && token_amount == .0
    }
}