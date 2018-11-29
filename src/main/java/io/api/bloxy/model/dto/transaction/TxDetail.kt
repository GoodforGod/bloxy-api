package io.api.bloxy.model.dto.transaction

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class TxDetail(
    val receiver: String = "",
    val amount: Double = .0,
    val method: String = "",
    val block: Long = 0,
    val gas: Double = .0,
    @Json(name = "tx_to") val txTo: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "tx_from") val txFrom: String = "",
    @Json(name = "gas_price") val gasPrice: Double = .0,
    @Json(name = "gas_value") val gasValue: Double = .0,
    @Json(name = "tx_to_type") val txToType: String = "",
    @Json(name = "tx_to_annotation") val txToAnnotation: String = "",
    @Json(name = "tx_from_annotation") val txFromAnnotation: String = ""
) : IModel {

    @Json(ignored = true) val txTime = ParamConverter.parseDateTime(txTimeAsString)

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean {
        return txHash.isEmpty() && txFrom.isEmpty() && txTo.isEmpty()
    }
}