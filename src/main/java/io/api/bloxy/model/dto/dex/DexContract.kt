package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexContract(
    val smart_contract_address: String = "",
    val trades: Long = 0,
    @Json(name = "latest_trade")
    val latest_trade_as_string: String = "",
    val protocol: String = "",
    val annotation: String = ""
) : IModel {

    @Json(ignored = true)
    val latest_trade = ParamConverter.parseDateTime(latest_trade_as_string)

    fun haveDateTime() : Boolean = latest_trade != null

    override fun isEmpty(): Boolean {
        return smart_contract_address.isEmpty() && protocol.isEmpty()
    }
}