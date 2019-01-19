package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.makerdao.Poke
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 * API for Analysis of addresses, their activities and statistics
 * More information - https://bloxy.info/api_methods#daomaker
 *
 * @see io.api.bloxy.core.IMakerDaoApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
class MakerDaoApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "daomaker", key){

    /**
     * @see io.api.bloxy.core.IDAppApi.statistics
     */
    @NotNull
    fun poke(
        contract: String,
        limit: Int = 30,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<Poke> {
        val datesParam = "${dateAsParam("from_date", since)}${dateAsParam("till_date", till)}"
        val params = "poke?smart_contract=${checkAddressRequired(contract)}$datesParam"
        return getOffset(params, limit, offset)
    }
}