package io.api.bloxy.model.dto.address

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IDangerModel
import io.api.bloxy.model.IValidModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.model.dto.DangerLevel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class AddrStatistic(
    val address: String = "",
    val note: String = "",
    @Json(name = "type") val addrTypeAsString: String = "",
    @Json(name = "level") val levelAsString: String = "",
    @Json(name = "balance_eth") val balanceEth: Double = .0,
    @Json(name = "send_tx_count") val sendTxCount: Long = 0,
    @Json(name = "send_to_count") val sendToCount: Long = 0,
    @Json(name = "send_to_currencies") val sendToCurrencies: Long = 0,
    @Json(name = "send_eth_amount") val sendEthAmount: Double = .0,
    @Json(name = "receive_tx_count") val receiveTxCount: Long = 0,
    @Json(name = "receive_from_count") val receiveFromCount: Long = 0,
    @Json(name = "receive_from_currencies") val receiveFromCurrencies: Long = 0,
    @Json(name = "receive_eth_amount") val receiveEthAmount: Double = .0,
    @Json(name = "first_tx_at") val firstTxAt: String = "",
    @Json(name = "last_tx_at") val lastTxAt: String = "",
    val annotation: String = ""
) : IValidModel, IDangerModel, IAddressModel {

    @Json(ignored = true)
    override val level: DangerLevel = DangerLevel.parse(levelAsString)

    override val addrType: AddressType = AddressType.parse(addrTypeAsString)

    override fun isEmpty(): Boolean = address.isEmpty()

    override fun isValid(): Boolean = "Address was never used" != note

    override fun toString(): String {
        return "AddrStatistic(address='$address', note='$note', addrTypeAsString='$addrTypeAsString', " +
                "levelAsString='$levelAsString', balanceEth=$balanceEth, sendTxCount=$sendTxCount, " +
                "sendToCount=$sendToCount, sendToCurrencies=$sendToCurrencies, sendEthAmount=$sendEthAmount, " +
                "receiveTxCount=$receiveTxCount, receiveFromCount=$receiveFromCount, " +
                "receiveFromCurrencies=$receiveFromCurrencies, receiveEthAmount=$receiveEthAmount, " +
                "firstTxAt='$firstTxAt', lastTxAt='$lastTxAt', annotation='$annotation', level=$level, addrType=$addrType)"
    }
}