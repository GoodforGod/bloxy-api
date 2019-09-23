package io.api.bloxy.model.dto.dex

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import io.api.bloxy.util.KlaxonArgs
import io.api.bloxy.util.KlaxonStr

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
class DexTxPending(
    @KlaxonStr val protocol: String = "",
    @KlaxonStr val signature: String = "",
    @KlaxonArgs val arguments: Args = Args(),
    @KlaxonStr @Json(name = "method") val methodAsString: String = "",
    @KlaxonStr @Json(name = "tx_hash") val txHash: String = "",
    @KlaxonStr @Json(name = "tx_sender") val txSender: String = "",
    @KlaxonStr @Json(name = "smart_contract_address") val smartContractAddress: String = ""
) : IModel {

    @Json(ignored = true)
    val method = MethodType.parse(methodAsString)

    override fun isEmpty(): Boolean {
        return smartContractAddress.isEmpty() && protocol.isEmpty() && txHash.isEmpty()
    }

    override fun toString(): String {
        return "DexTxPending(protocol='$protocol', signature='$signature', arguments=$arguments, " +
                "methodAsString='$methodAsString', txHash='$txHash', txSender='$txSender', " +
                "smartContractAddress='$smartContractAddress', method=$method)"
    }
}