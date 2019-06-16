package io.api.bloxy.core

import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.tokensale.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATETIME
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


/**
 * API for Analysis of token sales (ICO)
 * More information - https://bloxy.info/api_methods#tokensale
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal interface ITokenSaleApi {

    /**
     * Lists recent token sale aggregated statistics
     * @param contracts to filter
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param timeSpanDays get info for period in days from today (MAX 1000)
     */
    @NotNull
    fun sales(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<Sale>

    /**
     * Lists recent token sale transactions
     * @param contracts to filter
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0)
     * @param timeSpanDays get info for period in days from today (MAX 1000)
     */
    @NotNull
    fun saleTxs(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<SaleTx>

    /**
     * Aggregated statistics for a token sale by days
     * @param contract to filter
     */
    @NotNull
    fun statsDaily(
        contract: String
    ): List<SaleDaily>

    /**
     * Token sale smart contracts and wallets addresses
     * @param contract token smart contract to filter
     */
    @NotNull
    fun statsAddress(
        contract: String
    ): List<SaleAddrStat>

    /**
     * Token sale buyers addresses
     * @param contract to filter
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun buyers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<SaleBuyer>

    /**
     * Shows the wallets, used to collect currency from buyers
     * @param contract to filter
     * @param withIntermediary
     */
    @NotNull
    fun wallets(
        contract: String,
        withIntermediary: Boolean = false
    ): List<SaleWallet>

    /**
     * Calculates money distribution from the tokensale
     * @param contract to filter
     * @param depth how deep should look in the transaction tree (MAX 300)
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param minBalance ignore addresses with this amount or less
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Address>

    /**
     * Calculates the transactions of money distribution from the tokensale
     * @param contract to filter
     * @param depth how deep should look in the transaction tree (MAX 300)
     * @param limit max result (MAX 210000)
     * @param offset of the list from origin (0) (MAX 200000)
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Tx>

    /**
     * Calculates the money sources for the tokensale by analyzing the graph of transaction for the given depth
     * @param contract to filter
     * @param depth how deep should look in the transaction tree (MAX 300)
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param minBalance ignore addresses with this amount or less
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Address>

    /**
     * Analyses the full graph of money transactions on tokensale
     * @param contract to filter
     * @param depth how deep should look in the transaction tree (MAX 300)
     * @param limit max result (MAX 210000)
     * @param offset of the list from origin (0) (MAX 200000)
     * @param minTxAmount minimum amount of transactions
     * @param ignoreAddressWithTxs ignore distribution from addresses with txs more than (MAX 10000, MIN 100)
     * @param since timestamp
     * @param till timestamp
     * @param snapshot take into account only transfers till this time
     */
    @NotNull
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
    ): List<Tx>

    /**
     * Builds the graph of the initial token distribution and calculates the list of token holders with the amounts
     * @param contract to filter
     * @param limit max result (MAX 210000)
     * @param offset of the list from origin (0) (MAX 200000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun tokenDistribution(
        contract: String,
        limit: Int = 5000,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<SaleDistribution>
}