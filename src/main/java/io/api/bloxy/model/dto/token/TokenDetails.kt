package io.api.bloxy.model.dto.token


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenDetails(
    val address: String = "",
    val name: String = "",
    val symbol: String = "",
    val decimals: Int = 0,
    val type: String = ""
) {
}