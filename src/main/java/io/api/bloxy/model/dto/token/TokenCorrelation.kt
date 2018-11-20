package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class TokenCorrelation(
    val symbol: String = "",
    val address: String = "",
    val transfers: Long = 0
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && address.isEmpty() && transfers == 0L
    }
}