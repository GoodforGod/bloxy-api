package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.moneyflow.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class MoneyFlowApiProvider(client: IHttpClient, key: String) : BasicProvider(client, "money_flow", key) {

    companion object {
        val errors = listOf(
            "Tokens? not found by".toRegex()
        )
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.addressVolumes */
    @NotNull
    @JvmOverloads
    fun addressVolumes(
        addresses: List<String>,
        contract: String = "ETH",
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Volume> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "volumes?${addressAsParamRequired(addresses)}$tokenParam$dateParams"
        return get(params, errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.topSenders */
    @NotNull
    @JvmOverloads
    fun topSenders(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Sender> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "senders?address=${checkAddressRequired(address)}$tokenParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.topReceivers */
    @NotNull
    @JvmOverloads
    fun topReceivers(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Receiver> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "receivers?address=${checkAddressRequired(address)}$tokenParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.moneyDistribution */
    @NotNull
    @JvmOverloads
    fun moneyDistribution(
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
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "distribution?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 1000000, errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.txsDistribution */
    @NotNull
    @JvmOverloads
    fun txsDistribution(
        address: String,
        contract: String = "ETH",
        depth: Int = 10,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Tx> {
        val snapParam = "&depth_limit=${toDepth(depth)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "distribution_transactions?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.moneySource */
    @NotNull
    @JvmOverloads
    fun moneySource(
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
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs, 1000)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "sources?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 1000000, errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.txsSource */
    @NotNull
    @JvmOverloads
    fun txsSource(
        address: String,
        contract: String = "ETH",
        depth: Int = 5,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 1000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Tx> {
        val snapParam = "&depth_limit=${toDepth(depth, 10)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs, 1000)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "source_transactions?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.transfersAll */
    @NotNull
    @JvmOverloads
    fun transfersAll(
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

    /** @see io.api.bloxy.core.IMoneyFlowApi.transfersReceived */
    @NotNull
    @JvmOverloads
    fun transfersReceived(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "received?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.transfersSend */
    @NotNull
    @JvmOverloads
    fun transfersSend(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "sent?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.topSendersCount */
    @NotNull
    @JvmOverloads
    fun topSendersCount(
        address: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<SenderSimple> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "senders_by_count?address=${checkAddressRequired(address)}$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    /** @see io.api.bloxy.core.IMoneyFlowApi.topReceiversCount */
    @NotNull
    @JvmOverloads
    fun topReceiversCount(
        address: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<ReceiverSimple> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "receivers_by_count?address=${checkAddressRequired(address)}$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }
}