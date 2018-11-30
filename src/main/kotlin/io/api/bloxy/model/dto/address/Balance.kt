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

    private val balances: Map<String, CoinBalance>

    init {
        this.balances = HashMap()
        balances.forEach { b -> this.balances[b.symbol] = b }
    }

    override fun isEmpty(): Boolean = balances.isEmpty()

    fun exist(symbol: String): Boolean {
        return this.balances[symbol] != null
    }

    fun getEth(): CoinBalance {
        return get("ETH") ?: CoinBalance()
    }

    fun get(symbol: String): CoinBalance? {
        return this.balances[symbol]
    }

    fun getAll(): List<CoinBalance> {
        return ArrayList(this.balances.values)
    }

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
}