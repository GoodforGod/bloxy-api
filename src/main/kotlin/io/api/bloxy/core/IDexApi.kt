package io.api.bloxy.core

import io.api.bloxy.model.dto.dex.*
import org.jetbrains.annotations.NotNull


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
     * @param timeSpanDays get info for period in days from today (MAX 1000)
     */
    @NotNull
    fun contracts(
        protocols: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<DexContract>

    /**
     * Lists trades, optionally filtered by protocol, DEX smart contract(s) and/or token(s).
     * @param protocols dex to filter
     * @param dexContracts dex to filter
     * @param tokenAddresses to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param timeSpanDays get info for period in days from today (MAX 30)
     */
    @NotNull
    fun trades(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        tokenAddresses: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        timeSpanDays: Int = 5
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
     * @param timeSpanDays get info for period in days from today (MAX 720)
     */
    @NotNull
    fun tradesActive(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<DexTradeActive>
}