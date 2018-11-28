package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.KlaxonArgs


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexTxPending(
    val smart_contract_address: String = "",
    val protocol: String = "",
    @Json(name = "method")
    val methodAsString: String = "",
    val signature: String = "",
    @KlaxonArgs val arguments: Args = Args(),
    val tx_sender: String = "",
    val tx_hash: String = ""
) : IModel {

    val method = MethodType.parse(methodAsString)

    override fun isEmpty(): Boolean {
        return smart_contract_address.isEmpty() && protocol.isEmpty() && tx_hash.isEmpty()
    }
}