package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.address.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDate


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
        return get("correlated_address_tokens?address=${checkAddrRequired(address)}")
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.balance
     */
    @NotNull
    fun balance(
        address: String
    ): Balance {
        return Balance(get("balance?address=${checkAddrRequired(address)}"))
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.annotationStatistic
     */
    @NotNull
    @JvmOverloads
    fun annotationStatistic(
        limit: Int = 1000,
        offset: Int = 0
    ): Map<String, Int> {
        val wordsList: List<WordCounter> = getOffset("annotation_words?", limit, offset)
        return wordsList.map { it.word to it.count }.toMap()
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.annotations
     */
    @NotNull
    @JvmOverloads
    fun annotations(
        words: List<String>,
        limit: Int = 1000,
        offset: Int = 0
    ): Map<String, List<String>> {
        val params = "annotated_addresses?${asParam(checkNonBlank(words), "word[]=", "&word[]=")}"
        val wordsList: List<WordCounter> = getOffset(params, limit, offset)
        return wordsList.groupBy({ it.word }, { it.address })
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.all
     */
    @NotNull
    fun all(
        limit: Int = 1000,
        offset: Int = 0
    ) : List<AddrInfo> {
        return getOffset("list_all?", limit, offset, 1000)
    }

    /**
     * @see io.api.bloxy.core.IAddressApi.daily
     */
    @NotNull
    fun daily(
        address: String,
        currency: Currency = Currency.USD,
        worthless: Boolean = false,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<AddrDaily> {
        val dateParams = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val additionalParam = "&valueless=$worthless&price_currency=${currency.name}"
        val params = "daily?${addressAsParamRequired(address)}$dateParams$additionalParam"
        return get(params)
    }
}