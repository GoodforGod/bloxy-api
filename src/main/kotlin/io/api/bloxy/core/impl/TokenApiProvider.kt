package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.token.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * API for Analysis of Tokens and holders
 * More information - https://bloxy.info/api_methods#token
 *
 * @see io.api.bloxy.core.ITokenApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "token", key) {

    companion object {
        private val errors = listOf(
            "^No currency found by token".toRegex(),
            "^Currency not found by".toRegex(),
            "^Token not found".toRegex()
        )
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.holders
     */
    @NotNull
    @JvmOverloads
    fun holders(
        contract: String,
        limit: Int = 100
    ): List<Holder> {
        return get("token_holders_list?token=${checkAddrRequired(contract)}&limit=${toLimit(limit)}", errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.holderDetails
     */
    @NotNull
    @JvmOverloads
    fun holderDetails(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        minBalance: Double = 1.0e-6,
        minReceived: Int = 1,
        minSend: Int = 0
    ): List<HolderDetails> {
        val txCountParam = "&to_count_min=${toNoZero(minReceived)}&from_count_min=${toZero(minSend)}"
        val urlParam = "token=${checkAddrRequired(contract)}&min_balance=${toNoZero(minBalance)}$txCountParam"
        return getOffset("token_holders_details?$urlParam", limit, offset, skipErrors = errors)
    }

    /**
     *  @see io.api.bloxy.core.ITokenApi.holderCorrelations
     */
    @NotNull
    fun holderCorrelations(
        contracts: List<String>
    ): List<TokenCorrelation> {
        val params = "token_correlation?${asTokenRequired(contracts)}"
        return if (contracts.size < 2) emptyList() else get(params, errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.holderSimilar
     */
    @NotNull
    fun holderSimilar(
        contracts: String
    ): List<HolderSimilar> {
        return get("similar_tokens?token=${checkAddrRequired(contracts)}", errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.findToken
     */
    @NotNull
    @JvmOverloads
    fun findToken(
        nameOrSymbol: String,
        limit: Int = 100
    ): List<Token> {
        return get("token_search?search=$nameOrSymbol&limit=${toLimit(limit)}", errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.details
     */
    @NotNull
    fun details(
        contracts: List<String>
    ): List<TokenDetails> {
        return get("token_info?${asTokenRequired(contracts)}", errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.statistic
     */
    @NotNull
    fun statistic(
        contract: String
    ): List<TokenStatistic> {
        return get("token_stat?token=${checkAddrRequired(contract)}", errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.transfers
     */
    @NotNull
    @JvmOverloads
    fun transfers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenTransfer> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        val params = "token=${checkAddrRequired(contract)}$dateParams"
        return getOffset("transfers?$params", limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.list
     */
    @JvmOverloads
    @NotNull
    fun list(
        limit: Int = 100,
        offset: Int = 0
    ) : List<TokenInfo> {
        return getOffset("list?", limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.transfersOrigin
     */
    @NotNull
    @JvmOverloads
    fun transfersOrigin(
        contract: String,
        contracts: List<String> = emptyList(),
        depth: Int = 5,
        limit: Int = 10000,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ) : List<TokenDistribution> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        val params = "token=${checkAddrRequired(contract)}$dateParams${asAddress(contracts)}&max_depth=$depth"
        return getOffset("transfers_with_origin?$params", limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.flow
     */
    @NotNull
    @JvmOverloads
    fun flow(
        contract: String,
        limit: Int = 10000,
        limitFlow: Double = 3.0,
        topCount: Int = 10,
        groupCount: Int = 50,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<TokenGraph> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        val limitParam = "&limit_flow_ratio=$limitFlow&group_count=$groupCount&top_count=$topCount"
        val params = "token=${checkAddrRequired(contract)}$dateParams$limitParam"
        return getOffset("token_flow?$params", limit, 0, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.flowGroup
     */
    @NotNull
    @JvmOverloads
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
    ): List<TokenGroupGraph> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        val limitParam = "&limit_flow_ratio=$limitFlow&group_count=$groupCount&top_count=$topCount"
        val params = "token=${checkAddrRequired(contract)}&group_hash=${checkNonBlank(groupHash)}$dateParams$limitParam"
        return getOffset("token_flow_group?$params", limit, offset, skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.metrics
     */
    @NotNull
    fun metrics(
        contract: String,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<TokenGroupGraph> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        return get("holder_metrics?token=${checkAddrRequired(contract)}&$dateParams", skipErrors = errors)
    }

    /**
     * @see io.api.bloxy.core.ITokenApi.specificHolders
     */
    @NotNull
    fun specificHolders(
        contract: String,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<TokenGroupGraph> {
        val dateParams = "${asDate("from_time", since)}${asDate("till_time", till)}"
        return get("token_correlated_addresses?token=${checkAddrRequired(contract)}&$dateParams", skipErrors = errors)
    }
}