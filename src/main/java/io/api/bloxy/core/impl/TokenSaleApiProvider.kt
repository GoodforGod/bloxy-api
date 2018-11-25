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

    companion object {
        val errors = listOf(
            "Tokens? not found by".toRegex()
        )
    }

    override fun sales(contracts: List<String>, limit: Int, offset: Int, timeSpanDays: Int): List<Sale> {
        val params = "days=${toTimeSpan(timeSpanDays)}${tokenAsParam(contracts, "&")}"
        return getOffset("tokens?$params", limit, offset, 1000, skipErrors = errors)
    }

    override fun saleTxs(contracts: List<String>, limit: Int, offset: Int, timeSpanDays: Int): List<SaleTx> {
        val params = "days=${toTimeSpan(timeSpanDays)}${tokenAsParam(contracts, "&")}"
        return getOffset("transactions?$params", limit, offset, 100, skipErrors = errors)
    }

    override fun statsDaily(contract: String): List<SaleDaily> {
        return get("by_days?token_address=${checkAddressRequired(contract)}", errors)
    }

    override fun statsAddress(contract: String): List<SaleAddrStat> {
        return get("addresses?token_address=${checkAddressRequired(contract)}", errors)
    }

    override fun buyers(contract: String, limit: Int, offset: Int): List<SaleBuyer> {
        return getOffset("buyers?token_address=${checkAddressRequired(contract)}", limit, offset, 1000, skipErrors = errors)
    }

    override fun wallets(contract: String, withIntermediary: Boolean): List<SaleWallet> {
        return get("addresses?token_address=${checkAddressRequired(contract)}&with_zero_balances=$withIntermediary", errors)
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
        val snapParam = "&depth_limit=${toDepth(depth)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val params = "distribution?token_address=${checkAddressRequired(contract)}$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
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
        val snapParam = asIgnored(ignoreAddressWithTxs) + dateAsParam("snapshot_time", snapshot)
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_tx_amount=${toZero(minTxAmount)}"
        val params = "distribution_transactions?token_address=${checkAddressRequired(contract)}$numParams$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
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
        val snapParam = "&depth_limit=${toDepth(depth)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val params = "source?token_address=${checkAddressRequired(contract)}$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
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
        val snapParam = asIgnored(ignoreAddressWithTxs) + dateAsParam("snapshot_time", snapshot)
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_tx_amount=${toZero(minTxAmount)}"
        val params = "source_transactions?token_address=${checkAddressRequired(contract)}$numParams$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    override fun tokenDistribution(
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Tx> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "source_transactions?token_address=${checkAddressRequired(contract)}$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }
}