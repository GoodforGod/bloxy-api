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

    override fun addressVolumes(
        addresses: List<String>,
        contract: String,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Volume> {
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "volumes?${addressAsParam(addresses)}$tokenParam$dateParams"
        return if (addresses.isNullOrEmpty()) emptyList() else parse(get(params))
    }

    override fun topSenders(
        address: String,
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Sender> {
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "senders?address=$address$tokenParam$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 1000)
    }

    override fun topReceivers(
        address: String,
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<Receiver> {
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "receivers?address=$address$tokenParam$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 1000)
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
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_balance$minBalance&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "distribution?address=$address$tokenParam$numParams$ignoreParam$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 1000000)
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
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth)}&min_balance$minBalance&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "distribution_transactions?address=$address$tokenParam$numParams$ignoreParam$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 200000)
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
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth, 10)}&min_balance$minBalance&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "sources?address=$address$tokenParam$numParams$ignoreParam$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 1000000)
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
        val snapParam = if (snapshot == LocalDateTime.MIN) "" else dateTimeAsParam("snapshot_time", snapshot)
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}$snapParam"
        val numParams = "&depth_limit=${toDepth(depth, 10)}&min_balance$minBalance&min_tx_amount=$minTxAmount"
        val ignoreParam = "&ignore_addresses_with_transaction_limit=${toIgnored(ignoreAddressWithTxs)}"
        val tokenParam = if (contract == "ETH") "" else "&token_address=$contract"
        val params = "source_transactions?address=$address$tokenParam$numParams$ignoreParam$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 10000, 200000)
    }

    override fun transfersAddr(
        addresses: List<String>,
        contracts: List<String>,
        limit: Int,
        offset: Int,
        since: LocalDate,
        till: LocalDate
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "transfers?${addressAsParam(addresses)}${tokenAsParam(contracts)}$dateParams"
        return if (addresses.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset)
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
        val params = "received?${addressAsParam(addresses)}${tokenAsParam(contracts)}$dateParams"
        return if (addresses.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset)
    }

    override fun transfersSent(
        addresses: List<String>,
        contracts: List<String>,
        limit: Int,
        offset: Int,
        since: LocalDate,
        till: LocalDate
    ): List<AddrTransfer> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "sent?${addressAsParam(addresses)}${tokenAsParam(contracts)}$dateParams"
        return if (addresses.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset)
    }

    override fun topSendersCount(
        address: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<SenderSimple> {
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}"
        val params = "senders_by_count?address=$address$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 1000)
    }

    override fun topReceiversCount(
        address: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<ReceiverSimple> {
        val dateParams = "${dateTimeAsParam("from_time", since)}${dateTimeAsParam("till_time", till)}"
        val params = "receivers_by_count?address=$address$dateParams"
        return if (address.isNullOrEmpty()) emptyList() else getOffset(params, limit, offset, 1000)
    }
}