package io.api.bloxy.core.impl

import io.api.bloxy.core.ITokenApi
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.token.*
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @see ITokenApi
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal class TokenApiProvider(client: IHttpClient, key: String) : ITokenApi, BasicProvider(client, "token", key) {

    companion object {
        val errors = listOf(
            "No currency found by token",
            "Currency not found by"
        )
    }

    override fun holders(contract: String, limit: Int): List<Holder> {
        return get("token_holders_list?token=${checkAddressRequired(contract)}&limit=${toLimit(limit)}", errors)
    }

    override fun holderDetails(
        contract: String,
        limit: Int,
        offset: Int,
        minBalance: Double,
        minReceived: Int,
        minSend: Int
    ): List<HolderDetails> {
        val txCountParam = "&to_count_min=${toNoZero(minReceived)}&from_count_min=${toZero(minSend)}"
        val urlParam = "token=${checkAddressRequired(contract)}&min_balance=${toNoZero(minBalance)}$txCountParam"
        return getOffset("token_holders_details?$urlParam", limit, offset, skipErrors = errors)
    }

    override fun holderCorrelations(contracts: List<String>): List<TokenCorrelation> {
        val params = "token_correlation?${tokenAsParamRequired(contracts)}"
        return if (contracts.size < 2) emptyList() else get(params, errors)
    }

    override fun holderSimilar(contracts: String): List<HolderSimilar> {
        return get("similar_tokens?token=${checkAddressRequired(contracts)}", errors)
    }

    override fun tokenByNameOrSymbol(nameOrSymbol: String, limit: Int, offset: Int): List<Token> {
        return get("token_search?search=$nameOrSymbol&limit=${toLimit(limit)}", errors)
    }

    override fun tokenDetails(contracts: List<String>): List<TokenDetails> {
        return get("token_info?${tokenAsParamRequired(contracts)}", errors)
    }

    override fun tokenStatistic(contract: String): List<TokenStatistic> {
        return get("token_stat?token=${checkAddressRequired(contract)}", errors)
    }

    override fun tokenTransfers(
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<TokenTransfer> {
        val dateParams = "${dateAsParam("from_time", since)}${dateAsParam("till_time", till)}"
        val params = "token=${checkAddressRequired(contract)}$dateParams"
        return getOffset("transfers?$params", limit, offset, skipErrors = errors)
    }
}