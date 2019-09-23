package io.api.bloxy.model.dto.address

import io.api.bloxy.model.IModel
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class Balance(balances: List<CoinBalance>) : IModel {

    companion object {
        val empty: Balance = Balance(emptyList())
    }

    private val balances: Map<String, CoinBalance> = balances.asSequence().map { it.symbol to it }.toMap()

    fun exist(symbol: String): Boolean = this.balances[symbol] != null

    fun getEth(): CoinBalance = get("ETH") ?: CoinBalance()

    fun get(symbol: String): CoinBalance? = this.balances[symbol]

    fun getAll(): List<CoinBalance> = ArrayList(this.balances.values)

    fun getSendOnly(): List<CoinBalance> {
        return ArrayList(this.balances.values).stream()
            .filter{ b -> b.sentTxs != 0L}
            .collect(Collectors.toList())
    }

    /**
     * Based on TX amount
     */
    fun getTopTenSend(): List<CoinBalance> {
        return ArrayList(this.balances.values).stream()
            .sorted { o1, o2 -> o2.sentTxs.compareTo(o1.sentTxs) }
            .limit(10)
            .collect(Collectors.toList())
    }

    /**
     * Based on TX amount
     */
    fun getTopTenReceived(): List<CoinBalance> {
        return ArrayList(this.balances.values).stream()
            .sorted { o1, o2 -> o2.receivedTxs.compareTo(o1.receivedTxs) }
            .limit(10)
            .collect(Collectors.toList())
    }

    override fun isEmpty(): Boolean = balances.isEmpty()

    override fun toString(): String = "Balance(balances=$balances)"
}
