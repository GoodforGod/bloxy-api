package io.api.bloxy.model.dto.livepeer

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
data class Round(
    val block: Long = -1L,
    val round: Long = 0L,
    @Json(name = "tx_time") val txTime: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "currentMintableTokens") val currentMintableTokens: Double = .0,
    @Json(name = "currentInflation") val currentInflation: Long = 0L
) : IModel {
    override fun isEmpty(): Boolean = txHash.isEmpty() && block == -1L && round == 0L
}