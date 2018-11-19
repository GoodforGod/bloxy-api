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

    private fun protocolAsParam(values: List<String>): String {
        return asParam(values, "&protocol[]=", "protocol[]=")
    }

    private fun contractAsParam(values: List<String>): String {
        return asParam(values, "&smart_contract_address[]=", "smart_contract_address[]=")
    }

    override fun protocols(): List<DexProtocol> {
        return parse(get("protocols"))
    }

    override fun contracts(protocols: List<String>, limit: Int, offset: Int, timeSpanDays: Int): List<DexContract> {
        val params = "smart_contracts?days=${toTimeSpan(timeSpanDays)}${protocolAsParam(protocols)}"
        return getOffset(params, limit, offset)
    }

    override fun trades(
        protocols: List<String>,
        dexContracts: List<String>,
        tokenAddresses: List<String>,
        limit: Int,
        offset: Int,
        timeSpanDays: Int
    ): List<DexTrade> {
        val paramsAddrs = "${contractAsParam(dexContracts)}${tokenAsParam(tokenAddresses)}"
        val params = "trades?days=${toTimeSpan(timeSpanDays)}${protocolAsParam(protocols)}$paramsAddrs"
        return getOffset(params, limit, offset)
    }

    override fun pendingTxs(protocols: List<String>, dexContracts: List<String>): List<DexPending> {
        val params = "pending?${protocolAsParam(dexContracts)}${contractAsParam(dexContracts)}"
        return parse(get(params))
    }

    override fun tradesActive(
        protocols: List<String>,
        dexContracts: List<String>,
        limit: Int,
        offset: Int,
        timeSpanDays: Int
    ): List<DexTradeActive> {
        val paramsAddrs = "${protocolAsParam(protocols)}${contractAsParam(dexContracts)}"
        val params = "trades?days=${toTimeSpan(timeSpanDays)}$paramsAddrs"
        return getOffset(params, limit, offset)
    }
}