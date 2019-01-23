package io.api.bloxy.core

import io.api.bloxy.model.dto.address.AddrCorrelation
import io.api.bloxy.model.dto.address.AddrDetails
import io.api.bloxy.model.dto.address.AddrStatistic
import io.api.bloxy.model.dto.address.Balance
import org.jetbrains.annotations.NotNull


/**
 * API for Analysis of addresses, their activities and statistics
 * More information - https://bloxy.info/api_methods#address
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
internal interface IAddressApi {

    /**
     * Basic check that address is suitable for token transfer distribution.
     * @param addresses to check
     */
    @NotNull
    fun details(
        addresses: List<String>
    ): List<AddrDetails>

    /**
     * Counts and aggregates basic statistic on addresses
     * @param addresses to check
     */
    @NotNull
    fun statistics(
        addresses: List<String>
    ): List<AddrStatistic>

    /**
     * Lists the tokens that owns holders, that also owns the same set of tokens
     * @param address to check
     */
    @NotNull
    fun correlated(
        address: String
    ): List<AddrCorrelation>

    /**
     * Lists the balance of all currency/tokens for this address
     * @param address to check
     */
    @NotNull
    fun balance(
        address: String
    ): Balance

    /**
     * Lists the words used in address annotations with counters
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @return map with word as KEY, number of annotated addresses as VALUE
     */
    @NotNull
    fun annotationStatistic(
            limit: Int = 1000,
            offset: Int = 0
    ) : Map<String, Int>

    /**
     * Lists the words used in address annotations per address
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     * @return map with word as KEY, list of addresses as VALUE
     */
    @NotNull
    fun annotations(
            words: List<String>,
            limit: Int = 1000,
            offset: Int = 0
    ) : Map<String, List<String>>
}