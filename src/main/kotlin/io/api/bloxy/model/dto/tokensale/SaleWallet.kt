package io.api.bloxy.model.dto.tokensale

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SaleWallet(
    val address: String = "",
    val balance: BigDecimal = BigDecimal.ZERO,
    val annotation: String = "",
    @Json(name = "amount_received") val amountReceived: BigDecimal = BigDecimal.ZERO,
    @Json(name = "amount_sent") val amountSent: BigDecimal = BigDecimal.ZERO,
    @Json(name = "transfers_received") val transfersReceived: Long = 0,
    @Json(name = "transfers_sent") val transfersSent: Long = 0,
    @Json(name = "from_time") val fromTimeAsString: String = "",
    @Json(name = "till_time") val tillTimeAsString: String = ""
) : IModel {

    @Json(ignored = true) val fromTime = fromTimeAsString.asDateTime()
    @Json(ignored = true) val tillTime = tillTimeAsString.asDateTime()

    override fun isEmpty(): Boolean = address.isEmpty() && balance == BigDecimal.ZERO
            && transfersSent == 0L && transfersReceived == 0L

    override fun toString(): String {
        return "SaleWallet(address='$address', balance=$balance, annotation='$annotation', " +
                "amount_received=$amountReceived, amount_sent=$amountSent, " +
                "transfers_received=$transfersReceived, transfers_sent=$transfersSent, " +
                "fromTimeAsString='$fromTimeAsString', tillTimeAsString='$tillTimeAsString')"
    }
}