package io.api.bloxy.model.dto.contract

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
data class ContractDetail(
    val name: String = "",
    val signature: String = "",
    @Json(name = "smart_contract") val smartContract: String = "",
    val annotation: String = "",
    val count: Long = 0L
) : IModel {
    override fun isEmpty(): Boolean {
        return smartContract.isEmpty() && name.isEmpty() && signature.isEmpty()
    }
}