package io.api.bloxy.model.dto.contract

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.util.ParamConverter.Companion.asDate
import io.api.bloxy.util.ParamConverter.Companion.asDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.08.2019
 */
data class ContractStat(
    @Json("period") val periodAsString: String = "",
    @Json("smart_contract_address") val address: String?,
    @Json("external_calls") val externalCalls: Long = 0L,
    @Json("first_call_at") val firstCallAtAsString: String = "",
    @Json("last_call_at") val lastCallAtAsString: String = "",
    val annotation: String = "",
    val type: String = "",
    val calls: Long = 0L,
    val txs: Long = 0L,
    val senders: Long = 0L,
    val callers: Long = 0L,
    val methods: Int = 0,
    val days: Int = 0
) : IModel, IAddressModel {

    enum class Aggregator {
        ALL,
        YEAR,
        MONTH,
        DAY
    }

    @Json(ignored = true) val period = periodAsString.asDate()

    @Json(ignored = true) val firstCallAt = firstCallAtAsString.asDateTime()
    @Json(ignored = true) val lastCallAt = lastCallAtAsString.asDateTime()

    override val addrType: AddressType = AddressType.parse(type)

    override fun isEmpty(): Boolean = periodAsString.isEmpty() && type.isEmpty()
            && calls == 0L && txs == 0L && days == 0
}