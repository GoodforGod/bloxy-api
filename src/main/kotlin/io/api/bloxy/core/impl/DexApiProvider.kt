package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.address.Currency
import io.api.bloxy.model.dto.dex.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


/**
 * API for Analysis of decentralised exchanges (DEX)
 * More information - https://bloxy.info/api_methods#dex
 *
 * @see io.api.bloxy.core.IDexApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class DexApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "dex", key) {

    companion object {
        private val errors = listOf(
            "^Protocols not found".toRegex(),
            "^Protocol can not be".toRegex(),
            "^Not found any DEXes".toRegex()
        )
    }

    private fun asProtocol(values: List<String>): String {
        return asParam(values, "&protocol[]=", "protocol[]=")
    }

    private fun asContract(values: List<String>): String {
        return asParam(checkAddr(values), "&smart_contract_address[]=", "smart_contract_address[]=")
    }

    /**
     * @see io.api.bloxy.core.IDexApi.protocols
     */
    @NotNull
    fun protocols(): List<DexProtocol> {
        return get("protocols?")
    }

    /**
     * @see io.api.bloxy.core.IDexApi.contracts
     */
    @NotNull
    @JvmOverloads
    fun contracts(
        protocols: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DexContract> {
        val datesParam = "${asDate("from_date", since)}${asDate("till_date", till)}"
        val params = "smart_contracts?$datesParam${asProtocol(protocols)}"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.IDexApi.trades
     */
    @NotNull
    @JvmOverloads
    fun trades(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        tokenAddresses: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DexTrade> {
        val datesParam = "${asDate("from_date", since)}${asDate("till_date", till)}"
        val paramsAddrs = "${asContract(dexContracts)}${asToken(tokenAddresses)}"
        val params = "trades?$datesParam${asProtocol(protocols)}$paramsAddrs"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IDexApi.pendingTxs
     */
    @NotNull
    @JvmOverloads
    fun pendingTxs(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList()
    ): List<DexTxPending> {
        val paramDex = asContract(dexContracts)
        val formattedDex = if (protocols.isEmpty()) paramDex.replace("&", "") else paramDex
        val params = "pending?${asProtocol(protocols)}$formattedDex"
        return get(params, errors)
    }

    /**
     * @see io.api.bloxy.core.IDexApi.tradesActive
     */
    @NotNull
    @JvmOverloads
    fun tradesActive(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DexTradeActive> {
        val datesParam = "${asDate("from_date", since)}${asDate("till_date", till)}"
        val paramsAddrs = "${asProtocol(protocols)}${asContract(dexContracts)}"
        val params = "traders?$datesParam$paramsAddrs"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IDexApi.tradesActive
     */
    @NotNull
    @JvmOverloads
    fun tradesByHash(
        txHash: String,
        currency: Currency = Currency.ETH
    ) : List<DexTrade> {
       return get("tx_trades?trader=${checkTxRequired(txHash)}&price_currency=${currency.name}")
    }

    /**
     * @see io.api.bloxy.core.IDexApi.tradesArbitrage
     */
    @NotNull
    @JvmOverloads
    fun tradesArbitrage(
        trader: String = "",
        symbol: String = "",
        currency: Currency = Currency.ETH,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DexArbitrage> {
        val datesParam = "${asDate("from_date", since)}${asDate("till_date", till)}"
        val additionalParam = asParam(symbol, "&symbol=")
        val params = "arbitrage_trades?trader=${checkAddrRequired(trader)}&price_currency=${currency.name}$datesParam$additionalParam"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IDexApi.deposits
     */
    @NotNull
    @JvmOverloads
    fun deposits(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        tokenAddresses: List<String> = emptyList(),
        currency: Currency = Currency.ETH,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DexDeposit> {
        val datesParam = "${asDate("from_date", since)}${asDate("till_date", till)}"
        val paramsAddrs = "${asContract(dexContracts)}${asToken(tokenAddresses)}"
        val params = "deposits?price_currency=${currency.name}$datesParam${asProtocol(protocols)}$paramsAddrs"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IDexApi.tokenStats
     */
    @NotNull
    @JvmOverloads
    fun tokenStats(
        tokenAddress: String,
        limit: Int = 100,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DexTokenStat> {
        val datesParam = "${asDate("from_date", since)}${asDate("till_date", till)}"
        val params = "token_stat?token=${checkAddrRequired(tokenAddress)}$datesParam"
        return getOffset(params, limit, 200000000, skipErrors = errors)
    }
}