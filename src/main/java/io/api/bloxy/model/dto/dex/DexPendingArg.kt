package io.api.bloxy.model.dto.dex

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexPendingArg(
    val tokenGet: String = "",
    val amountGet: Number = .0,
    val tokenGive: String = "",
    val amountGive: Number = .0,
    val expires: Number = .0,
    val nonce: Number = .0,
    val user: String = "",
    val v: Number = .0,
    val r: String = "",
    val s: String = "",
    val amount: Number = .0
) : IModel {
    override fun isEmpty(): Boolean {
        return tokenGet.isEmpty() && tokenGive.isEmpty() && v == .0 && amount == .0
    }
}