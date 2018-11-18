package io.api.bloxy.model.dto.moneyflow


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class AddrTransfer(
    val tx_hash: String = "",
    val tx_time: String = "",
    val direction: String = "",
    val party: String = "",
    val amount: Double = .0,
    val token_symbol: String = "",
    val token_address: String = "",
    val party_type: String = "",
    val party_annotation: String = ""
) {
}