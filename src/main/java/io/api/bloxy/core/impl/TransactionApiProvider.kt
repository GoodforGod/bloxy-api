package io.api.bloxy.core.impl

import io.api.bloxy.core.ITransactionApi
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxTransfer


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TransactionApiProvider(client: IHttpClient, key: String) : ITransactionApi, BasicProvider(client, "tx", key) {

    private fun hashAsParam(values: List<String>): String {
        return asParam(values, "&tx_hash[]=", "tx_hash[]=")
    }

    override fun transfers(txHashes: List<String>, limit: Int, offset: Int): List<TxTransfer> {
        val param = "transactions?${hashAsParam(txHashes)}"
        return if (txHashes.isNullOrEmpty()) emptyList() else getOffset(param, limit, offset, 100000)
    }

    override fun details(txHashes: List<String>): List<TxDetail> {
        return if (txHashes.isNullOrEmpty()) emptyList() else get("transactions?${hashAsParam(txHashes)}")
    }
}