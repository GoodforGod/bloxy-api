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
data class SaleDaily(
    @Json(name = "tx_date")
    val tx_date_as_string: String = "",
    val transactions: Int = 0,
    val eth_amount: Double = .0,
    val token_amount: Double = .0,
    val token_buyers: Int = 0
) : IModel {

    @Json(ignored = true)
    val tx_date = ParamConverter.parseDate(tx_date_as_string)

    fun haveDate() : Boolean = tx_date != null

    override fun isEmpty(): Boolean {
        return tx_date_as_string.isEmpty() && transactions == 0 && eth_amount == .0 && token_amount == .0
    }
}