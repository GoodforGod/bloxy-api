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
    val protocol: String = "",
    val signature: String = "",
    @KlaxonArgs val arguments: Args = Args(),
    @Json(name = "method") val methodAsString: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_sender") val txSender: String = "",
    @Json(name = "smart_contract_address") val smartContractAddress: String = ""
) : IModel {

    @Json(ignored = true)
    val method = MethodType.parse(methodAsString)

    override fun isEmpty(): Boolean {
        return smartContractAddress.isEmpty() && protocol.isEmpty() && txHash.isEmpty()
    }
}