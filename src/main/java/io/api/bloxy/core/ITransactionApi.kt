package io.api.bloxy.core

import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxTransfer


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITransactionApi {

    fun transfers(
        txHashes: List<String>,
        limit: Int = 1000
    ): List<TxTransfer>

    fun details(txHashes: List<String>): List<TxDetail>
}