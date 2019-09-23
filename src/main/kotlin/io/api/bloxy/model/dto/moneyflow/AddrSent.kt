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
data class AddrSent(
    val amount: Double = .0,
    val sender: String = "",
    val receiver: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") private val txTimeAsString: String = "",
    @Json(name = "receiver_type") val receiverType: String = "",
    @Json(name = "receiver_flag") val receiverFlag: Int = 0,
    @Json(name = "token_symbol") val tokenSymbol: String = "",
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "receiver_annotation") val receiverAnnotation: String = ""
) : IModel, IAddressModel {

    override val addrType = AddressType.parse(receiverType)

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean = txHash.isEmpty() && txTimeAsString.isEmpty()

    override fun toString(): String {
        return "AddrSent(amount=$amount, sender='$sender', receiver='$receiver', txHash='$txHash', " +
                "txTimeAsString='$txTimeAsString', receiverType='$receiverType', receiverFlag=$receiverFlag, " +
                "tokenSymbol='$tokenSymbol', tokenAddress='$tokenAddress', receiverAnnotation='$receiverAnnotation', " +
                "addrType=$addrType, txTime=$txTime)"
    }
}