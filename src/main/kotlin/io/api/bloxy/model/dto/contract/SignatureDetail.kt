package io.api.bloxy.model.dto.contract

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
data class SignatureDetail(
    val name: String = "",
    val signature: String = "",
    val signature_hash: String = "",
    val type: String = "",
    val abi: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return signature.isEmpty() && signature_hash.isEmpty() && name.isEmpty()
    }
}