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
        return if(addresses.isNullOrEmpty()) emptyList() else parse(get("address_diagnostics?${addressAsParam(addresses)}"))
    }

    override fun statistics(addresses: List<String>): List<AddrStatistic> {
        return if(addresses.isNullOrEmpty()) emptyList() else parse(get("address_stat?${addressAsParam(addresses)}"))
    }

    override fun correlated(address: String): List<AddrCorrelation> {
        return if(address.isNullOrEmpty()) emptyList() else parse(get("correlated_address_tokens?address=$address"))
    }

    override fun balance(address: String): Balance {
        return if(address.isNullOrEmpty()) Balance.empty else Balance(parse(get("balance?address=$address")))
    }
}