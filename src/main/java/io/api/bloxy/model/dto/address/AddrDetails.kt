package io.api.bloxy.model.dto.address

import io.api.bloxy.model.IValidModel


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
) : IValidModel {

    override fun isEmpty(): Boolean = address.isEmpty()

    override fun isValid(): Boolean = "Address was never used" != note
}