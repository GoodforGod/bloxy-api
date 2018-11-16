package io.api.bloxy.model.dto.token


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class HolderSimilar(
    val address: String = "",
    val symbol: String = "",
    val common_holders: Long = 0,
    val percentage: Double = .0
) {
}