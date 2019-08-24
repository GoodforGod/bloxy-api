package io.api.bloxy.core

import io.api.bloxy.model.dto.token.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATETIME
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * API for Analysis of Tokens and holders
 * More information - https://bloxy.info/api_methods#token
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal interface ITokenApi {

    /**
     * Top token holders ordered by the token amount
     * Bloxy - Token Holder List
     * @param contract to check
     * @param limit max result (MAX 100000)
     */
    @NotNull
    fun holders(
        contract: String,
        limit: Int = 100
    ): List<Holder>

    /**
     * Detailed information on token holders, filtered by the count of token send/receive transactions
     * Bloxy - Token Holder Detailed Statistics
     * @param contract to filter
     * @param limit max result (MAX 100000)
     * @param offset of the list from origin (0)
     * @param minBalance ignore addresses with this amount or less
     * @param minReceived
     * @param minSend
     */
    @NotNull
    fun holderDetails(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        minBalance: Double = 1.0e-6,
        minReceived: Int = 1,
        minSend: Int = 0
    ): List<HolderDetails>

    /**
     * Calculate the count of addresses, holding the set of different tokens
     * Bloxy - Correlation Between Tokens
     * @param contracts to check
     */
    @NotNull
    fun holderCorrelations(
        contracts: List<String>
    ): List<TokenCorrelation>

    /**
     * List the tokens, owned by the same holders
     * Bloxy - Search Similar Tokens by Common Holders
     * @param contracts to check
     */
    @NotNull
    fun holderSimilar(
        contracts: String
    ): List<HolderSimilar>

    /**
     * Lists tokens by matching text in symbol or name
     * Bloxy - Search Token by Name or Symbol
     * @param nameOrSymbol of token to look for
     * @param limit max result (MAX 100000)
     */
    @NotNull
    fun findToken(
        nameOrSymbol: String,
        limit: Int = 100
    ): List<Token>

    /**
     * General token information, as symbol, name, type
     * Bloxy - Token Information
     * @param contracts to check
     */
    @NotNull
    fun details(
        contracts: List<String>
    ): List<TokenDetails>

    /**
     * Number of token holders, supply and circulating amount
     * Bloxy - Token Statistics
     * @param contract to check
     */
    @NotNull
    fun statistic(
        contract: String
    ): List<TokenStatistic>

    /**
     * Lists token transfer transactions ( most recent first )
     * Bloxy - Token Transfers List
     * @param contract to filter
     * @param limit max result (MAX 101000 minus offset, there will be N requests performed with MAX limit per one)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun transfers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenTransfer>

    /**
     * Lists token transfer transactions ( most recent first )
     * Bloxy - List tokens
     * @param limit max result (MAX 1010000 minus offset, there will be N requests performed with MAX limit per one)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun list(
        limit: Int = 100,
        offset: Int = 0
    ): List<TokenInfo>

    /**
     * Lists token transfer transactions ( most recent first ) with the origin detection
     * Bloxy - Token Transfers With Origin
     * @param contract to filter
     * @param contracts tokens to filter
     * @param depth max depth of origin detection.
     * @param limit max result (MAX 101000 minus offset, there will be N requests performed with MAX limit per one)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun transfersOrigin(
        contract: String,
        contracts: List<String> = emptyList(),
        depth: Int = 5,
        limit: Int = 10000,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenDistribution>

    /**
     * Lists token amounts transferred between top groups of addresses
     * Bloxy - Token Flow Graph
     * @param contract to filter
     * @param topCount limit to top addresses
     * @param groupCount limit to count of address groups
     * @param limitFlow to filter
     * @param limit max result (MAX 100000 minus offset, there will be N requests performed with MAX limit per one)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun flow(
        contract: String,
        limit: Int = 10000,
        limitFlow: Double = 3.0,
        topCount: Int = 10,
        groupCount: Int = 50,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<TokenGraph>

    /**
     * Lists addresses by hash from token_flow result set
     * Bloxy - Token Flow Group Addresses
     * @param contract to filter
     * @param groupHash to filter
     * @param topCount limit to top addresses
     * @param groupCount limit to count of address groups
     * @param limit max result (MAX 200000 minus offset, there will be N requests performed with MAX limit per one)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun flowGroup(
        contract: String,
        groupHash: String,
        limit: Int = 1000,
        offset: Int = 0,
        limitFlow: Double = 3.0,
        topCount: Int = 50,
        groupCount: Int = 50,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<TokenGroupGraph>

    /**
     * Metrics ( holder count, supply, Gini, Theil & Nakamoto indexes ) by day
     * Bloxy - Historical holder metrics
     * @param contract to filter
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun metrics(
        contract: String,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<TokenGroupGraph>

    /**
     * Calculate the list of addresses, holding the set of different tokens
     * Bloxy - Addresses holding set of specific tokens
     * @param contract to filter
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun specificHolders(
        contract: String,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<TokenGroupGraph>
}