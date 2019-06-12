package io.api.bloxy.core

import io.api.bloxy.model.dto.dydx.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


/**
 * API for dYdX protocol analytics
 * More information - https://bloxy.info/api_methods#dydx
 *
 * @author GoodforGod
 * @since 27.02.2019
 */
internal interface IdYdXProtocolApi {

    /**
     * Lists positions of dYdX Margin trading with states and statistics
     * @param contract dex to filter
     * @param positionsIdsOrTokens filter by position id(s) or by position token address(es).
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun positions(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<Position>

    /**
     * Open/increase/close position transactions of dYdX Margin Tranding
     * @param contract dex to filter
     * @param positionsIdsOrTokens filter by position id(s) or by position token address(es).
     * @param traders to filter on
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun positionsLS(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<PositionLS>

    /**
     * Daily position aggregates for dYdX Margin Tranding
     * @param contract dex to filter
     * @param positionsIdsOrTokens filter by position id(s) or by position token address(es).
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun positionsDaily(
        positionsIdsOrTokens: String,
        contract: String = "",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<PositionDaily>

    /**
     * Tokenized position price/supply/holders/marketcap hourly. Source for the hourly Ether and DAI price:
     * https://cryptorank.io
     * @param contract dex to filter
     * @param positionsTokens filter by position token address(es)
     */
    @NotNull
    fun positionToken(
        positionsTokens: String,
        contract: String = ""
    ): List<PositionToken>


    /**
     * Profit/loss/volume statistics by traders for optionally selected positions. Note that it may show incorrect PL figures in case of Margin Call
     * @param contract dex to filter
     * @param positionsIdsOrTokens filter by position id(s) or by position token address(es).
     * @param traders to filter on
     */
    @NotNull
    fun tradersStats(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList()
    ): List<TraderStats>


    /**
     * Profit/loss/volume statistics by positions. Note that it may show incorrect PL figures in case of Margin Call
     * @param contract dex to filter
     * @param positionsIdsOrTokens filter by position id(s) or by position token address(es).
     * @param traders to filter on
     * @param since timestamp
     * @param till timestamp
     */
    @NotNull
    fun positionStats(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList(),
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<PositionStats>

    /**
     * withdrawals events due to margin call
     * @param contract dex to filter
     * @param positionsIdsOrTokens filter by position id(s) or by position token address(es).
     * @param traders to filter on
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun withdrawals(
        contract: String = "",
        positionsIdsOrTokens: List<String> = emptyList(),
        traders: List<String> = emptyList(),
        limit: Int = 100,
        offset: Int = 0
    ): List<MarginCall>
}