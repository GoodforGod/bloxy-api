package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxEvent
import io.api.bloxy.model.dto.transaction.TxTrace
import io.api.bloxy.model.dto.transaction.TxTransfer
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


/**
 * API for reading transaction information
 * More information - https://bloxy.info/api_methods#tx
 *
 * @see io.api.bloxy.core.ITransactionApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TransactionApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "tx", key) {

    private fun hashAsParam(values: List<String>): String = asParam(values, "&tx_hash[]=", "tx_hash[]=")

    /**
     * @see io.api.bloxy.core.ITransactionApi.transfers
     */
    @NotNull
    @JvmOverloads
    fun transfers(
        txHashes: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): List<TxTransfer> {
        val param = "transfers?${hashAsParam(checkTxRequired(txHashes))}"
        return getOffset(param, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ITransactionApi.details
     */
    @NotNull
    fun details(
        txHashes: List<String>
    ): List<TxDetail> {
        return get("info?${hashAsParam(checkTxRequired(txHashes))}")
    }

    /**
     * @see io.api.bloxy.core.ITransactionApi.callsByTx
     */
    @NotNull
    fun callsByTx(
        txHash: String
    ) : List<TxTrace> {
        return get("tx_calls?${checkTxRequired(txHash)}")
    }

    /**
     * @see io.api.bloxy.core.ITransactionApi.callsByContract
     */
    @NotNull
    @JvmOverloads
    fun callsByContract(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<TxTrace> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        val params = "smart_contract_address=${checkAddrRequired(contract)}$dateParams"
        return getOffset("sc_calls?$params", limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ITransactionApi.eventsByTx
     */
    @NotNull
    fun eventsByTx(
        txHash: String
    ) : List<TxEvent> {
        return get("tx_events?${checkTxRequired(txHash)}")
    }

    /**
     * @see io.api.bloxy.core.ITransactionApi.eventsByContract
     */
    @NotNull
    @JvmOverloads
    fun eventsByContract(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<TxEvent> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        val params = "smart_contract_address=${checkAddrRequired(contract)}$dateParams"
        return getOffset("sc_events?$params", limit, offset)
    }
}