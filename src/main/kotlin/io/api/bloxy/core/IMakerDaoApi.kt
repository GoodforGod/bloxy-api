package io.api.bloxy.core

import io.api.bloxy.model.dto.makerdao.Poke
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 * API for Analysis of addresses, their activities and statistics
 * More information - https://bloxy.info/api_methods#daomaker
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
internal interface IMakerDaoApi {

    /**
     * Lists smart contracts with users and volume statistics
     * @param since timestamp (default 5 days ago)
     * @param till timestamp (default now)
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun poke(
        contract: String,
        limit: Int = 30,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<Poke>
}