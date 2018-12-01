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
    @Json(name = "smart_contract_address") val smartContractAddress: String = "",
    @Json(name = "latest_trade") val latestTradeAsString: String = "",
    val trades: Long = 0,
    val protocol: String = "",
    val annotation: String = ""
) : IModel {

    @Json(ignored = true)
    val latestTrade = ParamConverter.parseDateTime(latestTradeAsString)

    fun haveTradeTime() : Boolean = latestTrade != null

    override fun isEmpty(): Boolean {
        return smartContractAddress.isEmpty() && protocol.isEmpty()
    }

    override fun toString(): String {
        return "DexContract(smartContractAddress='$smartContractAddress', latestTradeAsString='$latestTradeAsString', " +
                "trades=$trades, protocol='$protocol', annotation='$annotation', latestTrade=$latestTrade)"
    }
}