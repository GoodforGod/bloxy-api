package io.api.bloxy.core

import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.moneyflow.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATETIME
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * API for Money Flow Analysis between wallets. Supports Ether and any tokens
 * More information - https://bloxy.info/api_methods#money_flow
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal interface IMoneyFlowApi {

    /**
     * Aggregates amount of receive and sent amounts for each from the list of addresses
     * Bloxy - Transactions Volume by Address
     * @param addresses to look for
     * @param contract to filter (ETH default)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun addressVolumes(
        addresses: List<String>,
        contract: String = "ETH",
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Volume>

    /**
     * Aggregates amount of receive transactions for specific currency and select top senders ( by amount )
     * Bloxy - Top Senders To Address in Currency
     * @param address to look for
     * @param contract to filter (ETH default)
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun topSenders(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Sender>

    /**
     * Aggregates amount of sent transactions for specific currency and select top receivers ( by amount )
     * Bloxy - Top Receivers From Address in Currency
     * @param address to look for
     * @param contract to filter (ETH default)
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun topReceivers(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Receiver>

    /**
     * Analyses the full graph of money transactions and calculates the money distribution from the given address
     * Bloxy - Money Distribution From the Address
     * @param address to look for
     * @param contract to filter (ETH default)
     * @param depth how deep should look in the transaction tree (MAX 300)
     * @param limit max result (MAX 1010000)
     * @param offset of the list from origin (0) (MAX 1000000)
     * @param minBalance ignore addresses with this amount or less
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Address>

    /**
     * Analyses the full graph of money transactions
     * Outputs the transactions of the flow how money distributed from the given address
     * Bloxy - Distribution Transactions From Address
     * @param address to look for
     * @param contract to filter (ETH default)
     * @param depth how deep should look in the transaction tree (MAX 300)
     * @param limit max result (MAX 210000)
     * @param offset of the list from origin (0) (MAX 200000)
     * @param minBalance ignore addresses with this amount or less
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Tx>

    /**
     * Analyses the full graph of money transactions and calculates the money sources for the given address
     * Bloxy - Source Money For the Address
     * @param address to look for
     * @param contract to filter (ETH default)
     * @param depth how deep should look in the transaction tree (MAX 10)
     * @param limit max result (MAX 1010000)
     * @param offset of the list from origin (0) (MAX 1000000)
     * @param minBalance ignore addresses with this amount or less
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Address>

    /**
     * Analyses the full graph of money transactions.
     * Outputs the transactions of the flow how money come into given address
     * Bloxy - Source Transactions For the Address
     * @param address to look for
     * @param contract to filter (ETH default)
     * @param depth how deep should look in the transaction tree (MAX 10)
     * @param limit max result (MAX 210000)
     * @param offset of the list from origin (0) (MAX 200000)
     * @param minBalance ignore addresses with this amount or less
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Tx>

    /**
     * List of all transfers to/from the given address
     * Bloxy - Address Transfers
     * @param addresses to look for
     * @param contracts to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default is 100 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun transfersAll(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer>

    /**
     * List of transfers to the given address
     * Bloxy - Received transfers to Address
     * @param addresses to look for
     * @param contracts to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default is 100 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun transfersReceived(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrReceived>

    /**
     * List of transfers from the given address
     * Bloxy - Sent transfers from Address
     * @param addresses to look for
     * @param contracts to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default is 100 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun transfersSent(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrSent>

    /**
     * Aggregates amount of receive transactions and select top senders ( by Transfer Count )
     * Bloxy - Top Senders To Address by Transfer Count
     * @param address to look for
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default is 100 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun topSendersCount(
        address: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<SenderSimple>

    /**
     * Aggregates amount of sent transactions and select top receivers ( by Transfer Count )
     * Bloxy - Top Receivers From Address by Transfer Count
     * @param address to look for
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp (default is 100 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun topReceiversCount(
        address: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<ReceiverSimple>

    /**
     * Show the number of transfers (ETH and token) an address has had over time (by day)
     * Bloxy - Daily transfers to/from address(es)
     * @param addresses to look for
     * @param contracts to filter
     * @param since timestamp (default is 100 days ago)
     * @param till timestamp (default now)
     */
    @NotNull
    fun daily(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<FlowDaily>
}