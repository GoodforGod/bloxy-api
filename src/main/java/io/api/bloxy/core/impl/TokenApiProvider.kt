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

    private inline fun <reified T> cycleWithLimit(urlParams: String, limit: Int): List<T> {
        val result: MutableList<T> = ArrayList()
        var temp: List<T>
        var cycleLimit = limit
        var offset = 0
        do {
            temp = parse(get("$urlParams&limit=$cycleLimit&offset=$offset"))
            result.addAll(temp)
            cycleLimit -= 10000
            offset += 10000
        } while (cycleLimit > 0 && temp.isNotEmpty())

        return result
    }

    override fun holders(address: String, limit: Int): List<Holder> {
        return if (limit < 1) emptyList() else cycleWithLimit("token_holders_list?token=$address", limit)
    }

    override fun holderDetails(
        address: String,
        limit: Int,
        minBalance: Double,
        minReceived: Int,
        minSend: Int
    ): List<HolderDetails> {
        val urlParam = "token=$address&min_balance=$minBalance&to_count_min=$minReceived&from_count_min=$minSend"
        return if (limit < 1) emptyList() else cycleWithLimit("token_holders_details?$urlParam", limit)
    }

    override fun holderCorrelations(addresses: List<String>): List<TokenCorrelation> {
        return parse(get("token_correlation?" + tokenAsParam(addresses)))
    }

    override fun holderSimilar(address: String): List<HolderSimilar> {
        return parse(get("similar_tokens?token=$address"))
    }

    override fun tokenByNameOrSymbol(nameOrSymbol: String, limit: Int): List<Token> {
        val formatLimit = if (limit > 100000) 100000 else limit
        return if (formatLimit < 1) emptyList() else parse(get("token_search?search=$nameOrSymbol&limit=$formatLimit"))
    }

    override fun tokenDetails(addresses: List<String>): List<TokenDetails> {
        return parse(get("token_info?" + tokenAsParam(addresses)))
    }

    override fun tokenStatistic(address: String): List<TokenStatistic> {
        return parse(get("token_stat?token=$address"))
    }

    override fun tokenTransfers(
        address: String,
        limit: Int,
        fromTime: LocalDateTime,
        tillTime: LocalDateTime
    ): List<TokenTransfer> {
        return cycleWithLimit("transfers?token=$address&from_time=$fromTime&till_time=$tillTime", limit)
    }
}