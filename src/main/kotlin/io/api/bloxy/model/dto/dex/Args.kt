package io.api.bloxy.model.dto.dex

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.11.2018
 */
data class Args(val map: Map<String, Any?> = emptyMap()) : IModel {
    override fun isEmpty(): Boolean {
        return map.isEmpty()
    }

    override fun toString(): String {
        return "Args(map=$map)"
    }
}