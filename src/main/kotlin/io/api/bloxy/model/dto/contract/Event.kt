package io.api.bloxy.model.dto.contract

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
data class Event(
    val name: String = "",
    val signature: String = "",
    val signature_hash: String = "",
    val count: Long = 0L,
    val first_tx_at: String = "",
    val last_tx_at: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return name.isEmpty() && signature.isEmpty() && signature_hash.isEmpty()
    }
}