package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.address.AddrStatistic
import io.api.bloxy.model.dto.address.Balance
import io.api.bloxy.model.dto.moneyflow.*
import io.api.bloxy.model.dto.token.Holder
import io.api.bloxy.model.dto.token.TokenDetails
import io.api.bloxy.model.dto.token.TokenStatistic
import io.api.bloxy.model.dto.token.TokenTransfer
import io.api.bloxy.model.dto.tokensale.SaleAddrStat
import io.api.bloxy.model.dto.transaction.TxDetail
import io.api.bloxy.model.dto.transaction.TxTransfer
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * API for Maltego integration
 * More information - https://bloxy.info/api_methods#maltego
 *
 * @see io.api.bloxy.core.IMaltegoApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 27.02.2019
 */
class MaltegoApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "maltego", key) {

    companion object {
        private val errors = listOf(
            "Tokens? not found by".toRegex(),
            "No currency found by token".toRegex(),
            "Currency not found by".toRegex()
        )
    }

    private fun hashAsParam(values: List<String>): String {
        return asParam(values, "tx_hash[]=", "&tx_hash[]=")
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrBalance
     */
    @NotNull
    fun addrBalance(
        address: String
    ): Balance {
        return Balance(get("balance?address=${checkAddrRequired(address)}"))
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrStatistics
     */
    @NotNull
    fun addrStatistics(
        addresses: List<String>
    ): List<AddrStatistic> {
        return validOnly(get("address_stat?${addressAsParamRequired(addresses)}"))
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrTopSenders
     */
    @NotNull
    @JvmOverloads
    fun addrTopSenders(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Sender> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddrRequired(contract)}"
        val params = "senders?address=${checkAddrRequired(address)}$tokenParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrTopReceivers
     */
    @NotNull
    @JvmOverloads
    fun addrTopReceivers(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Receiver> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddrRequired(contract)}"
        val params = "receivers?address=${checkAddrRequired(address)}$tokenParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /**
     *  @see io.api.bloxy.core.IMaltegoApi.addrTransfersAll
     */
    @NotNull
    @JvmOverloads
    fun addrTransfersAll(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "transfers?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrTransfersReceived
     */
    @NotNull
    @JvmOverloads
    fun addrTransfersReceived(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrReceived> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "received?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrTransfersSent
     */
    @NotNull
    @JvmOverloads
    fun addrTransfersSent(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrSent> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "sent?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrMoneySource
     */
    @NotNull
    @JvmOverloads
    fun addrMoneySource(
        address: String,
        contract: String = "ETH",
        depth: Int = 5,
        limit: Int = 1000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 1000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Address> {
        val snapParam = "&depth_limit=${toDepth(depth, 10)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs, 1000)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddrRequired(contract)}"
        val params = "sources?address=${checkAddrRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 1000000, errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.addrMoneyDistribution
     */
    @NotNull
    @JvmOverloads
    fun addrMoneyDistribution(
        address: String,
        contract: String = "ETH",
        depth: Int = 10,
        limit: Int = 1000,
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
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddrRequired(contract)}"
        val params = "distribution?address=${checkAddrRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 1000000, errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.txTransfers
     */
    @NotNull
    @JvmOverloads
    fun txTransfers(
        txHashes: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): List<TxTransfer> {
        val param = "transfers?${hashAsParam(checkTxsRequired(txHashes))}"
        return getOffset(param, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.txDetails
     */
    @NotNull
    fun txDetails(
        txHashes: List<String>
    ): List<TxDetail> {
        return get("info?${hashAsParam(checkTxsRequired(txHashes))}")
    }

    /**
     *  @see io.api.bloxy.core.IMaltegoApi.tokenSaleStatsAddress
     */
    @NotNull
    fun tokenSaleStatsAddress(
        contract: String
    ): List<SaleAddrStat> {
        return get("addresses?token_address=${checkAddrRequired(contract)}", errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.tokenDetails
     */
    @NotNull
    fun tokenDetails(
        contracts: List<String>
    ): List<TokenDetails> {
        return get("token_info?${tokenAsParamRequired(contracts)}", errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.tokenStatistic
     */
    @NotNull
    fun tokenStatistic(
        contract: String
    ): List<TokenStatistic> {
        return get("token_stat?token=${checkAddrRequired(contract)}", errors)
    }

    /**
     * @see io.api.bloxy.core.IMaltegoApi.tokenHolders
     */
    @NotNull
    @JvmOverloads
    fun tokenHolders(
        contract: String,
        limit: Int = 100
    ): List<Holder> {
        return get("token_holders_list?token=${checkAddrRequired(contract)}&limit=${toLimit(limit)}", errors)
    }

    /**
     *  @see io.api.bloxy.core.IMaltegoApi.tokenTransfers
     */
    @NotNull
    @JvmOverloads
    fun tokenTransfers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenTransfer> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "token=${checkAddrRequired(contract)}$dateParams"
        return getOffset("transfers?$params", limit, offset, skipErrors = errors)
    }
}