package io.api.bloxy.model.dto.dex

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexProtocol(
    val protocol: String = "",
    val smart_contracts: Int = 0
) : IModel {
    override fun isEmpty(): Boolean {
        return smart_contracts == 0 && protocol.isEmpty()
    }
}