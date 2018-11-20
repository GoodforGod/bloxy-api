package io.api.bloxy.model.dto

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class Address(
    val address: String = "",
    val address_type: String = "",
    val amount: Double = .0,
    val annotation: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return address.isEmpty()
    }
}