package io.api.bloxy.core

import io.api.bloxy.model.dto.address.AddrCorrelation
import io.api.bloxy.model.dto.address.AddrDetails
import io.api.bloxy.model.dto.address.AddrStatistic
import io.api.bloxy.model.dto.address.Balance
import org.jetbrains.annotations.NotNull


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IAddressApi {

    @NotNull
    fun details(
        addresses: List<String>
    ): List<AddrDetails>

    @NotNull
    fun statistics(
        addresses: List<String>
    ): List<AddrStatistic>

    @NotNull
    fun correlated(
        address: String
    ): List<AddrCorrelation>

    @NotNull
    fun balance(
        address: String
    ): Balance
}