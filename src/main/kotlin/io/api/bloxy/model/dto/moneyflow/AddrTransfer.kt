package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class AddrTransfer(
    val direction: String = "",
    val party: String = "",
    val amount: Double = .0,
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "party_type") val partyType: String = "",
    @Json(name = "token_symbol") val tokenSymbol: String = "",
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "party_annotation") val partyAnnotation: String = ""
) : IModel {

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean {
        return txHash.isEmpty() && txTimeAsString.isEmpty()
    }

    override fun toString(): String {
        return "AddrTransfer(direction='$direction', party='$party', amount=$amount, txHash='$txHash', " +
                "txTimeAsString='$txTimeAsString', partyType='$partyType', tokenSymbol='$tokenSymbol', " +
                "tokenAddress='$tokenAddress', partyAnnotation='$partyAnnotation', txTime=$txTime)"
    }
}