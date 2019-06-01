package io.api.bloxy.model.dto.token

import com.beust.klaxon.Json
import io.api.bloxy.model.IModel
import java.math.BigDecimal


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.06.2019
 */
data class TokenGraph(
    val amount: BigDecimal = BigDecimal.ZERO,
    @Json(name = "group_from_hash") val groupFromHash: String = "",
    @Json(name = "group_from_size") val groupFromSize: Int = 0,
    @Json(name = "group_to_hash") val groupToHash: String = "",
    @Json(name = "group_to_size") val groupToSize: Int = 0,
    @Json(name = "tx_count") val txCount: Long = 0,
    @Json(name = "from_group_addresses") val fromGroupAddressesAsString: String = "",
    @Json(name = "to_group_addresses") val toGroupAddressesAsString: String = "",
    @Json(name = "from_group_addresses_annotations") val fromGroupAddressesAnnotations: String = "",
    @Json(name = "to_group_addresses_annotations") val toGroupAddressesAnnotations: String= "",
    @Json(name = "from_group_types") val fromGroupTypesAsString: String = "",
    @Json(name = "to_group_types") val toGroupTypesAsString: String = ""
) : IModel {

    /**
     * Address -> Type
     */
    val fromGroups: Map<String, String> = parseGroups(fromGroupAddressesAnnotations, fromGroupTypesAsString)
    val toGroups: Map<String, String> = parseGroups(toGroupAddressesAsString, toGroupTypesAsString)

    private fun parseGroups(groupAddress: String, groupTypes: String) : Map<String, String> {
        val addresses = fromGroupAddressesAnnotations.split(';')
        val types = groupTypes.split(';')

        val result: MutableMap<String, String> = mutableMapOf()
        for(i in 1..addresses.size)
            result[addresses[i]] = if(types.size <= i) types[i] else ""
        return result
    }

    override fun isEmpty(): Boolean = amount == BigDecimal.ZERO && txCount == 0L && groupFromHash.isEmpty()

    override fun toString(): String {
        return "TokenGraph(amount=$amount, groupFromHash='$groupFromHash', groupFromSize=$groupFromSize, " +
                "groupToHash='$groupToHash', groupToSize=$groupToSize, txCount=$txCount, " +
                "fromGroupAddressesAsString='$fromGroupAddressesAsString', " +
                "toGroupAddressesAsString='$toGroupAddressesAsString', " +
                "fromGroupAddressesAnnotations='$fromGroupAddressesAnnotations', " +
                "toGroupAddressesAnnotations='$toGroupAddressesAnnotations', " +
                "fromGroupTypesAsString='$fromGroupTypesAsString', " +
                "toGroupTypesAsString='$toGroupTypesAsString', fromGroups=$fromGroups, toGroups=$toGroups)"
    }
}