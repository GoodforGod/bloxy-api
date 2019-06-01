package io.api.bloxy.core

import io.api.bloxy.model.dto.address.Currency
import io.api.bloxy.model.dto.dex.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


/**
 * API for Analysis of decentralised exchanges (DEX)
 * More information - https://bloxy.info/api_methods#dex
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal interface IDexApi {

    /**
     * Lists DEX protocol names and count of implementations in smart contracts
     */
    @NotNull
    fun protocols(): List<DexProtocol>

    /**
     * Lists tokens by matching text in symbol or name. List is sorted descending by transaction count
     * @param protocols dex to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun contracts(
        protocols: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DexContract>

    /**
     * Lists trades, optionally filtered by protocol, DEX smart contract(s) and/or token(s).
     * @param protocols dex to filter
     * @param dexContracts dex to filter
     * @param tokenAddresses to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun trades(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        tokenAddresses: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DexTrade>

    /**
     * Lists transactions sent to DEXes in pending state (not mined yet).
     * @param protocols to filter
     * @param dexContracts to filter
     */
    @NotNull
    fun pendingTxs(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList()
    ): List<DexTxPending>

    /**
     * Lists active traders ordered by number of trades, as maker or taker.
     * !!! Note, that the data does not include the last day trades
     * @param protocols dex to filter
     * @param dexContracts dex to filter
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun tradesActive(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DexTradeActive>

    /**
     * Lists trades, in a transaction
     * @param txHash transaction hash
     * @param currency for trades to view
     */
    @NotNull
    fun tradesByHash(
        txHash: String,
        currency: Currency = Currency.ETH
    ) : List<DexTrade>

    /**
     * Lists active traders ordered by number of trades, as maker or taker.
     * @param trader trader address to filter
     * @param symbol token to filter
     * @param currency to view result (ETH,USD)
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun tradesArbitrage(
        trader: String = "",
        symbol: String = "",
        currency: Currency = Currency.ETH,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DexArbitrage>

    /**
     * Lists active traders ordered by number of trades, as maker or taker.
     * @param protocols dex to filter
     * @param dexContracts dex to filter
     * @param currency to view result (ETH,USD)
     * @param tokenAddresses token addresses to filter
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun deposits(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        tokenAddresses: List<String> = emptyList(),
        currency: Currency = Currency.ETH,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DexDeposit>

    /**
     * Lists active traders ordered by number of trades, as maker or taker.
     * @param tokenAddress token address to filter
     * @param currency to view result (ETH,USD)
     * @param limit max result (MAX 101000)
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun tokenStats(
        tokenAddress: String,
        limit: Int = 100,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DexTokenStat>
}