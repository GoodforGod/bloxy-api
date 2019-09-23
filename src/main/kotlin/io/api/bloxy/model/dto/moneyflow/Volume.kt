package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Volume(
    @Json(name = "address_type") val typeAsString: String = "",
    @Json(name = "received_amount") val receivedAmount: Double = .0,
    @Json(name = "received_txs") val receivedTxs: Long = 0,
    @Json(name = "sent_amount") val sentAmount: Double = .0,
    @Json(name = "sent_txs") val sentTxs: Long = 0,
    val address: String = "",
    val annotation: String = ""
) : IModel, IAddressModel {

    override val addrType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean = address.isEmpty() && typeAsString.isEmpty() && receivedAmount == .0 && sentAmount == .0

    override fun toString(): String {
        return "Volume(typeAsString='$typeAsString', receivedAmount=$receivedAmount, " +
                "receivedTxs=$receivedTxs, sentAmount=$sentAmount, sentTxs=$sentTxs, " +
                "address='$address', annotation='$annotation', addrType=$addrType)"
    }
}