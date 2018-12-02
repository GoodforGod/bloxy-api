package io.api.bloxy.model.dto.contract

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
data class ContractDetail(
    val name: String = "",
    val signature: String = "",
    val smart_contract: String = "",
    val annotation: String = "",
    val count: Long = 0L
) : IModel {
    override fun isEmpty(): Boolean {
        return smart_contract.isEmpty() && name.isEmpty() && signature.isEmpty()
    }
}