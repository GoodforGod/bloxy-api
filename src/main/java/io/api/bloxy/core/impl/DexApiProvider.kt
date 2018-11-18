package io.api.bloxy.core.impl

import io.api.bloxy.core.IDexApi
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.dex.*


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class DexApiProvider(client: IHttpClient, key: String) : IDexApi, BasicProvider(client, "dex", key) {

    override fun protocols(): List<DexProtocol> {
        return parse(get("protocols"))
    }

    override fun contracts(protocols: List<String>, limit: Int, timeSpanDays: Int): List<DexContract> {
        if(protocols.isEmpty())
            return emptyList()

        return parse(get("protocols${toLimit(limit, )}"))
    }

    override fun trades(
        protocols: List<String>,
        dexContracts: List<String>,
        tokenAddresses: List<String>,
        limit: Int,
        timeSpanDays: Int
    ): List<DexTrade> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun pendingTxs(protocols: List<String>, dexContracts: List<String>): List<DexPending> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun tradesActive(
        protocols: List<String>,
        dexContracts: List<String>,
        limit: Int,
        timeSpanDays: Int
    ): List<DexTradeActive> {
        TODO("not implemented yet") //File | Settings | File Templates
    }
}