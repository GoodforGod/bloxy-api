package io.api.bloxy.model.dto.address

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class AddrDetails(
    val address: String = "",
    val level: String = "",
    val note: String = "",
    val annotation: String = ""
) : IModel{
    override fun isEmpty(): Boolean {
        return address.isEmpty()
    }
}