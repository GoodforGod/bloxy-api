package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
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
class AddressApiProvider(client: IHttpClient, key: String) : BasicProvider(client, "address", key) {

    @NotNull
    fun details(
        addresses: List<String>
    ): List<AddrDetails> {
        return validOnly(get("address_diagnostics?${addressAsParamRequired(addresses)}"))
    }

    @NotNull
    fun statistics(
        addresses: List<String>
    ): List<AddrStatistic> {
        return validOnly(get("address_stat?${addressAsParamRequired(addresses)}"))
    }

    @NotNull
    fun correlated(
        address: String
    ): List<AddrCorrelation> {
        return get("correlated_address_tokens?address=${checkAddressRequired(address)}")
    }

    @NotNull
    fun balance(
        address: String
    ): Balance {
        return Balance(get("balance?address=${checkAddressRequired(address)}"))
    }
}