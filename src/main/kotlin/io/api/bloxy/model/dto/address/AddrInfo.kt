package io.api.bloxy.model.dto.address

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class AddrInfo(
    val address: String = "",
    val balance: BigDecimal = BigDecimal.ZERO,
    @Json(name = "transfers_from_count") val transfersFromCount: Long = 0L,
    @Json(name = "calls_from_count") val callsFromCount: Long = 0L,
    @Json(name = "address_annotation") val addressAnnotation: String = "",
    @Json(name = "smart_contract_type") val addrTypeAsString: String = ""
) : IModel, IAddressModel {

    override val addrType: AddressType = AddressType.parse(addrTypeAsString)

    override fun isEmpty(): Boolean = address.isEmpty() && BigDecimal.ZERO == balance
            && transfersFromCount == 0L && callsFromCount == 0L
}