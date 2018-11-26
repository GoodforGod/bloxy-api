package io.api.bloxy.core

import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxTransfer
import org.jetbrains.annotations.NotNull


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITransactionApi {

    @NotNull
    fun transfers(
        txHashes: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): List<TxTransfer>

    @NotNull
    fun details(
        txHashes: List<String>
    ): List<TxDetail>
}