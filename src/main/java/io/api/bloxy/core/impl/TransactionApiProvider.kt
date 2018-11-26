package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxTransfer
import org.jetbrains.annotations.NotNull


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TransactionApiProvider(client: IHttpClient, key: String) : BasicProvider(client, "tx", key) {

    private fun hashAsParam(values: List<String>): String {
        return asParam(values, "&tx_hash[]=", "tx_hash[]=")
    }

    /** @see io.api.bloxy.core.ITokenSaleApi.tokenDistribution */
    @NotNull
    @JvmOverloads
    fun transfers(
        txHashes: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): List<TxTransfer> {
        val param = "transfers?${hashAsParam(checkTxsRequired(txHashes))}"
        return getOffset(param, limit, offset)
    }

    /** @see io.api.bloxy.core.ITokenSaleApi.tokenDistribution */
    @NotNull
    fun details(
        txHashes: List<String>
    ): List<TxDetail> {
        return get("info?${hashAsParam(checkTxsRequired(txHashes))}")
    }
}