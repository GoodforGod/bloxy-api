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
interface IAddressApi {

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
}