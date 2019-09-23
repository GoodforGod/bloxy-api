package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 27.02.2019
 */
data class AddrReceived(
    val amount: Double = .0,
    val sender: String = "",
    val receiver: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") private val txTimeAsString: String = "",
    @Json(name = "sender_type") val senderType: String = "",
    @Json(name = "sender_flag") val senderFlag: Int = 0,
    @Json(name = "token_symbol") val tokenSymbol: String = "",
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "sender_annotation") val senderAnnotation: String = ""
) : IModel, IAddressModel {

    override val addrType = AddressType.parse(senderType)

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean = txHash.isEmpty() && txTimeAsString.isEmpty()

    override fun toString(): String {
        return "AddrReceived(amount=$amount, sender='$sender', receiver='$receiver', txHash='$txHash', " +
                "txTimeAsString='$txTimeAsString', senderType='$senderType', senderFlag=$senderFlag, " +
                "tokenSymbol='$tokenSymbol', tokenAddress='$tokenAddress', senderAnnotation='$senderAnnotation', " +
                "addrType=$addrType, txTime=$txTime)"
    }
}