package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.dydx.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


/**
 * API for dYdX protocol analytics
 * More information - https://bloxy.info/api_methods#dydx
 *
 * @see io.api.bloxy.core.IdYdXProtocolApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 28.02.2019
 */
class DYdXProtocolApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "dydx", key) {

    private fun contractAsParam(addr: String): String  = if (addr.isEmpty()) "" else "&smart_contract=${checkAddrRequired(addr)}"

    private fun positionAsParam(pos: List<String>): String = asParam(pos, "&positions[]=", "&positions[]=")

    private fun tradersAsParam(pos: List<String>): String = asParam(checkAddr(pos), "&traders[]=", "&traders[]=")

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.positions
     */
    @NotNull
    @JvmOverloads
    fun positions(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<Position> {
        val datesParam = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "positions?${contractAsParam(contract)}${positionAsParam(positionsIdsOrTokens)}$datesParam"
        return getOffset(params, limit, offset, 10000)
    }

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.positionsLS
     */
    @NotNull
    @JvmOverloads
    fun positionsLS(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<PositionLS> {
        val datesParam = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}${tradersAsParam(traders)}"
        val params = "trades?${contractAsParam(contract)}${positionAsParam(positionsIdsOrTokens)}$datesParam"
        return getOffset(params, limit, offset, 10000)
    }

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.positionsDaily
     */
    @NotNull
    @JvmOverloads
    fun positionsDaily(
        positionsIdsOrTokens: String,
        contract: String = "",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<PositionDaily> {
        val datesParam = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "daily?position=$positionsIdsOrTokens${contractAsParam(contract)}$datesParam"
        return getOffset(params, limit, offset, 10000)
    }

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.positionToken
     */
    @NotNull
    @JvmOverloads
    fun positionToken(
        positionsTokens: String,
        contract: String = ""
    ): List<PositionToken> {
        val params = "marketcap?token=$positionsTokens${contractAsParam(contract)}"
        return get(params)
    }

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.tradersStats
     */
    @NotNull
    @JvmOverloads
    fun tradersStats(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList()
    ): List<TraderStats> {
        val params = "traders?${contractAsParam(contract)}${tradersAsParam(traders)}${positionAsParam(positionsIdsOrTokens)}"
        return get(params)
    }

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.positionStats
     */
    @NotNull
    @JvmOverloads
    fun positionStats(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList(),
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<PositionStats> {
        val datesParam = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}${tradersAsParam(traders)}"
        val params = "stat?${contractAsParam(contract)}${positionAsParam(positionsIdsOrTokens)}$datesParam"
        return get(params)
    }

    /**
     * @see io.api.bloxy.core.IdYdXProtocolApi.withdrawals
     */
    @NotNull
    @JvmOverloads
    fun withdrawals(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0
    ): List<MarginCall> {
        val params = "withdrawals?${contractAsParam(contract)}${positionAsParam(positionsIdsOrTokens)}${tradersAsParam(traders)}"
        return getOffset(params, limit, offset, 10000)
    }
}