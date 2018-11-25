package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenDetails(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    val type: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && name.isEmpty() && symbol.isEmpty() && decimals == 0
    }
}