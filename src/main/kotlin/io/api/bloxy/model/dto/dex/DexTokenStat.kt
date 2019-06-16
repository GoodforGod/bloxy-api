package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class DexTokenStat(
    @Json(name = "smart_contract")
    val smartContract: String = "",
    val protocol: String = "",
    val token: String = "",
    val symbol: String = "",
    val volume: BigDecimal = BigDecimal.ZERO,
    val trades: Long = 0
) : IModel{

    override fun isEmpty(): Boolean = smartContract.isEmpty() && token.isEmpty() && trades == 0L
}