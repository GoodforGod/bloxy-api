package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class TokenCorrelation(
    val symbols: String = "",
    val addresses: String = "",
    val transfer_to_uniques: Long = 0
) : IModel {
    override fun isEmpty(): Boolean {
        return addresses.isEmpty() && transfer_to_uniques == 0L
    }
}