package io.api.bloxy.core

import io.api.bloxy.model.dto.dex.*


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
    fun protocols(): List<DexProtocol>

    /**
     *
     * @param protocols
     * @param limit
     * @param offset
     * @param timeSpanDays
     */
    fun contracts(
        protocols: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        timeSpanDays: Int = 30
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
    fun tradesActive(
        protocols: List<String> = emptyList(),
        dexContracts: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<DexTradeActive>
}