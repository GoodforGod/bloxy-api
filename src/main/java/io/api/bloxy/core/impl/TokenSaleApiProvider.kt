package io.api.bloxy.core.impl

import io.api.bloxy.core.ITokenSaleApi
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.tokensale.*
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenSaleApiProvider(client: IHttpClient, key: String) : ITokenSaleApi, BasicProvider(client, "tokensale", key) {

    override fun sales(contracts: List<String>, limit: Int, offset: Int, timeSpanDays: Int): List<Sale> {
        val urlParam = "days=${toTimeSpan(timeSpanDays)}${tokenAsParam(contracts)}"
        return getOffset("tokens?$urlParam", limit, offset, 1000)
    }

    override fun saleTxs(contracts: List<String>, limit: Int, offset: Int, timeSpanDays: Int): List<SaleTx> {
        val urlParam = "days=${toTimeSpan(timeSpanDays)}${tokenAsParam(contracts)}"
        return getOffset("transactions?$urlParam", limit, offset, 100)
    }

    override fun dailyStats(contract: String): List<SaleDaily> {
        return if (contract.isNullOrEmpty()) emptyList() else parse(get("by_days?token_address=$contract"))
    }

    override fun addrStats(contract: String): List<SaleAddrStatistic> {
        return if (contract.isNullOrEmpty()) emptyList() else parse(get("addresses?token_address=$contract"))
    }

    override fun buyers(contract: String, limit: Int, offset: Int): List<SaleBuyer> {
        return if (contract.isNullOrEmpty()) emptyList() else getOffset(
            "buyers?token_address=$contract",
            limit,
            offset,
            1000
        )
    }

    override fun wallets(contract: String, withIntermediary: Boolean): List<SaleWallet> {
        val params = "addresses?token_address=$contract&with_zero_balances=$withIntermediary"
        return if (contract.isNullOrEmpty()) emptyList() else parse(get(params))
    }

    override fun moneyDistribution(
        contract: String,
        depth: Int,
        limit: Int,
        offset: Int,
        minTxAmount: Int,
        minBalance: Double,
        ignoreAddressWithTxs: Int,
        since: LocalDateTime,
        till: LocalDateTime,
        snapshot: LocalDateTime
    ): List<Address> {
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_balance$minBalance&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val params = "distribution?token_address=$contract$numParams$ignoreParam$dateParams"
        return if (contract.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 1000)
    }

    override fun txsDistribution(
        contract: String,
        depth: Int,
        limit: Int,
        offset: Int,
        minTxAmount: Int,
        ignoreAddressWithTxs: Int,
        since: LocalDateTime,
        till: LocalDateTime,
        snapshot: LocalDateTime
    ): List<Tx> {
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val params = "distribution_transactions?token_address=$contract$numParams$ignoreParam$dateParams"
        return if (contract.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 200000)
    }

    override fun moneySources(
        contract: String,
        depth: Int,
        limit: Int,
        offset: Int,
        minTxAmount: Int,
        minBalance: Double,
        ignoreAddressWithTxs: Int,
        since: LocalDateTime,
        till: LocalDateTime,
        snapshot: LocalDateTime
    ): List<Address> {
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_balance$minBalance&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val params = "source?token_address=$contract$numParams$ignoreParam$dateParams"
        return if (contract.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 1000)
    }

    override fun txsSources(
        contract: String,
        depth: Int,
        limit: Int,
        offset: Int,
        minTxAmount: Int,
        ignoreAddressWithTxs: Int,
        since: LocalDateTime,
        till: LocalDateTime,
        snapshot: LocalDateTime
    ): List<Tx> {
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val params = "source_transactions?token_address=$contract$numParams$ignoreParam$dateParams"
        return if (contract.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 200000)
    }

    override fun tokenDistribution(
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Tx> {
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}"
        val params = "source_transactions?token_address=$contract$dateParams"
        return if (contract.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 200000)
    }
}