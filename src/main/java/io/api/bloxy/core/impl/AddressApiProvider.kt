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
        return get("address_stat?" + addressAsParam(addresses), AddrDetails::class.java)
    }

    override fun statistics(addresses: List<String>): List<AddrStatistic> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun correlated(addresses: List<String>): List<AddrCorrelation> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun balance(address: String): Balance {
        TODO("not implemented yet") //File | Settings | File Templates
    }
}