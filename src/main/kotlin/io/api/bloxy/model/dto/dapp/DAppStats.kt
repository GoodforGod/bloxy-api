package io.api.bloxy.model.dto.dapp

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel

/**
 * DApp Statistic
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
data class DAppStats(
    @Json(name = "smart_contract_address") val contractAddress: String = "",
    @Json(name = "contract_type") val contractType: String = "",
    val annotation: String = "",
    val callers: Long = 0L,
    val calls: Long = 0L,
    val senders: Long = 0L,
    val transfers: Long = 0L,
    val amount: Double = .0
) : IModel {
    override fun isEmpty() = contractAddress.isEmpty() && contractType.isEmpty()
            && amount == .0 && transfers == 0L
}