package io.api.bloxy.model.dto.address


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class AddrCorrelation(
    val address: String = "",
    val symbol: String = "",
    val likelihood: Double = .0
) {
}