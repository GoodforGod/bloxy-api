package io.api.bloxy.model.dto


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
enum class AddressType {
    WALLET,
    SMART_CONTRACT,
    DEX,
    MULTISIG,
    TOKEN,
    TOKEN_SALE,
    UNKNOWN;

    companion object {
        fun parse(value:String) : AddressType {
            return when(value) {
                "DEX" -> WALLET
                "Token" -> TOKEN
                "Wallet" -> WALLET
                "Multisig" -> MULTISIG
                "TokenSale" -> TOKEN_SALE
                "Smart Contract" -> SMART_CONTRACT
                else -> UNKNOWN
            }
        }
    }
}