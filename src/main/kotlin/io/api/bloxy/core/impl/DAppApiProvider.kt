package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.dapp.DAppStats
import io.api.bloxy.model.dto.dapp.DAppUser
import io.api.bloxy.model.dto.dapp.MultiSource
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 * Smart Contracts with high volume and users ( DApps)
 * More information - https://bloxy.info/api_methods#dapp
 *
 * @see io.api.bloxy.core.IDAppApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
class DAppApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "dapp", key) {

    /**
     * @see io.api.bloxy.core.IDAppApi.statistics
     */
    @NotNull
    @JvmOverloads
    fun statistics(
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<DAppStats> {
        val params = "stat?${asDate("from_date", since)}${asDate("till_date", till)}"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.IDAppApi.users
     */
    @NotNull
    @JvmOverloads
    fun users(
        contract: String,
        multiSource: String = "",
        limit: Int = 10000,
        offset: Int = 0
    ) : List<DAppUser> {
        val sourceParam = if (multiSource.isEmpty()) "" else "&multi_source=${checkAddrRequired(multiSource)}"
        val params = "users?smart_contract_address=${checkAddrRequired(contract)}$sourceParam"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.IDAppApi.sources
     */
    @NotNull
    @JvmOverloads
    fun sources(
        contract: String,
        limit: Int = 10000,
        offset: Int = 0
    ) : List<MultiSource> {
        val params = "multi?smart_contract_address=${checkAddrRequired(contract)}"
        return getOffset(params, limit, offset)
    }
}