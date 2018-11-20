package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class Token(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    val type: String = "",
    val created: String = "",
    val transactions: Long = 0,
    val latest_tx: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && name.isEmpty() && symbol.isEmpty() && type.isEmpty() && transactions == 0L
    }
}