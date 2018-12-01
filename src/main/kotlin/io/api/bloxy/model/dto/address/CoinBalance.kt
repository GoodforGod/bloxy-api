package io.api.bloxy.model.dto.address

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class CoinBalance(
    val symbol: String = "",
    @Json(name = "sent_txs") val sentTxs: Long = 0,
    @Json(name = "sent_amount") val sentAmount: Double = .0,
    @Json(name = "received_txs") val receivedTxs: Long = 0,
    @Json(name = "token_address") val tokenAddress: String = "",
    @Json(name = "received_amount") val receivedAmount: Double = .0,
    val balance: Double = .0
) : IModel {

    fun isEth(): Boolean = "ETH" == symbol

    override fun isEmpty(): Boolean = symbol.isEmpty() && tokenAddress.isEmpty()

    override fun toString(): String {
        return "CoinBalance(symbol='$symbol', sentTxs=$sentTxs, sentAmount=$sentAmount, receivedTxs=$receivedTxs, " +
                "tokenAddress='$tokenAddress', receivedAmount=$receivedAmount, balance=$balance)"
    }
}