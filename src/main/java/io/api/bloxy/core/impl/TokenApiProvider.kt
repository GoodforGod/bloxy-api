package io.api.bloxy.core.impl

import io.api.bloxy.core.ITokenApi
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.token.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @see ITokenApi
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenApiProvider(client: IHttpClient, key: String) : BasicProvider(client, "token", key) {

    companion object {
        val errors = listOf(
            "No currency found by token".toRegex(),
            "Currency not found by".toRegex()
        )
    }

    /** @see io.api.bloxy.core.ITokenApi.holders */
    @NotNull
    @JvmOverloads
    fun holders(
        contract: String,
        limit: Int = 100
    ): List<Holder> {
        return get("token_holders_list?token=${checkAddressRequired(contract)}&limit=${toLimit(limit)}", errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.holderDetails */
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
        val urlParam = "token=${checkAddressRequired(contract)}&min_balance=${toNoZero(minBalance)}$txCountParam"
        return getOffset("token_holders_details?$urlParam", limit, offset, skipErrors = errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.holderCorrelations */
    @NotNull
    fun holderCorrelations(
        contracts: List<String>
    ): List<TokenCorrelation> {
        val params = "token_correlation?${tokenAsParamRequired(contracts)}"
        return if (contracts.size < 2) emptyList() else get(params, errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.holderSimilar */
    @NotNull
    fun holderSimilar(
        contracts: String
    ): List<HolderSimilar> {
        return get("similar_tokens?token=${checkAddressRequired(contracts)}", errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.tokenByNameOrSymbol */
    @NotNull
    @JvmOverloads
    fun tokenByNameOrSymbol(
        nameOrSymbol: String,
        limit: Int = 100
    ): List<Token> {
        return get("token_search?search=$nameOrSymbol&limit=${toLimit(limit)}", errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.tokenDetails */
    @NotNull
    fun tokenDetails(
        contracts: List<String>
    ): List<TokenDetails> {
        return get("token_info?${tokenAsParamRequired(contracts)}", errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.tokenStatistic */
    @NotNull
    fun tokenStatistic(
        contract: String
    ): List<TokenStatistic> {
        return get("token_stat?token=${checkAddressRequired(contract)}", errors)
    }

    /** @see io.api.bloxy.core.ITokenApi.tokenTransfers */
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
        val params = "token=${checkAddressRequired(contract)}$dateParams"
        return getOffset("transfers?$params", limit, offset, skipErrors = errors)
    }
}