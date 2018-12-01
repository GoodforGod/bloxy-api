package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexTrade(
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "tx_date") val txDateAsString: String = "",
    @Json(name = "tx_sender") val txSender: String = "",
    @Json(name = "smart_contract_id") val smartContractId: Long = 0,
    @Json(name = "smart_contract_address") val smartContractAddress: String = "",
    @Json(name = "contract_type") val contractType: String = "",
    @Json(name = "maker_annotation") val makerAnnotation: String = "",
    @Json(name = "taker_annotation") val takerAnnotation: String = "",
    val maker: String = "",
    val taker: String = "",
    val amountBuy: Double = .0,
    val makerFee: Double = .0,
    val buyCurrencyId: Long = 0,
    val buySymbol: String = "",
    val amountSell: Double = .0,
    val takerFee: Double = .0,
    val sellCurrencyId: Long = 0,
    val sellSymbol: String = "",
    val protocol: String = "",
    val buyAddress: String = "",
    val sellAddress: String = ""
) : IModel {

    @Json(ignored = true)
    val txTime = ParamConverter.parseDateTime(txTimeAsString)

    fun haveTxTime() : Boolean = txTime != null

    override fun isEmpty(): Boolean {
        return smartContractAddress.isEmpty() && protocol.isEmpty()
    }

    override fun toString(): String {
        return "DexTrade(txHash='$txHash', txTimeAsString='$txTimeAsString', txDateAsString='$txDateAsString', " +
                "txSender='$txSender', smartContractId=$smartContractId, smartContractAddress='$smartContractAddress', " +
                "contractType='$contractType', makerAnnotation='$makerAnnotation', takerAnnotation='$takerAnnotation', " +
                "maker='$maker', taker='$taker', amountBuy=$amountBuy, makerFee=$makerFee, buyCurrencyId=$buyCurrencyId, " +
                "buySymbol='$buySymbol', amountSell=$amountSell, takerFee=$takerFee, sellCurrencyId=$sellCurrencyId, " +
                "sellSymbol='$sellSymbol', protocol='$protocol', buyAddress='$buyAddress', sellAddress='$sellAddress', " +
                "txTime=$txTime)"
    }
}