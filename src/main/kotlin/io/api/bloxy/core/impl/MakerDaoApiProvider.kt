package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.makerdao.Poke
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
class MakerDaoApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "daomaker", key){

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
    ) : List<Poke> {

    }
}