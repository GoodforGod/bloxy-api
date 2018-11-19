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

    override fun holders(contract: String, limit: Int, offset:Int): List<Holder> {
        return getOffset("token_holders_list?token=$contract", limit, offset)
    }

    override fun holderDetails(
        contract: String,
        limit: Int,
        offset: Int,
        minBalance: Double,
        minReceived: Int,
        minSend: Int
    ): List<HolderDetails> {
        val urlParam = "token=$contract&min_balance=$minBalance&to_count_min=$minReceived&from_count_min=$minSend"
        return getOffset("token_holders_details?$urlParam", limit, offset)
    }

    override fun holderCorrelations(contracts: List<String>): List<TokenCorrelation> {
        return parse(get("token_correlation?${tokenAsParam(contracts)}"))
    }

    override fun holderSimilar(contracts: String): List<HolderSimilar> {
        return parse(get("similar_tokens?token=$contracts"))
    }

    override fun tokenByNameOrSymbol(nameOrSymbol: String, limit: Int, offset: Int): List<Token> {
        return parse(get("token_search?search=$nameOrSymbol&limit=${toLimit(limit, 100000)}"))
    }

    override fun tokenDetails(contracts: List<String>): List<TokenDetails> {
        return parse(get("token_info?${tokenAsParam(contracts)}"))
    }

    override fun tokenStatistic(contract: String): List<TokenStatistic> {
        return parse(get("token_stat?token=$contract"))
    }

    override fun tokenTransfers(
        contract: String,
        limit: Int,
        offset: Int,
        since: LocalDateTime,
        till: LocalDateTime
    ): List<TokenTransfer> {
        val params = "token=$contract&from_time=$since&till_time=$till"
        return getOffset("transfers?$params", limit, offset)
    }
}