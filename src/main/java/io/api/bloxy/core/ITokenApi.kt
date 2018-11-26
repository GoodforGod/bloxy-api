package io.api.bloxy.core

import io.api.bloxy.model.dto.token.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATETIME
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


/**
 * API for Analysis of Tokens and holders
 * More information - https://bloxy.info/api_methods#token
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITokenApi {

    /**
     * Top token holders ordered by the token amount
     * @param contract to check
     * @param limit max result
     */
    @NotNull
    fun holders(
        contract: String,
        limit: Int = 100
    ): List<Holder>

    /**
     * Detailed information on token holders, filtered by the count of token send/receive transactions
     * @param contract to filter
     * @param limit max result
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
     * @param contracts to check
     */
    @NotNull
    fun holderCorrelations(
        contracts: List<String>
    ): List<TokenCorrelation>

    /**
     * List the tokens, owned by the same holders
     * @param contracts to check
     */
    @NotNull
    fun holderSimilar(
        contracts: String
    ): List<HolderSimilar>

    /**
     * Lists tokens by matching text in symbol or name
     * @param nameOrSymbol of token to look for
     * @param limit max result
     */
    @NotNull
    fun tokenByNameOrSymbol(
        nameOrSymbol: String,
        limit: Int = 100
    ): List<Token>

    /**
     * General token information, as symbol, name, type
     * @param contracts to check
     */
    @NotNull
    fun tokenDetails(
        contracts: List<String>
    ): List<TokenDetails>

    /**
     * Number of token holders, supply and circulating amount
     * @param contract to check
     */
    @NotNull
    fun tokenStatistic(
        contract: String
    ): List<TokenStatistic>

    /**
     * Lists token transfer transactions ( most recent first )
     * @param contract to filter
     * @param limit max result
     * @param offset of the list from origin (0)
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
}