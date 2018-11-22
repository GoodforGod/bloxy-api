package io.api.bloxy.core.impl

import io.api.bloxy.core.IAddressApi
import io.api.bloxy.executor.IHttpClient
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
class AddressApiProvider(client: IHttpClient, key: String) : IAddressApi, BasicProvider(client, "address", key) {

    override fun details(addresses: List<String>): List<AddrDetails> {
        return validOnly(get("address_diagnostics?${addressAsParamRequired(addresses)}"))
    }

    override fun statistics(addresses: List<String>): List<AddrStatistic> {
        return validOnly(get("address_stat?${addressAsParamRequired(addresses)}"))
    }

    override fun correlated(address: String): List<AddrCorrelation> {
        return get("correlated_address_tokens?address=${checkAddressRequired(address)}")
    }

    override fun balance(address: String): Balance {
        return Balance(get("balance?address=${checkAddressRequired(address)}"))
    }
}