package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter.Companion.asDateTime
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class DexDeposit(
    val amount: BigDecimal = BigDecimal.ZERO,
    val symbol: String = "",
    val protocol: String = "",
    val user: String = "",
    val token: String = "",
    val opIndex: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "amount_in_currency") val amountInCurrency: BigDecimal = BigDecimal.ZERO,
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_sender") val txSender: String = "",
    @Json(name = "smart_contract_address") val smartContractAddress: String = "",
    @Json(name = "user_annotation") val userAnnotation: String = ""
) : IModel {

    @Json(ignored = true)
    val txTime = txTimeAsString.asDateTime()

    override fun isEmpty(): Boolean = amount == BigDecimal.ZERO && symbol.isEmpty()
            && txHash.isEmpty() && txSender.isEmpty()

    override fun toString(): String {
        return "DexDeposit(amount=$amount, symbol='$symbol', protocol='$protocol', user='$user', " +
                "token='$token', opIndex='$opIndex', txTimeAsString='$txTimeAsString', " +
                "amountInCurrency=$amountInCurrency, txHash='$txHash', txSender='$txSender', " +
                "smartContractAddress='$smartContractAddress', userAnnotation='$userAnnotation')"
    }
}