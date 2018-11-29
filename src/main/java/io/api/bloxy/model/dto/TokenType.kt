package io.api.bloxy.model.dto


/**
 * Ethereum ERC token type
 *
 * @author GoodforGod
 * @since 28.11.2018
 */
enum class TokenType {
    ERC20,
    ERC223,
    ERC621,
    ERC721,
    ERC777,
    ERC820,
    ERC827,
    UNKNOWN;

    companion object {
        fun parse(value:String) : TokenType {
            return when(value) {
                "ERC20" -> ERC20
                "ERC223" -> ERC223
                "ERC621" -> ERC621
                "ERC721" -> ERC721
                "ERC777" -> ERC777
                "ERC820" -> ERC820
                "ERC827" -> ERC827
                else -> UNKNOWN
            }
        }
    }
}