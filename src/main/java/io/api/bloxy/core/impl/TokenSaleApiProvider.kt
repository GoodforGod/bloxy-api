package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.tokensale.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenSaleApiProvider(client: IHttpClient, key: String) : BasicProvider(client, "tokensale", key) {

    companion object {
        val errors = listOf("Tokens? not found by".toRegex())
    }

    @NotNull
    @JvmOverloads
    fun sales(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<Sale> {
        val params = "days=${toTimeSpan(timeSpanDays)}${tokenAsParam(contracts, "&")}"
        return getOffset("tokens?$params", limit, offset, 1000, skipErrors = errors)
    }

    @NotNull
    @JvmOverloads
    fun saleTxs(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<SaleTx> {
        val params = "days=${toTimeSpan(timeSpanDays)}${tokenAsParam(contracts, "&")}"
        return getOffset("transactions?$params", limit, offset, 100, skipErrors = errors)
    }

    @NotNull
    fun statsDaily(
        contract: String
    ): List<SaleDaily> {
        return get("by_days?token_address=${checkAddressRequired(contract)}", errors)
    }

    @NotNull
    fun statsAddress(
        contract: String
    ): List<SaleAddrStat> {
        return get("addresses?token_address=${checkAddressRequired(contract)}", errors)
    }

    @NotNull
    @JvmOverloads
    fun buyers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<SaleBuyer> {
        return getOffset("buyers?token_address=${checkAddressRequired(contract)}", limit, offset, 1000, skipErrors = errors)
    }

    @NotNull
    @JvmOverloads
    fun wallets(
        contract: String,
        withIntermediary: Boolean = false
    ): List<SaleWallet> {
        return get(
            "addresses?token_address=${checkAddressRequired(contract)}&with_zero_balances=$withIntermediary",
            errors
        )
    }

    @NotNull
    @JvmOverloads
    fun moneyDistribution(
        contract: String,
        depth: Int = 10,
        limit: Int = 100,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Address> {
        val snapParam = "&depth_limit=${toDepth(depth)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val params = "distribution?token_address=${checkAddressRequired(contract)}$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    @NotNull
    @JvmOverloads
    fun txsDistribution(
        contract: String,
        depth: Int = 10,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Tx> {
        val snapParam = asIgnored(ignoreAddressWithTxs) + dateAsParam("snapshot_time", snapshot)
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_tx_amount=${toZero(minTxAmount)}"
        val params = "distribution_transactions?token_address=${checkAddressRequired(contract)}$numParams$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    @NotNull
    @JvmOverloads
    fun moneySources(
        contract: String,
        depth: Int = 5,
        limit: Int = 100,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Address> {
        val snapParam = "&depth_limit=${toDepth(depth)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val params = "source?token_address=${checkAddressRequired(contract)}$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    @NotNull
    @JvmOverloads
    fun txsSources(
        contract: String,
        depth: Int = 5,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Tx> {
        val snapParam = asIgnored(ignoreAddressWithTxs) + dateAsParam("snapshot_time", snapshot)
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_tx_amount=${toZero(minTxAmount)}"
        val params = "source_transactions?token_address=${checkAddressRequired(contract)}$numParams$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    @NotNull
    @JvmOverloads
    fun tokenDistribution(
        contract: String,
        limit: Int = 5000,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Tx> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "source_transactions?token_address=${checkAddressRequired(contract)}$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }
}