package io.api.bloxy.model.dto.token

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class Holder(
    val address: String = "",
    val address_type: String = "",
    val annotation: String = "",
    val balance: Double = .0
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty() && balance == .0
    }
}