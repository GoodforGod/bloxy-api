package io.api.bloxy.core.impl

import io.api.bloxy.core.IMoneyFlowApi
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.moneyflow.*
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class MoneyFlowApiProvider(client: IHttpClient, key:String) : IMoneyFlowApi, BasicProvider(client, "money_flow", key) {

    companion object {
        val errors = listOf(
            "Token not found by",
            "Tokens not found by"
        )
    }

    override fun addressVolumes(
        addresses: List<String>,
        contract: String,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Volume> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "volumes?${addressAsParamRequired(addresses)}$tokenParam$dateParams"
        return get(params, errors)
    }

    override fun topSenders(
        address: String,
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Sender> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "senders?address=${checkAddressRequired(address)}$tokenParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    override fun topReceivers(
        address: String,
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Receiver> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "receivers?address=${checkAddressRequired(address)}$tokenParam$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    override fun moneyDistribution(
        address: String,
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
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "distribution?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 1000000, errors)
    }

    override fun txsDistribution(
        address: String,
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
    ): List<Tx> {
        val snapParam = "&depth_limit=${toDepth(depth)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "distribution_transactions?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    override fun moneySource(
        address: String,
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
        val snapParam = "&depth_limit=${toDepth(depth, 10)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs, 1000)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "sources?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 1000000, errors)
    }

    override fun txsSource(
        address: String,
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
    ): List<Tx> {
        val snapParam = "&depth_limit=${toDepth(depth, 10)}${dateAsParam("snapshot_time", snapshot)}"
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}$snapParam"
        val numParams = "&min_balance=${toNoZero(minBalance)}&min_tx_amount=${toZero(minTxAmount)}"
        val ignoreParam = asIgnored(ignoreAddressWithTxs, 1000)
        val tokenParam = if (contract == "ETH") "" else "&token_address=${checkAddressRequired(contract)}"
        val params = "source_transactions?address=${checkAddressRequired(address)}$tokenParam$numParams$ignoreParam$dateParams"
        return getOffset(params, limit, offset, 10000, 200000, errors)
    }

    override fun transfersAll(
        addresses: List<String>,
        contracts: List<String>,
        limit: Int,
        offset: Int,
        since: LocalDate,
        till: LocalDate
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "transfers?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    override fun transfersReceived(
        addresses: List<String>,
        contracts: List<String>,
        limit: Int,
        offset: Int,
        since: LocalDate,
        till: LocalDate
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "received?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    override fun transfersSend(
        addresses: List<String>,
        contracts: List<String>,
        limit: Int,
        offset: Int,
        since: LocalDate,
        till: LocalDate
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "sent?${addressAsParamRequired(addresses)}${tokenAsParam(contracts, "&")}$dateParams"
        return getOffset(params, limit, offset, skipErrors = errors)
    }

    override fun topSendersCount(
        address: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<SenderSimple> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "senders_by_count?address=${checkAddressRequired(address)}$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }

    override fun topReceiversCount(
        address: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<ReceiverSimple> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "receivers_by_count?address=${checkAddressRequired(address)}$dateParams"
        return getOffset(params, limit, offset, 1000, skipErrors = errors)
    }
}