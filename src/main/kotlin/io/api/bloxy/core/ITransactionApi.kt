package io.api.bloxy.core

import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxEvent
import io.api.bloxy.model.dto.transaction.TxTrace
import io.api.bloxy.model.dto.transaction.TxTransfer
import io.api.bloxy.util.ParamConverter
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


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

    /**
     * All calls of smart contracts in transaction(s), including internal calls
     * @param txHash to filter
     */
    @NotNull
    fun callsByTx(
        txHash: String
    ): List<TxTrace>

    /**
     * All calls of smart contracts in transactions, initiated from the specified smart contract
     * including internal calls
     * @param contract to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun callsByContract(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = ParamConverter.MIN_DATE,
        till: LocalDate = ParamConverter.MAX_DATE
    ): List<TxTrace>

    /**
     * All events, logged in transaction(s), including internal calls
     * @param txHash to filter
     */
    @NotNull
    fun eventsByTx(
        txHash: String
    ): List<TxEvent>

    /**
     * All events in transactions, initiated from the specified smart contract
     * @param contract to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun eventsByContract(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = ParamConverter.MIN_DATE,
        till: LocalDate = ParamConverter.MAX_DATE
    ): List<TxEvent>
}