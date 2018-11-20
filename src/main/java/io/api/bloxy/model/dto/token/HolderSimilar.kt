package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class HolderSimilar(
    val address: String = "",
    val symbol: String = "",
    val common_holders: Long = 0,
    val percentage: Double = .0
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && symbol.isEmpty() && common_holders == 0L
    }
}