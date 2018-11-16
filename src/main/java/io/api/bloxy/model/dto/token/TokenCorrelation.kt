package io.api.bloxy.model.dto.token


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class TokenCorrelation(
    val symbol: String,
    val address: String,
    val transfers: Int
) {
}