package io.api.bloxy.core

import io.api.bloxy.model.dto.dex.*


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IDexApi {
    fun protocols(): List<DexProtocol>

    fun contracts(
        protocols: List<String>,
        limit: Int = 100,
        timeSpanDays: Int = 30
    ): List<DexContract>

    fun trades(
        protocols: List<String>,
        dexContracts: List<String> = emptyList(),
        tokenAddresses: List<String> = emptyList(),
        limit: Int = 100,
        timeSpanDays: Int = 5
    ): List<DexTrade>

    fun pendingTxs(
        protocols: List<String>,
        dexContracts: List<String> = emptyList()
    ): List<DexPending>

    fun tradesActive(
        protocols: List<String>,
        dexContracts: List<String> = emptyList(),
        limit: Int = 100,
        timeSpanDays: Int = 30
    ): List<DexTradeActive>
}