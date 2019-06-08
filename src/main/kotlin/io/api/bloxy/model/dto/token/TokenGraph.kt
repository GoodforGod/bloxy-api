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
    @Json(name = "from_group_addresses_annotations") val fromGroupAddressesAnnotationsAsString: String = "",
    @Json(name = "to_group_addresses_annotations") val toGroupAddressesAnnotationsAsString: String = "",
    @Json(name = "from_group_types") val fromGroupTypesAsString: String = "",
    @Json(name = "to_group_types") val toGroupTypesAsString: String = ""
) : IModel {

    /**
     * Address -> Type, Annotation
     */
    val fromGroups: Map<String, Pair<String, String>> =
        parseGroups(fromGroupAddressesAsString, fromGroupTypesAsString, fromGroupAddressesAnnotationsAsString)
    val toGroups: Map<String, Pair<String, String>> =
        parseGroups(toGroupAddressesAsString, toGroupTypesAsString, toGroupAddressesAnnotationsAsString)

    fun getToAddresses(): List<String> = toGroups.asSequence().map { it.key }.toList()
    fun getFromAddresses(): List<String> = fromGroups.asSequence().map { it.key }.toList()

    fun getToAnnotations(): List<String> = toGroups.asSequence().map { it.value.second }.toList()
    fun getFromAnnotations(): List<String> = fromGroups.asSequence().map { it.value.second }.toList()

    fun getToTypes(): List<String> = toGroups.asSequence().map { it.value.first }.toList()
    fun getFromTypes(): List<String> = fromGroups.asSequence().map { it.value.first }.toList()

    private fun parseGroups(
        groupAddress: String,
        groupTypes: String,
        groupAnnotations: String
    ): Map<String, Pair<String, String>> {
        try {
            val addresses = split(groupAddress)
            val types = split(groupTypes)
            val annotations = split(groupAnnotations)

            val result: MutableMap<String, Pair<String, String>> = mutableMapOf()
            for (i in 0 until addresses.size) {
                val type = getElem(types, i)
                val annotation = getElem(annotations, i)
                result[addresses[i]] = Pair(type, annotation)
            }

            return result
        } catch (e: Exception) {
            return emptyMap()
        }
    }

    private fun split(value: String): List<String> = if (value.contains(',')) value.split(';') else value.split(';')

    private fun getElem(list: List<String>, i: Int): String = if (list.size >= i) list[i] else ""

    override fun isEmpty(): Boolean = amount == BigDecimal.ZERO && txCount == 0L && groupFromHash.isEmpty()

    override fun toString(): String {
        return "TokenGraph(amount=$amount, groupFromHash='$groupFromHash', groupFromSize=$groupFromSize, " +
                "groupToHash='$groupToHash', groupToSize=$groupToSize, txCount=$txCount, " +
                "fromGroupAddressesAsString='$fromGroupAddressesAsString', " +
                "toGroupAddressesAsString='$toGroupAddressesAsString', " +
                "fromGroupAddressesAnnotations='$fromGroupAddressesAnnotationsAsString', " +
                "toGroupAddressesAnnotations='$toGroupAddressesAnnotationsAsString', " +
                "fromGroupTypesAsString='$fromGroupTypesAsString', " +
                "toGroupTypesAsString='$toGroupTypesAsString', fromGroups=$fromGroups, toGroups=$toGroups)"
    }
}