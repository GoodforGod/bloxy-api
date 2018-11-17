package io.api.bloxy.model.dto.dex


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexContract(
    val smart_contract_address: String = "",
    val trades: Long = 0,
    val latest_trade: String = "",
    val protocol: String = "",
    val annotation: String = ""
) {
}