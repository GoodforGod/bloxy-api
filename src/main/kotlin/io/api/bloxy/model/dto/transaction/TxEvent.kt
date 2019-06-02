package io.api.bloxy.model.dto.transaction

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.06.2019
 */
data class TxEvent(
    val block: Long = 0,
    val event: String = "",
    val signature: String = "",
    @Json(name = "tx_hash") val txHash: String = "",
    @Json(name = "tx_time") val txTimeAsString: String = "",
    @Json(name = "smart_contract_address") val smartContractAddress: String = "",
    @Json(name = "smart_contract_type") val smartContractType: String = "",
    @Json(name = "smart_contract_annotation") val smartContractAnnotation: String = "",
    @Json(name = "tx_from") val txFrom: String = "",
    @Json(name = "tx_from_annotation") val txFromAnnotation: String = ""
) : IModel, IAddressModel {

    @Json(ignored = true) val txTime = txTimeAsString.asDateTime()

    override val addrType: AddressType = AddressType.parse(smartContractType)

    override fun isEmpty(): Boolean = block == 0L && event.isEmpty() && txHash.isEmpty() && txFrom.isEmpty()
}