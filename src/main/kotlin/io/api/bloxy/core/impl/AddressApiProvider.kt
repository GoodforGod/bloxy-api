package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.address.AddrCorrelation
import io.api.bloxy.model.dto.address.AddrDetails
import io.api.bloxy.model.dto.address.AddrStatistic
import io.api.bloxy.model.dto.address.Balance
import org.jetbrains.annotations.NotNull


/**
 * API for Analysis of addresses, their activities and statistics
 * More information - https://bloxy.info/api_methods#address
 *
 * @see io.api.bloxy.core.IAddressApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class AddressApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "address", key) {

    /**
     * @see io.api.bloxy.core.IAddressApi.details
     */
    @NotNull
    fun details(
        addresses: List<String>
    ): List<AddrDetails> {
        return validOnly(get("address_diagnostics?${addressAsParamRequired(addresses)}"))
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.statistics
     */
    @NotNull
    fun statistics(
        addresses: List<String>
    ): List<AddrStatistic> {
        return validOnly(get("address_stat?${addressAsParamRequired(addresses)}"))
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.correlated
     */
    @NotNull
    fun correlated(
        address: String
    ): List<AddrCorrelation> {
        return get("correlated_address_tokens?address=${checkAddressRequired(address)}")
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.balance
     */
    @NotNull
    fun balance(
        address: String
    ): Balance {
        return Balance(get("balance?address=${checkAddressRequired(address)}"))
    }
}