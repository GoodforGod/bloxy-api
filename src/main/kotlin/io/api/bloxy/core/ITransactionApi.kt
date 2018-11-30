package io.api.bloxy.core

import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxTransfer
import org.jetbrains.annotations.NotNull


/**
 * API for reading transaction information
 * More information - https://bloxy.info/api_methods#tx
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal interface ITransactionApi {

    /**
     * List of all transfers in the given transaction
     * @param txHashes to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun transfers(
        txHashes: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): List<TxTransfer>

    /**
     * Transaction information by hash
     * @param txHashes to filter
     */
    @NotNull
    fun details(
        txHashes: List<String>
    ): List<TxDetail>
}