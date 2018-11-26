package io.api.bloxy.core

import io.api.bloxy.model.dto.dex.*
import org.jetbrains.annotations.NotNull


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IDexApi {

    /**
     *
     */
    @NotNull
    fun protocols(): List<DexProtocol>

    /**
     *
     * @param protocols
     * @param limit
     * @param offset
     * @param timeSpanDays
     */
    @NotNull
    fun contracts(
        protocols: List<String>,
        limit: Int,
        offset: Int,
        timeSpanDays: Int
    ): List<DexContract>

    /**
     *
     * @param protocols
     * @param dexContracts
     * @param tokenAddresses
     * @param limit
     * @param offset
     * @param timeSpanDays
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
     *
     * @param protocols
     * @param dexContracts
     */
    @NotNull
    fun pendingTxs(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList()
    ): List<DexTxPending>

    /**
     *
     * @param protocols
     * @param dexContracts
     * @param limit
     * @param offset
     * @param timeSpanDays
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