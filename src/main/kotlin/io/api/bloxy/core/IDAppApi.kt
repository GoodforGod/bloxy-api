package io.api.bloxy.core

import io.api.bloxy.model.dto.dapp.DAppStats
import io.api.bloxy.model.dto.dapp.DAppUser
import io.api.bloxy.model.dto.dapp.MultiSource
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 * API for Analysis of addresses, their activities and statistics
 * More information - https://bloxy.info/api_methods#dapp
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
internal interface IDAppApi {

    /**
     * Lists smart contracts with users and volume statistics
     * @param since timestamp (default 30 days ago)
     * @param till timestamp (default now)
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun statistics(
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<DAppStats>

    /**
     * Lists smart contract addresses, which called or transfered money to smart contract
     * @param contract to look for
     * @param multiSource Common source address used for filtering
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun users(
        contract: String,
        multiSource: String = "",
        limit: Int = 10000,
        offset: Int = 0
    ): List<DAppUser>

    /**
     * Lists addresses, which created more than one address, which send money or called smart contract
     * @param contract to look for
     * @param limit max result (MAX 110000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun sources(
        contract: String,
        limit: Int = 10000,
        offset: Int = 0
    ): List<MultiSource>
}