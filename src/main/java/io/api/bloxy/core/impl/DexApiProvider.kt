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

    companion object {
        val errors = listOf(
            "Protocols not found by",
            "Not found any DEXes"
        )
    }

    private fun protocolAsParam(values: List<String>): String {
        return asParam(values, "&protocol[]=", "protocol[]=")
    }

    private fun contractAsParam(values: List<String>): String {
        return asParam(checkAddress(values), "&smart_contract_address[]=", "smart_contract_address[]=")
    }

    override fun protocols(): List<DexProtocol> {
        return get("protocols?")
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
        val params = "trades?days=${toTimeSpan(timeSpanDays, 30)}${protocolAsParam(protocols)}$paramsAddrs"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    override fun pendingTxs(protocols: List<String>, dexContracts: List<String>): List<DexTxPending> {
        val paramDex = contractAsParam(dexContracts)
        val formattedDex = if (protocols.isEmpty()) paramDex.replace("&", "") else paramDex
        val params = "pending?${protocolAsParam(protocols)}$formattedDex"
        return get(params, errors)
    }

    override fun tradesActive(
        protocols: List<String>,
        dexContracts: List<String>,
        limit: Int,
        offset: Int,
        timeSpanDays: Int
    ): List<DexTradeActive> {
        val paramsAddrs = "${protocolAsParam(protocols)}${contractAsParam(dexContracts)}"
        val params = "traders?days=${toTimeSpan(timeSpanDays, 720)}$paramsAddrs"
        return getOffset(params, limit, offset, skipErrors = errors)
    }
}