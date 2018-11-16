package io.api.bloxy.model.dto.address


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class Balance(balances: List<CoinBalance>) {

    private val balances: Map<String, CoinBalance>

    init {
        this.balances = HashMap()
        balances.forEach { b -> this.balances[b.symbol] = b }
    }

    fun exist(symbol: String): Boolean {
        return this.balances[symbol] != null
    }

    fun get(symbol: String): CoinBalance? {
        return this.balances[symbol]
    }

    fun getAll(): List<CoinBalance> {
        return ArrayList(this.balances.values)
    }
}