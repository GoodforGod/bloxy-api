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
    val tx_hash: String = "",
    @Json(name = "tx_time")
    val tx_time_as_string: String = "",
    val tx_from: String = "",
    val tx_to: String = "",
    val receiver: String = "",
    val amount: Double = .0,
    val method: String = "",
    val block: Long = 0,
    val tx_from_annotation: String = "",
    val tx_to_annotation: String = "",
    val tx_to_type: String = "",
    val gas_price: Double = .0,
    val gas: Double = .0,
    val gas_value: Double = .0
) : IModel {

    @Json(ignored = true) val tx_time = ParamConverter.parseDateTime(tx_time_as_string)

    fun haveTxTime() : Boolean = tx_time != null

    override fun isEmpty(): Boolean {
        return tx_hash.isEmpty() && tx_from.isEmpty() && tx_to.isEmpty()
    }
}