package io.api.bloxy.model.dto.dex


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexTradeActive(
    val address: String = "",
    val maker_trades: Int = 0,
    val taker_trades: Int = 0,
    val dexes: Int = 0,
    val currencies: Int = 0,
    val from_time: String ="",
    val till_time: String = "",
    val contract_type: String = "",
    val address_type: String = "",
    val address_annotation: String  =""
) {
}