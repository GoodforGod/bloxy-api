package io.api.bloxy.core

import io.api.bloxy.model.dto.address.AddrCorrelation
import io.api.bloxy.model.dto.address.AddrDetails
import io.api.bloxy.model.dto.address.AddrStatistic
import io.api.bloxy.model.dto.address.Balance


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IAddressApi {

    fun details(
        addresses: List<String>
    ): List<AddrDetails>

    fun statistics(
        addresses: List<String>
    ): List<AddrStatistic>

    fun correlated(
        address: String
    ): List<AddrCorrelation>

    fun balance(
        address: String
    ): Balance
}