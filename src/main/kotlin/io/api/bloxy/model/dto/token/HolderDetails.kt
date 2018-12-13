package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class HolderDetails(
    @Json(name = "address_type") val typeAsString: String = "",
    @Json(name = "uniq_senders") val uniqSenders: Long = 0,
    @Json(name = "to_count") val toCount: Long = 0,
    @Json(name = "to_amount") val toAmount: Double = .0,
    @Json(name = "from_count") val fromCount: Long = 0,
    @Json(name = "uniq_receivers") val uniqReceivers: Long = 0,
    @Json(name = "from_amount") val fromAmount: Double = .0,
    @Json(name = "first_tx_at") val firstTxAtAsString: String = "",
    @Json(name = "last_tx_at") val lastTxAtAsString: String = "",
    val address: String = "",
    val balance: Double = .0,
    val annotation: String = ""
) : IModel, IAddressModel {

    @Json(ignored = true) val firstTxAt = firstTxAtAsString.asDateTime()
    @Json(ignored = true) val lastTxAt = lastTxAtAsString.asDateTime()

    fun haveFirstTxTime() : Boolean = firstTxAt != null
    fun haveLastTxTime() : Boolean = lastTxAt != null

    override val addrType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean = address.isEmpty() && typeAsString.isEmpty() && toCount == 0L && fromCount == 0L

    override fun toString(): String {
        return "HolderDetails(typeAsString='$typeAsString', uniqSenders=$uniqSenders, " +
                "toCount=$toCount, toAmount=$toAmount, fromCount=$fromCount, uniqReceivers=$uniqReceivers," +
                " fromAmount=$fromAmount, firstTxAtAsString='$firstTxAtAsString', lastTxAtAsString='$lastTxAtAsString'," +
                " address='$address', balance=$balance, annotation='$annotation', " +
                "firstTxAt=$firstTxAt, lastTxAt=$lastTxAt, addrType=$addrType)"
    }
}