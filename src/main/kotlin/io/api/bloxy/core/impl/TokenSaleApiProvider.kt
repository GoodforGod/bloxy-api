package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.tokensale.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


/**
 * API for Analysis of token sales (ICO)
 * More information - https://bloxy.info/api_methods#tokensale
 *
 * @see io.api.bloxy.core.ITokenSaleApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenSaleApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "tokensale", key) {

    companion object {
        private val errors = listOf("Tokens? not found by".toRegex())
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.sales
     */
    @NotNull
    @JvmOverloads
    fun sales(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<Sale> {
        val params = "days=${toDays(timeSpanDays)}${tokenAsParam(contracts, "&")}"
        return getOffset("tokens?$params", limit, offset, 1000, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.saleTxs
     * */
    @NotNull
    @JvmOverloads
    fun saleTxs(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<SaleTx> {
        val params = "days=${toDays(timeSpanDays)}${tokenAsParam(contracts, "&")}"
        return getOffset("transactions?$params", limit, offset, 100, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.statsDaily
     */
    @NotNull
    fun statsDaily(
        contract: String
    ): List<SaleDaily> {
        return get("by_days?token_address=${checkAddrRequired(contract)}", errors)
    }

    /**
     *  @see io.api.bloxy.core.ITokenSaleApi.statsAddress
     */
    @NotNull
    fun statsAddress(
        contract: String
    ): List<SaleAddrStat> {
        return get("addresses?token_address=${checkAddrRequired(contract)}", errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.buyers
     */
    @NotNull
    @JvmOverloads
    fun buyers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<SaleBuyer> {
        return getOffset("buyers?token_address=${checkAddrRequired(contract)}", limit, offset, 1000, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.wallets
     */
    @NotNull
    @JvmOverloads
    fun wallets(
        contract: String,
        withIntermediary: Boolean = false
    ): List<SaleWallet> {
        return get(
            "wallets?token_address=${checkAddrRequired(contract)}&with_zero_balances=$withIntermediary",
            errors
        )
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.moneyDistribution
     */
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
        val numParams = "&min_balance=${toZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val params = "distribution?token_address=${checkAddrRequired(contract)}$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenSaleApi.txsDistribution
     */
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
        val params = "distribution_transactions?token_address=${checkAddrRequired(contract)}$numParams$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    /**
     *  @see io.api.bloxy.core.ITokenSaleApi.moneySources
     */
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
        val numParams = "&min_balance=${toZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val params = "source?token_address=${checkAddrRequired(contract)}$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /**
     *  @see io.api.bloxy.core.ITokenSaleApi.txsSources
     */
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
        val params = "source_transactions?token_address=${checkAddrRequired(contract)}$numParams$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    /**
     *  @see io.api.bloxy.core.ITokenSaleApi.tokenDistribution
     */
    @NotNull
    @JvmOverloads
    fun tokenDistribution(
        contract: String,
        limit: Int = 5000,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<SaleDistribution> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "initial_distribution?token_address=${checkAddrRequired(contract)}$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }
}