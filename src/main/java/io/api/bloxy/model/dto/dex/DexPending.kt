package io.api.bloxy.model.dto.dex

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexPending(
    val smart_contract_address: String = "",
    val protocol: String = "",
    val method: String = "",
    val signature: String = "",
    val arguments: DexPendingArg = DexPendingArg(),
    val tx_sender: String = "",
    val tx_hash: String = ""
) : IModel{
    override fun isEmpty(): Boolean {
        return smart_contract_address.isEmpty() && protocol.isEmpty()
    }
}