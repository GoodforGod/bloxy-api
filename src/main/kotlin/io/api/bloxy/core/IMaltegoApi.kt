package io.api.bloxy.core

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
import io.api.bloxy.util.ParamConverter
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATETIME
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * API for Montego integration
 * More information - https://bloxy.info/api_methods#maltego
 *
 * @author GoodforGod
 * @since 13.12.2018
 */
internal interface IMaltegoApi {

    /**
     * Lists the balance of all currency/tokens for this address
     * Bloxy - Address Balance by Currencies
     * @param address to check
     */
    @NotNull
    fun addrBalance(
        address: String
    ): Balance

    /**
     * Counts and aggregates basic statistic on addresses
     * Bloxy - Batch Address Statistics
     * @param addresses to check
     */
    @NotNull
    fun addrStatistics(
        addresses: List<String>
    ): List<AddrStatistic>

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
    fun addrTopSenders(
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
    fun addrTopReceivers(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<Receiver>

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
    fun addrTransfersAll(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = ParamConverter.MIN_DATE,
        till: LocalDate = ParamConverter.MAX_DATE
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
    fun addrTransfersReceived(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = ParamConverter.MIN_DATE,
        till: LocalDate = ParamConverter.MAX_DATE
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
    fun addrTransfersSent(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = ParamConverter.MIN_DATE,
        till: LocalDate = ParamConverter.MAX_DATE
    ): List<AddrSent>

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
    ): List<Address>

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
    ): List<Address>

    /**
     * List of all transfers in the given transaction
     * Bloxy - List transfers by TX hash
     * @param txHashes to filter
     * @param limit max result (MAX 200000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun txTransfers(
        txHashes: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): List<TxTransfer>

    /**
     * Transaction information by hash
     * Bloxy - Transaction information by TX hash
     * @param txHashes to filter
     */
    @NotNull
    fun txDetails(
        txHashes: List<String>
    ): List<TxDetail>

    /**
     * General token information, as symbol, name, type
     * Bloxy - Token Information
     * @param contracts to check
     */
    @NotNull
    fun tokenDetails(
        contracts: List<String>
    ): List<TokenDetails>

    /**
     * Number of token holders, supply and circulating amount
     * Bloxy - Token Statistics
     * @param contract to check
     */
    @NotNull
    fun tokenStatistic(
        contract: String
    ): List<TokenStatistic>

    /**
     * Top token holders ordered by the token amount
     * Bloxy - Token Holder List
     * @param contract to check
     * @param limit max result (MAX 100000)
     */
    @NotNull
    fun tokenHolders(
        contract: String,
        limit: Int = 100
    ): List<Holder>

    /**
     * Lists token transfer transactions ( most recent first )
     * Bloxy - Token Transfers List
     * @param contract to filter
     * @param limit max result (MAX 101000 minus offset, there will be N requests performed with 1000 limit per one)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun tokenTransfers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenTransfer>

    /**
     * Token sale smart contracts and wallets addresses
     * Bloxy - Token Sale Addresses Statistics
     * @param contract token smart contract to filter
     */
    @NotNull
    fun tokenSaleStatsAddress(
        contract: String
    ): List<SaleAddrStat>
}