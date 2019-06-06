package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDate
import java.math.BigDecimal

/**
 * FlowDaily model
 *
 * @author GoodforGod
 * @since 31.05.2019
 */
data class FlowDaily(
    @Json(name = "date")
    val dateAsString: String = "",
    val address: String = "",
    val token: String = "",
    val symbol: String = "",
    @Json(name = "receive_count") val receiveCount: Long = 0L,
    @Json(name = "receive_amount") val receiveAmount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "unique_receivers") val uniqueReceivers: Long = 0L,
    @Json(name = "sent_count") val sentCount: Long = 0L,
    @Json(name = "sent_amount") val sentAmount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "unique_senders") val uniqueSenders: Long = 0L
) : IModel {

    @Json(ignored = true)
    val date = dateAsString.asDate()

    override fun isEmpty(): Boolean = address.isEmpty() && token.isEmpty() && dateAsString.isEmpty()

    override fun toString(): String {
        return "FlowDaily(dateAsString='$dateAsString', address='$address', token='$token', symbol='$symbol', " +
                "receiveCount=$receiveCount, receiveAmount=$receiveAmount, uniqueReceivers=$uniqueReceivers, " +
                "sentCount=$sentCount, sentAmount=$sentAmount, uniqueSenders=$uniqueSenders, date=$date)"
    }
}